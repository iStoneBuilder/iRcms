package com.stone.it.rcms.core.http;

/**
 * @author cj.stone
 * @Desc
 */

public class ResponseEntity {

  private String code;
  private String message;
  private String body;
  private String errors;

  public ResponseEntity() {
    code = "200";
    message = "success";
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getErrors() {
    return errors;
  }

  public void setErrors(String errors) {
    this.errors = errors;
  }
}
