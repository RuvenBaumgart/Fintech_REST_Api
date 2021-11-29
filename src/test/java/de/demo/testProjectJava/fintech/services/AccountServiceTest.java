package de.demo.testProjectJava.fintech.services;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import de.demo.testProjectJava.fintech.entities.AccountEntity;
import de.demo.testProjectJava.fintech.entities.TransactionsEntity;
import de.demo.testProjectJava.fintech.exceptions.BankAccountNotFoundException;
import de.demo.testProjectJava.fintech.exceptions.CustomerNotFoundException;
import de.demo.testProjectJava.fintech.repositories.AccountRepository;
import de.demo.testProjectJava.fintech.repositories.CustomerRepository;
import de.demo.testProjectJava.fintech.service.AccountService;

import static org.assertj.core.api.Assertions.*;

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
  public void isUpdatingAccountBalanceCorrectly() throws BankAccountNotFoundException{

    Long startAmount = 20000L;
    Long addAmount = 500L;

    Optional<AccountEntity> account = Optional.of(
      new AccountEntity(
        "123",
        "2135",
        startAmount,
        23L,
        Arrays.asList("232", "34424"),
        new ArrayList<TransactionsEntity>(),
        new ArrayList<TransactionsEntity>()
      )
    );

    Long expected = startAmount + addAmount;

    when(accountRepository.findById(anyString())).thenReturn(account);
    
    Long result = accountService.updateAccountBalance(account.get(), addAmount);

    assertThat(result).usingRecursiveComparison()
    .ignoringFields("transactions")
    .isEqualTo(expected);
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
      new ArrayList<TransactionsEntity>(),
      new ArrayList<TransactionsEntity>()
    );

     AccountEntity accountTwo = 
      new AccountEntity(
      "",
      "",
      1700000L,
      1L,
      new ArrayList<>(),
      new ArrayList<TransactionsEntity>(),
      new ArrayList<TransactionsEntity>()
    );

    when(accountRepository.findAllByCustomerId(anyString())).thenReturn(Optional.of(Arrays.asList(accountOne, accountTwo)));

    Long result = accountService.getAccountsAndCalculateSum("customerId");
    assertThat(result).isEqualTo(accountOne.getBalanceInCent() + accountTwo.getBalanceInCent());
  }

  @Test
  public void upatedTransactionIdsListCorrectly(){
  
    List<String> transactions = new ArrayList<String>( Arrays.asList("123","123"));
    transactions = AccountService.appendTransaction(transactions, "test");
    
    assertThat(transactions).hasSize(3);
    assertThat(transactions).contains("test");
    
  }
}
