package de.kirschUndKern.testProjectJava.fintech.services;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.*;

import de.kirschUndKern.testProjectJava.fintech.dto.response.AccountResponse;
import de.kirschUndKern.testProjectJava.fintech.entities.AccountEntity;
import de.kirschUndKern.testProjectJava.fintech.entities.TransactionsEntity;
import de.kirschUndKern.testProjectJava.fintech.exceptions.BankAccountNotFoundException;
import de.kirschUndKern.testProjectJava.fintech.exceptions.CustomerNotFoundException;
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
        Arrays.asList("232", "34424"),
        new ArrayList<TransactionsEntity>()
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
    
    AccountResponse result = accountService.updateAccountBalance("123", 500L, "54325", new TransactionsEntity());

    assertThat(result).usingRecursiveComparison().isEqualTo(expected);
  }

  @Test
  public void getCorrectBalanceOfAllCustomerAccounts() throws BankAccountNotFoundException{
    //given
    AccountEntity accountOne = 
      new AccountEntity(
      "",
      "",
      2300000L,
      0L,
      new ArrayList<>(),
      new ArrayList<TransactionsEntity>()
    );

     AccountEntity accountTwo = 
      new AccountEntity(
      "",
      "",
      1700000L,
      1L,
      new ArrayList<>(),
      new ArrayList<TransactionsEntity>()
    );

    when(accountRepository.findAllByCustomerId(anyString())).thenReturn(Optional.of(Arrays.asList(accountOne, accountTwo)));

    Long result = accountService.getAccountsAndCalculateSum("customerId");
    assertThat(result).isEqualTo(accountOne.getBalanceInCent() + accountTwo.getBalanceInCent());
  }
}
