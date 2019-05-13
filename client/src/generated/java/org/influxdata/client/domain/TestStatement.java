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
import org.influxdata.client.domain.VariableAssignment;

/**
 * declares a Flux test case
 */
@ApiModel(description = "declares a Flux test case")

public class TestStatement extends Statement {
  public static final String SERIALIZED_NAME_TYPE = "type";
  @SerializedName(SERIALIZED_NAME_TYPE)
  private String type;

  public static final String SERIALIZED_NAME_ASSIGNMENT = "assignment";
  @SerializedName(SERIALIZED_NAME_ASSIGNMENT)
  private VariableAssignment assignment = null;

  public TestStatement type(String type) {
    this.type = type;
    return this;
  }

   /**
   * type of AST node
   * @return type
  **/
  @ApiModelProperty(value = "type of AST node")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public TestStatement assignment(VariableAssignment assignment) {
    this.assignment = assignment;
    return this;
  }

   /**
   * Get assignment
   * @return assignment
  **/
  @ApiModelProperty(value = "")
  public VariableAssignment getAssignment() {
    return assignment;
  }

  public void setAssignment(VariableAssignment assignment) {
    this.assignment = assignment;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TestStatement testStatement = (TestStatement) o;
    return Objects.equals(this.type, testStatement.type) &&
        Objects.equals(this.assignment, testStatement.assignment) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, assignment, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TestStatement {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    assignment: ").append(toIndentedString(assignment)).append("\n");
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

