package de.kirschUndKern.testProjectJava.fintech.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;

import de.kirschUndKern.testProjectJava.fintech.exceptions.WrongDateFormatException;

public class CustomerService {

  public static  LocalDate convertToDate(String date) throws WrongDateFormatException{
    try{
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate convertedDate = LocalDate.parse(date,formatter);
    return convertedDate;
    } catch(Exception e){
      throw new WrongDateFormatException("The Data of Birth needs to be in the format dd/MM/yyyy", HttpStatus.BAD_REQUEST);
    }
  }
  
}
