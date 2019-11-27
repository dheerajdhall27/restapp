package com.basic.api.restapp.exception;

import com.basic.api.restapp.util.Global;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    ServletWebRequest servletWebRequest = (ServletWebRequest) request;

    String error = "Not a JSON object";
    ExceptionResponse response = ExceptionResponse.getBuilder()
        .addVerb(servletWebRequest.getHttpMethod().name())
        .addURL(Global.URL)
        .addMessage(error).build();
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    ServletWebRequest servletWebRequest = (ServletWebRequest) request;
    String error = "Mismatch in types";
    ExceptionResponse response = ExceptionResponse.getBuilder()
        .addVerb(servletWebRequest.getHttpMethod().name())
        .addURL(Global.URL)
        .addMessage(error).build();
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
      HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status,
      WebRequest request) {
    ServletWebRequest servletWebRequest = (ServletWebRequest) request;
    String error = "Incorrect URL";
    ExceptionResponse response = ExceptionResponse.getBuilder()
        .addVerb(servletWebRequest.getHttpMethod().name())
        .addURL(Global.URL)
        .addMessage(error).build();
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }
}
