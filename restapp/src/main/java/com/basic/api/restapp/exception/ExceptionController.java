package com.basic.api.restapp.exception;

import java.util.Iterator;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    String error = "Not a JSON object";
    ExceptionResponse response = ExceptionResponse.getBuilder()
        .addVerb("POST")
        .addURL("https://myrestapp.cisco.com/api/objects")
        .addMessage(error).build();
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }
}
