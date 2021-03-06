/*
 * VideoRentalStore
 * A sample REST API for a VideoRentalStore
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;

/**
 * ReturnResponse
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-16T16:06:48.217+02:00")
public class ReturnResponse   {
  @JsonProperty("filmId")
  private String filmId = null;

  @JsonProperty("paid")
  private Integer paid = null;

  @JsonProperty("totalToPay")
  private Integer totalToPay = null;

  @JsonProperty("message")
  private String message = null;

  public ReturnResponse filmId(String filmId) {
    this.filmId = filmId;
    return this;
  }

   /**
   * Get filmId
   * @return filmId
  **/
  @JsonProperty("filmId")
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getFilmId() {
    return filmId;
  }

  public void setFilmId(String filmId) {
    this.filmId = filmId;
  }

  public ReturnResponse paid(Integer paid) {
    this.paid = paid;
    return this;
  }

   /**
   * Get paid
   * @return paid
  **/
  @JsonProperty("paid")
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public Integer getPaid() {
    return paid;
  }

  public void setPaid(Integer paid) {
    this.paid = paid;
  }

  public ReturnResponse totalToPay(Integer totalToPay) {
    this.totalToPay = totalToPay;
    return this;
  }

   /**
   * Get totalToPay
   * @return totalToPay
  **/
  @JsonProperty("totalToPay")
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public Integer getTotalToPay() {
    return totalToPay;
  }

  public void setTotalToPay(Integer totalToPay) {
    this.totalToPay = totalToPay;
  }

  public ReturnResponse message(String message) {
    this.message = message;
    return this;
  }

   /**
   * Get message
   * @return message
  **/
  @JsonProperty("message")
  @ApiModelProperty(value = "")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReturnResponse returnResponse = (ReturnResponse) o;
    return Objects.equals(this.filmId, returnResponse.filmId) &&
        Objects.equals(this.paid, returnResponse.paid) &&
        Objects.equals(this.totalToPay, returnResponse.totalToPay) &&
        Objects.equals(this.message, returnResponse.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(filmId, paid, totalToPay, message);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReturnResponse {\n");
    
    sb.append("    filmId: ").append(toIndentedString(filmId)).append("\n");
    sb.append("    paid: ").append(toIndentedString(paid)).append("\n");
    sb.append("    totalToPay: ").append(toIndentedString(totalToPay)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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

