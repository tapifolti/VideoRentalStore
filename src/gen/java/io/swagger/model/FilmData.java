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
import io.swagger.model.FilmKind;
import javax.validation.constraints.*;

/**
 * FilmData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-15T21:13:37.898+02:00")
public class FilmData   {
  @JsonProperty("title")
  private String title = null;

  @JsonProperty("kind")
  private FilmKind kind = null;

  public FilmData title(String title) {
    this.title = title;
    return this;
  }

   /**
   * Get title
   * @return title
  **/
  @JsonProperty("title")
  @ApiModelProperty(value = "")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public FilmData kind(FilmKind kind) {
    this.kind = kind;
    return this;
  }

   /**
   * Get kind
   * @return kind
  **/
  @JsonProperty("kind")
  @ApiModelProperty(value = "")
  public FilmKind getKind() {
    return kind;
  }

  public void setKind(FilmKind kind) {
    this.kind = kind;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FilmData filmData = (FilmData) o;
    return Objects.equals(this.title, filmData.title) &&
        Objects.equals(this.kind, filmData.kind);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, kind);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FilmData {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    kind: ").append(toIndentedString(kind)).append("\n");
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
