/**
 * The MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.influxdb.client.scala.internal

import java.util.function.BiConsumer

import akka.NotUsed
import akka.stream.OverflowStrategy
import akka.stream.scaladsl.Source
import com.influxdb.client.InfluxDBClientOptions
import com.influxdb.client.domain.{Dialect, Query}
import com.influxdb.client.internal.AbstractInfluxDBClient
import com.influxdb.client.scala.QueryScalaApi
import com.influxdb.client.service.QueryService
import com.influxdb.internal.AbstractQueryApi
import com.influxdb.query.internal.FluxCsvParser
import com.influxdb.query.{FluxRecord, FluxTable}
import com.influxdb.{Arguments, Cancellable}
import javax.annotation.Nonnull

import scala.compat.java8.FunctionConverters.asJavaConsumer

/**
 * @author Jakub Bednar (bednar@github) (06/11/2018 08:19)
 */
class QueryScalaApiImpl(@Nonnull service: QueryService,
                        @Nonnull options: InfluxDBClientOptions,
                        @Nonnull val bufferSize: Int,
                        @Nonnull val overflowStrategy: OverflowStrategy)

  extends AbstractQueryApi()
    with QueryScalaApi {

  Arguments.checkNotNull(overflowStrategy, "overflowStrategy")
  Arguments.checkNotNull(bufferSize, "bufferSize")

  /**
   * Executes the Flux query against the InfluxDB and asynchronously stream [[FluxRecord]]s to [[Stream]].
   *
   * <p>[[com.influxdb.client.InfluxDBClientOptions#getOrg()]] will be used as source organization. </p>
   *
   * @param query the flux query to execute
   * @return the stream of [[FluxRecord]]s
   */
  override def query(query: String): Source[FluxRecord, NotUsed] = {

    Arguments.checkNotNull(options.getOrg, "InfluxDBClientOptions.getOrg")

    this.query(query, options.getOrg)
  }

  /**
   * Executes the Flux query against the InfluxDB and asynchronously stream [[FluxRecord]]s to [[Stream]].
   *
   * @param query the flux query to execute
   * @return the stream of [[FluxRecord]]s
   */
  override def query(@Nonnull query: String, @Nonnull org: String): Source[FluxRecord, NotUsed] = {

    Arguments.checkNonEmpty(query, "query")
    Arguments.checkNonEmpty(org, "org")

    this.query(new Query().query(query).dialect(AbstractInfluxDBClient.DEFAULT_DIALECT), org)
  }


  /**
   * Executes the Flux query against the InfluxDB and asynchronously stream [[FluxRecord]]s to [[Stream]].
   *
   * <p>[[com.influxdb.client.InfluxDBClientOptions#getOrg()]] will be used as source organization. </p>
   *
   * @param query the flux query to execute
   * @return the stream of [[FluxRecord]]s
   */
  override def query(query: Query): Source[FluxRecord, NotUsed] = {

    Arguments.checkNotNull(options.getOrg, "InfluxDBClientOptions.getOrg")

    this.query(query, options.getOrg)
  }

  /**
   * Executes the Flux query against the InfluxDB and asynchronously stream [[FluxRecord]]s to [[Stream]].
   *
   * @param query the flux query to execute
   * @param org   specifies the source organization
   * @return the stream of [[FluxRecord]]s
   */
  override def query(query: Query, org: String): Source[FluxRecord, NotUsed] = {

    Arguments.checkNotNull(query, "query")
    Arguments.checkNonEmpty(org, "org")

    Source
      .single(query)
      .map(_ => service
        .postQueryResponseBody(null, "application/json",
          null, org, null, query))
      .flatMapConcat(queryCall => {
        Source.queue[FluxRecord](bufferSize, overflowStrategy)
          .mapMaterializedValue(queue => {

            val eventualDone = queue.watchCompletion()
            val consumer = new FluxCsvParser.FluxResponseConsumer() {

              override
              def accept(index: Int, @Nonnull cancellable: Cancellable, @Nonnull table: FluxTable): Unit = {
              }

              override
              def accept(index: Int, @Nonnull cancellable: Cancellable, @Nonnull record: FluxRecord): Unit = {
                if (eventualDone.isCompleted) {
                  cancellable.cancel()
                } else {
                  queue.offer(record)
                }
              }
            }

            val onError = asJavaConsumer[Throwable](t => queue.fail(t))

            this.query(queryCall, consumer, onError, () => queue.complete, true)
          })
      })
  }

  /**
   * Executes the Flux query against the InfluxDB and asynchronously stream measurements to [[Stream]].
   *
   * <p>[[com.influxdb.client.InfluxDBClientOptions#getOrg()]] will be used as source organization. </p>
   *
   * @param query           the flux query to execute
   * @param measurementType the measurement (POJO)
   * @tparam M the type of the measurement (POJO)
   * @return the stream of measurements
   */
  override def query[M](query: String, measurementType: Class[M]): Source[M, NotUsed] = {

    Arguments.checkNotNull(options.getOrg, "InfluxDBClientOptions.getOrg")

    this.query(query, options.getOrg, measurementType)
  }

  /**
   * Executes the Flux query against the InfluxDB and asynchronously stream measurements to [[Stream]].
   *
   * @param query           the flux query to execute
   * @param measurementType the measurement (POJO)
   * @tparam M the type of the measurement (POJO)
   * @return the stream of measurements
   */
  override def query[M](@Nonnull query: String, @Nonnull org: String, @Nonnull measurementType: Class[M]): Source[M, NotUsed] = {

    Arguments.checkNonEmpty(query, "query")
    Arguments.checkNonEmpty(org, "org")
    Arguments.checkNotNull(measurementType, "measurementType")

    this.query(query, org).map(t => resultMapper.toPOJO(t, measurementType))
  }

  /**
   * Executes the Flux query against the InfluxDB and asynchronously stream measurements to [[Stream]].
   *
   * <p>[[com.influxdb.client.InfluxDBClientOptions#getOrg()]] will be used as source organization. </p>
   *
   * @param query           the flux query to execute
   * @param measurementType the measurement (POJO)
   * @tparam M the type of the measurement (POJO)
   * @return the stream of measurements
   */
  override def query[M](query: Query, measurementType: Class[M]): Source[M, NotUsed] = {

    Arguments.checkNotNull(options.getOrg, "InfluxDBClientOptions.getOrg")

    this.query(query, options.getOrg, measurementType)
  }

  /**
   * Executes the Flux query against the InfluxDB and asynchronously stream measurements to [[Stream]].
   *
   * @param query           the flux query to execute
   * @param org             specifies the source organization
   * @param measurementType the measurement (POJO)
   * @tparam M the type of the measurement (POJO)
   * @return the stream of measurements
   */
  override def query[M](query: Query, org: String, measurementType: Class[M]): Source[M, NotUsed] = {

    Arguments.checkNotNull(query, "query")
    Arguments.checkNonEmpty(org, "org")
    Arguments.checkNotNull(measurementType, "measurementType")

    this.query(query, org).map(t => resultMapper.toPOJO(t, measurementType))
  }

  /**
   * Executes the Flux query against the InfluxDB and asynchronously stream response to [[Stream]].
   *
   * <p>[[com.influxdb.client.InfluxDBClientOptions#getOrg()]] will be used as source organization. </p>
   *
   * @param query the flux query to execute
   * @return the response stream
   */
  override def queryRaw(query: String): Source[String, NotUsed] = {

    Arguments.checkNotNull(options.getOrg, "InfluxDBClientOptions.getOrg")

    this.queryRaw(query, options.getOrg)
  }

  /**
   * Executes the Flux query against the InfluxDB and asynchronously stream response to [[Stream]].
   *
   * @param query the flux query to execute
   * @return the response stream
   */
  override def queryRaw(@Nonnull query: String, @Nonnull org: String): Source[String, NotUsed] = {

    Arguments.checkNonEmpty(query, "query")
    Arguments.checkNonEmpty(org, "org")

    this.queryRaw(query, AbstractInfluxDBClient.DEFAULT_DIALECT, org)
  }

  /**
   * Executes the Flux query against the InfluxDB and asynchronously stream response to [[Stream]].
   *
   * <p>[[com.influxdb.client.InfluxDBClientOptions#getOrg()]] will be used as source organization. </p>
   *
   * @param query   the flux query to execute
   * @param dialect Dialect is an object defining the options to use when encoding the response.
   *                [[http://bit.ly/flux-dialect See dialect SPEC]].
   * @return the response stream
   */
  override def queryRaw(query: String, dialect: Dialect): Source[String, NotUsed] = {

    Arguments.checkNotNull(options.getOrg, "InfluxDBClientOptions.getOrg")

    this.queryRaw(query, dialect, options.getOrg)
  }

  /**
   * Executes the Flux query against the InfluxDB and asynchronously stream response to [[Stream]].
   *
   * @param query   the flux query to execute
   * @param dialect Dialect is an object defining the options to use when encoding the response.
   *                [[http://bit.ly/flux-dialect See dialect SPEC]].
   * @return the response stream
   */
  override def queryRaw(query: String, dialect: Dialect, org: String): Source[String, NotUsed] = {

    Arguments.checkNonEmpty(query, "query")
    Arguments.checkNonEmpty(org, "org")

    this.queryRaw(new Query().query(query).dialect(dialect), org)
  }

  /**
   * Executes the Flux query against the InfluxDB and asynchronously stream response to [[Stream]].
   *
   * <p>[[com.influxdb.client.InfluxDBClientOptions#getOrg()]] will be used as source organization. </p>
   *
   * @param query the flux query to execute
   * @return the response stream
   */
  override def queryRaw(query: Query): Source[String, NotUsed] = {

    Arguments.checkNotNull(options.getOrg, "InfluxDBClientOptions.getOrg")

    this.queryRaw(query, options.getOrg)
  }

  /**
   * Executes the Flux query against the InfluxDB and asynchronously stream response to [[Stream]].
   *
   * @param query the flux query to execute
   * @param org   specifies the source organization
   * @return the response stream
   */
  override def queryRaw(query: Query, org: String): Source[String, NotUsed] = {

    Arguments.checkNotNull(query, "query")
    Arguments.checkNonEmpty(org, "org")

    Source
      .single(query)
      .map(it => service
        .postQueryResponseBody(null, "application/json",
          null, org, null, query))
      .flatMapConcat(queryCall => {
        Source.queue[String](bufferSize, overflowStrategy)
          .mapMaterializedValue(queue => {

            val eventualDone = queue.watchCompletion()

            val onResponse = new BiConsumer[Cancellable, String] {
              override def accept(cancellable: Cancellable, line: String): Unit = {
                if (eventualDone.isCompleted) {
                  cancellable.cancel()
                } else {
                  queue.offer(line)
                }
              }
            }

            val onError = asJavaConsumer[Throwable](t => queue.fail(t))

            this.queryRaw(queryCall, onResponse, onError, () => queue.complete, true)
          })
      })
  }
}
