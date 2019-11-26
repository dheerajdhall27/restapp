package com.basic.api.restapp.exception;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {

  private String verb;
  private String url;
  private String message;

  public static ExceptionResponseBuilder getBuilder() {
    return new ExceptionResponseBuilder();
  }

  public String getVerb() {
    return verb;
  }

  public void setVerb(String verb) {
    this.verb = verb;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public static class ExceptionResponseBuilder {

    private String verb;
    private String url;
    private String message;

    public ExceptionResponseBuilder addVerb(String verb) {
      this.verb = verb;
      return this;
    }

    public ExceptionResponseBuilder addURL(String url) {
      this.url = url;
      return this;
    }

    public ExceptionResponseBuilder addMessage(String message) {
      this.message = message;
      return this;
    }

    public ExceptionResponse build() {
      ExceptionResponse exceptionResponse = new ExceptionResponse();
      exceptionResponse.verb = this.verb;
      exceptionResponse.url = this.url;
      exceptionResponse.message = this.message;
      return exceptionResponse;
    }
  }
}
