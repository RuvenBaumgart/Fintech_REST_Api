package de.kirschUndKern.testProjectJava.fintech.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import de.kirschUndKern.testProjectJava.fintech.exceptions.WrongDateFormatException;
import de.kirschUndKern.testProjectJava.fintech.service.CustomerService;

public class CustomerServiceTest {

  CustomerService customerService;

  @BeforeEach
  public void init(){
    customerService = new CustomerService();
  }
  @Test 
  public void isDateStringCorrectConvertetToDateObject() throws WrongDateFormatException{
    //given
    String date = "22/05/2020";
    LocalDate expectedDate = LocalDate.of(2020,05,22);
    //when
    LocalDate convertedDate = CustomerService.convertToDate(date);
    //then
    assertThat(convertedDate).isEqualTo(expectedDate);
  }

  @Test
  public void isInvalidDateThrowingCorrectException(){
    //given
    String date = "22.05.2020";
    assertThatThrownBy(() -> CustomerService.convertToDate(date)).isInstanceOf(WrongDateFormatException.class);
  }
}
