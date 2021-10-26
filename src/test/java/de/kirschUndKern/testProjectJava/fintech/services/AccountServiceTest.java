package de.kirschUndKern.testProjectJava.fintech.services;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.*;

import de.kirschUndKern.testProjectJava.fintech.entities.AccountEntity;
import de.kirschUndKern.testProjectJava.fintech.exceptions.BankAccountNotFoundException;
import de.kirschUndKern.testProjectJava.fintech.exceptions.CustomerNotFoundException;
import de.kirschUndKern.testProjectJava.fintech.modell.AccountResponse;
import de.kirschUndKern.testProjectJava.fintech.repositories.AccountRepository;
import de.kirschUndKern.testProjectJava.fintech.repositories.CustomerRepository;
import de.kirschUndKern.testProjectJava.fintech.service.AccountService;

public class AccountServiceTest {
  
  private AccountService accountService;
  private CustomerRepository customerRepository;
  private AccountRepository accountRepository;

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

  @Test
  public void isUpdatingAccountCorrectly() throws BankAccountNotFoundException{

    Long startAmount = 20000L;
    Long addAmount = 500L;

    Optional<AccountEntity> account = Optional.of(
      new AccountEntity(
        "123",
        "2135",
        startAmount,
        23L,
        Arrays.asList("232", "34424")
      )
    );

    AccountResponse expected = new AccountResponse(
      "123",
      "2135",
      startAmount + addAmount,
      24L,
      Arrays.asList("232","34424","54325")
    );

    when(accountRepository.findById(anyString())).thenReturn(account);
    
    AccountResponse result = accountService.updateAccountBalance("123", 500L, "54325");

    assertThat(result).usingRecursiveComparison().isEqualTo(expected);


  }
}