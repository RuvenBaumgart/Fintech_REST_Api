package de.kirschUndKern.testProjectJava.fintech.exceptions;

import org.springframework.http.HttpStatus;

public class CreditNotFoundException extends Exception {
  private final String errorMessage;
  private final HttpStatus errorStatus;

  public CreditNotFoundException(
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

