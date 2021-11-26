package de.kirschUndKern.testProjectJava.fintech.exceptions;

import org.springframework.http.HttpStatus;

public class BankAccountNotFoundException extends Exception {
  private final String errorMessage;
  private final HttpStatus errorStatus;

  public BankAccountNotFoundException(
  ){
    this.errorMessage = "Bankaccount not found";
    this.errorStatus = HttpStatus.BAD_REQUEST;
  }

  public String getMessage(){
    return errorMessage;
  }

  public HttpStatus getErrorStatus(){
    return errorStatus;
  }

}
