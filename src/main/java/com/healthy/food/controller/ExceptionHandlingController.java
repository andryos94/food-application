package com.healthy.food.controller;

import com.healthy.food.util.InvalidFilteringRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

  @ExceptionHandler(InvalidFilteringRequestException.class)
  public ResponseEntity handleException(InvalidFilteringRequestException exception, WebRequest request) {
    //when an InvalidFilteringRequestException has been thrown, a message is showed in the web interface
    return handleExceptionInternal(exception, exception.getMessage(),
            new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }
}
