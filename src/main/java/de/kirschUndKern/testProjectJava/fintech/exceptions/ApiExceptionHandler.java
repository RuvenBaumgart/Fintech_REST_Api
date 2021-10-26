package de.kirschUndKern.testProjectJava.fintech.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
  
  @ExceptionHandler
  public ResponseEntity<ErrorInfo> handleErrors(HttpServletRequest request, Exception exception){
    String errorMessage = exception.getMessage();
    ErrorInfo error = new ErrorInfo(errorMessage, request.getRequestURL().toString());
    System.out.println(errorMessage + exception);

    if(exception instanceof CustomerNotFoundException){
      return ResponseEntity.status(CustomerNotFoundException.class.cast(exception).getErrorStatus()).body(error);
    } else if(exception instanceof WrongDateFormatException){
      return ResponseEntity.status(WrongDateFormatException.class.cast(exception).getErrorStatus()).body(error);
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
  }

  public static class ErrorInfo{
    String error;
    String path;

    public ErrorInfo(
      String error,
      String path
    ){
      this.error = error;
      this.path = path;
    }

    public String getError(){
      return this.error;
    }

    public String getPath(){
      return this.path;
    }
  }
}
