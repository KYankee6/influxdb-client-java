/*
 * Influx API Service
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * OpenAPI spec version: 0.1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.influxdata.client.domain;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import org.influxdata.client.domain.RoutesExternal;
import org.influxdata.client.domain.RoutesQuery;
import org.influxdata.client.domain.RoutesSystem;

/**
 * Routes
 */

public class Routes {
  public static final String SERIALIZED_NAME_AUTHORIZATIONS = "authorizations";
  @SerializedName(SERIALIZED_NAME_AUTHORIZATIONS)
  private String authorizations;

  public static final String SERIALIZED_NAME_BUCKETS = "buckets";
  @SerializedName(SERIALIZED_NAME_BUCKETS)
  private String buckets;

  public static final String SERIALIZED_NAME_DASHBOARDS = "dashboards";
  @SerializedName(SERIALIZED_NAME_DASHBOARDS)
  private String dashboards;

  public static final String SERIALIZED_NAME_EXTERNAL = "external";
  @SerializedName(SERIALIZED_NAME_EXTERNAL)
  private RoutesExternal external = null;

  public static final String SERIALIZED_NAME_VARIABLES = "variables";
  @SerializedName(SERIALIZED_NAME_VARIABLES)
  private String variables;

  public static final String SERIALIZED_NAME_ME = "me";
  @SerializedName(SERIALIZED_NAME_ME)
  private String me;

  public static final String SERIALIZED_NAME_ORGS = "orgs";
  @SerializedName(SERIALIZED_NAME_ORGS)
  private String orgs;

  public static final String SERIALIZED_NAME_QUERY = "query";
  @SerializedName(SERIALIZED_NAME_QUERY)
  private RoutesQuery query = null;

  public static final String SERIALIZED_NAME_SETUP = "setup";
  @SerializedName(SERIALIZED_NAME_SETUP)
  private String setup;

  public static final String SERIALIZED_NAME_SIGNIN = "signin";
  @SerializedName(SERIALIZED_NAME_SIGNIN)
  private String signin;

  public static final String SERIALIZED_NAME_SIGNOUT = "signout";
  @SerializedName(SERIALIZED_NAME_SIGNOUT)
  private String signout;

  public static final String SERIALIZED_NAME_SOURCES = "sources";
  @SerializedName(SERIALIZED_NAME_SOURCES)
  private String sources;

  public static final String SERIALIZED_NAME_SYSTEM = "system";
  @SerializedName(SERIALIZED_NAME_SYSTEM)
  private RoutesSystem system = null;

  public static final String SERIALIZED_NAME_TASKS = "tasks";
  @SerializedName(SERIALIZED_NAME_TASKS)
  private String tasks;

  public static final String SERIALIZED_NAME_TELEGRAFS = "telegrafs";
  @SerializedName(SERIALIZED_NAME_TELEGRAFS)
  private String telegrafs;

  public static final String SERIALIZED_NAME_USERS = "users";
  @SerializedName(SERIALIZED_NAME_USERS)
  private String users;

  public static final String SERIALIZED_NAME_WRITE = "write";
  @SerializedName(SERIALIZED_NAME_WRITE)
  private String write;

  public Routes authorizations(String authorizations) {
    this.authorizations = authorizations;
    return this;
  }

   /**
   * Get authorizations
   * @return authorizations
  **/
  @ApiModelProperty(value = "")
  public String getAuthorizations() {
    return authorizations;
  }

  public void setAuthorizations(String authorizations) {
    this.authorizations = authorizations;
  }

  public Routes buckets(String buckets) {
    this.buckets = buckets;
    return this;
  }

   /**
   * Get buckets
   * @return buckets
  **/
  @ApiModelProperty(value = "")
  public String getBuckets() {
    return buckets;
  }

  public void setBuckets(String buckets) {
    this.buckets = buckets;
  }

  public Routes dashboards(String dashboards) {
    this.dashboards = dashboards;
    return this;
  }

   /**
   * Get dashboards
   * @return dashboards
  **/
  @ApiModelProperty(value = "")
  public String getDashboards() {
    return dashboards;
  }

  public void setDashboards(String dashboards) {
    this.dashboards = dashboards;
  }

  public Routes external(RoutesExternal external) {
    this.external = external;
    return this;
  }

   /**
   * Get external
   * @return external
  **/
  @ApiModelProperty(value = "")
  public RoutesExternal getExternal() {
    return external;
  }

  public void setExternal(RoutesExternal external) {
    this.external = external;
  }

  public Routes variables(String variables) {
    this.variables = variables;
    return this;
  }

   /**
   * Get variables
   * @return variables
  **/
  @ApiModelProperty(value = "")
  public String getVariables() {
    return variables;
  }

  public void setVariables(String variables) {
    this.variables = variables;
  }

  public Routes me(String me) {
    this.me = me;
    return this;
  }

   /**
   * Get me
   * @return me
  **/
  @ApiModelProperty(value = "")
  public String getMe() {
    return me;
  }

  public void setMe(String me) {
    this.me = me;
  }

  public Routes orgs(String orgs) {
    this.orgs = orgs;
    return this;
  }

   /**
   * Get orgs
   * @return orgs
  **/
  @ApiModelProperty(value = "")
  public String getOrgs() {
    return orgs;
  }

  public void setOrgs(String orgs) {
    this.orgs = orgs;
  }

  public Routes query(RoutesQuery query) {
    this.query = query;
    return this;
  }

