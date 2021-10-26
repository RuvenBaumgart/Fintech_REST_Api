package de.kirschUndKern.testProjectJava.fintech.services;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.*;

import de.kirschUndKern.testProjectJava.fintech.exceptions.CustomerNotFoundException;
import de.kirschUndKern.testProjectJava.fintech.repositories.AccountRepository;
import de.kirschUndKern.testProjectJava.fintech.repositories.CustomerRepository;
import de.kirschUndKern.testProjectJava.fintech.service.AccountService;

public class AccountServiceTest {
  
  AccountService accountService;
  CustomerRepository customerRepository;
  AccountRepository accountRepository;

  @BeforeEach
  public void init(){
    customerRepository = Mockito.mock(CustomerRepository.class);
    accountRepository = Mockito.mock(AccountRepository.class);
    accountService = new AccountService(
      accountRepository, 
      customerRepository
    );
  }
  @Test
  public void isThrowingExceptionWhenCustomerIdNotFound(){
    //given
    when(customerRepository.findById(anyString())).thenReturn(Optional.empty());
    //when
    //then
    assertThatThrownBy(()-> accountService.saveNewAccountForCustomer("id")).isInstanceOf(CustomerNotFoundException.class);
  }
}
