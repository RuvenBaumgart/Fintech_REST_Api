package de.kirschUndKern.testProjectJava.fintech.exceptions;

import org.springframework.http.HttpStatus;

public class WrongDateFormatException extends Exception {
  private final String errorMessage;
  private final HttpStatus errorStatus;

  public WrongDateFormatException(
    String errorMessage,
    HttpStatus errorStatus
  ){
    this.errorMessage = errorMessage;
    this.errorStatus = errorStatus;
  }

  public String getErrorMessage(){
    return this.errorMessage;
  }

  public HttpStatus getErrorStatus(){
    return this.errorStatus;
  }
}
