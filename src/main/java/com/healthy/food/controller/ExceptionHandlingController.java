package com.healthy.food.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

  @ExceptionHandler(Exception.class) // TODO: change to InvalidFilteringRequestException
  public String handleException(Exception exception) {
    // logger
    //        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
    return exception.getMessage();
  }
}