   /**
   * Get query
   * @return query
  **/
  @ApiModelProperty(value = "")
  public RoutesQuery getQuery() {
    return query;
  }

  public void setQuery(RoutesQuery query) {
    this.query = query;
  }

  public Routes setup(String setup) {
    this.setup = setup;
    return this;
  }

   /**
   * Get setup
   * @return setup
  **/
  @ApiModelProperty(value = "")
  public String getSetup() {
    return setup;
  }

  public void setSetup(String setup) {
    this.setup = setup;
  }

  public Routes signin(String signin) {
    this.signin = signin;
    return this;
  }

   /**
   * Get signin
   * @return signin
  **/
  @ApiModelProperty(value = "")
  public String getSignin() {
    return signin;
  }

  public void setSignin(String signin) {
    this.signin = signin;
  }

  public Routes signout(String signout) {
    this.signout = signout;
    return this;
  }

   /**
   * Get signout
   * @return signout
  **/
  @ApiModelProperty(value = "")
  public String getSignout() {
    return signout;
  }

  public void setSignout(String signout) {
    this.signout = signout;
  }

  public Routes sources(String sources) {
    this.sources = sources;
    return this;
  }

   /**
   * Get sources
   * @return sources
  **/
  @ApiModelProperty(value = "")
  public String getSources() {
    return sources;
  }

  public void setSources(String sources) {
    this.sources = sources;
  }

  public Routes system(RoutesSystem system) {
    this.system = system;
    return this;
  }

   /**
   * Get system
   * @return system
  **/
  @ApiModelProperty(value = "")
  public RoutesSystem getSystem() {
    return system;
  }

  public void setSystem(RoutesSystem system) {
    this.system = system;
  }

  public Routes tasks(String tasks) {
    this.tasks = tasks;
    return this;
  }

   /**
   * Get tasks
   * @return tasks
  **/
  @ApiModelProperty(value = "")
  public String getTasks() {
    return tasks;
  }

  public void setTasks(String tasks) {
    this.tasks = tasks;
  }

  public Routes telegrafs(String telegrafs) {
    this.telegrafs = telegrafs;
    return this;
  }

   /**
   * Get telegrafs
   * @return telegrafs
  **/
  @ApiModelProperty(value = "")
  public String getTelegrafs() {
    return telegrafs;
  }

  public void setTelegrafs(String telegrafs) {
    this.telegrafs = telegrafs;
  }

  public Routes users(String users) {
    this.users = users;
    return this;
  }

   /**
   * Get users
   * @return users
  **/
  @ApiModelProperty(value = "")
  public String getUsers() {
    return users;
  }

  public void setUsers(String users) {
    this.users = users;
  }

  public Routes write(String write) {
    this.write = write;
    return this;
  }

   /**
   * Get write
   * @return write
  **/
  @ApiModelProperty(value = "")
  public String getWrite() {
    return write;
  }

  public void setWrite(String write) {
    this.write = write;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Routes routes = (Routes) o;
    return Objects.equals(this.authorizations, routes.authorizations) &&
        Objects.equals(this.buckets, routes.buckets) &&
        Objects.equals(this.dashboards, routes.dashboards) &&
        Objects.equals(this.external, routes.external) &&
        Objects.equals(this.variables, routes.variables) &&
        Objects.equals(this.me, routes.me) &&
        Objects.equals(this.orgs, routes.orgs) &&
        Objects.equals(this.query, routes.query) &&
        Objects.equals(this.setup, routes.setup) &&
        Objects.equals(this.signin, routes.signin) &&
        Objects.equals(this.signout, routes.signout) &&
        Objects.equals(this.sources, routes.sources) &&
        Objects.equals(this.system, routes.system) &&
        Objects.equals(this.tasks, routes.tasks) &&
        Objects.equals(this.telegrafs, routes.telegrafs) &&
        Objects.equals(this.users, routes.users) &&
        Objects.equals(this.write, routes.write);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authorizations, buckets, dashboards, external, variables, me, orgs, query, setup, signin, signout, sources, system, tasks, telegrafs, users, write);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Routes {\n");
    sb.append("    authorizations: ").append(toIndentedString(authorizations)).append("\n");
    sb.append("    buckets: ").append(toIndentedString(buckets)).append("\n");
    sb.append("    dashboards: ").append(toIndentedString(dashboards)).append("\n");
    sb.append("    external: ").append(toIndentedString(external)).append("\n");
    sb.append("    variables: ").append(toIndentedString(variables)).append("\n");
    sb.append("    me: ").append(toIndentedString(me)).append("\n");
    sb.append("    orgs: ").append(toIndentedString(orgs)).append("\n");
    sb.append("    query: ").append(toIndentedString(query)).append("\n");
    sb.append("    setup: ").append(toIndentedString(setup)).append("\n");
    sb.append("    signin: ").append(toIndentedString(signin)).append("\n");
    sb.append("    signout: ").append(toIndentedString(signout)).append("\n");
    sb.append("    sources: ").append(toIndentedString(sources)).append("\n");
    sb.append("    system: ").append(toIndentedString(system)).append("\n");
    sb.append("    tasks: ").append(toIndentedString(tasks)).append("\n");
    sb.append("    telegrafs: ").append(toIndentedString(telegrafs)).append("\n");
    sb.append("    users: ").append(toIndentedString(users)).append("\n");
    sb.append("    write: ").append(toIndentedString(write)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

