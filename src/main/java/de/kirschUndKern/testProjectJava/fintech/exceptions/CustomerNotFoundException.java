package de.kirschUndKern.testProjectJava.fintech.exceptions;

import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends Exception {
  private final String errorMessage;
  private final HttpStatus errorStatus;

  public CustomerNotFoundException(
    String errorMessage,
    HttpStatus errorStatus
  ){
    this.errorMessage = errorMessage;
    this.errorStatus = errorStatus;
  }

  public String getMessage(){
    return errorMessage;
  }

  public HttpStatus getErrorStatus(){
    return errorStatus;
  }

}
