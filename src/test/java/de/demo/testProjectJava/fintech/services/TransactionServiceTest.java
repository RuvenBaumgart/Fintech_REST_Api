package de.demo.testProjectJava.fintech.services;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import de.demo.testProjectJava.fintech.dto.request.TransactionRequest;
import de.demo.testProjectJava.fintech.dto.response.AccountResponse;
import de.demo.testProjectJava.fintech.dto.response.TransactionsFullResponse;
import de.demo.testProjectJava.fintech.entities.AccountEntity;
import de.demo.testProjectJava.fintech.entities.TransactionsEntity;
import de.demo.testProjectJava.fintech.exceptions.BankAccountNotFoundException;
import de.demo.testProjectJava.fintech.repositories.AccountRepository;
import de.demo.testProjectJava.fintech.repositories.CustomerRepository;
import de.demo.testProjectJava.fintech.repositories.TransactionRepository;
import de.demo.testProjectJava.fintech.service.AccountService;
import de.demo.testProjectJava.fintech.service.TransactionService;

public class TransactionServiceTest {
  
  private TransactionService transactionService;
  private AccountRepository accountRepository;
  private TransactionRepository transactionRepository;
  private CustomerRepository customerRepository;
  private AccountService accountService;

  @BeforeEach
  public void init(){
    accountRepository = Mockito.mock(AccountRepository.class);
    transactionRepository = Mockito.mock(TransactionRepository.class);
    customerRepository = Mockito.mock(CustomerRepository.class);
    accountService = new AccountService(
      accountRepository,
      customerRepository
    );
    transactionService = new TransactionService(
      accountRepository, 
      transactionRepository,
      customerRepository,
      accountService
    );
  }

  @Test
  public void isCreatingTransaction() throws BankAccountNotFoundException{
    Optional<AccountEntity> accountA = Optional.of(
      new AccountEntity(
      UUID.randomUUID().toString(),
      "1234",
      1000000L,
      23L,
      new ArrayList<>(),
      new ArrayList<TransactionsEntity>(),
      new ArrayList<TransactionsEntity>()
      )
    );

    Optional<AccountEntity> accountB = Optional.of(
      new AccountEntity(
      UUID.randomUUID().toString(),
      "4321",
      1000000L,
      23L,
      new ArrayList<>(),
      new ArrayList<TransactionsEntity>(),
      new ArrayList<TransactionsEntity>()
      )
    );

    TransactionsEntity transactionEntity = new TransactionsEntity(
      "12312",
      accountA.get().getId(),
      accountB.get().getId(),
      500000L,
      LocalDate.now(),
      LocalTime.now(),
      "testmessage",
      accountA.get(),
      new AccountEntity()
    );

    when(accountRepository.findById(anyString()))
    .thenReturn(accountA)
    .thenReturn(accountB);
    when(transactionRepository.save(any()))
    .thenReturn(transactionEntity);

    

    TransactionRequest transactionRequest = new TransactionRequest (
      500000L,
      accountA.get().getId(),
      accountB.get().getId(),
      "Test Transaction"
    );


    TransactionsFullResponse results = transactionService.processNewTransaction(transactionRequest);
    TransactionsFullResponse expected = new TransactionsFullResponse(transactionEntity);
    assertThat(results).usingRecursiveComparison()
    .ignoringFields("transactionTime","transactionId", "id")
    .isEqualTo(expected);
  }

  @Test
  public void listAllTransactionForGivenCustomer() throws BankAccountNotFoundException{
    Optional<AccountEntity> sender = Optional.of(
      new AccountEntity(
      UUID.randomUUID().toString(),
      "1234",
      1000000L,
      23L,
      new ArrayList<>(),
      new ArrayList<TransactionsEntity>(),
      new ArrayList<TransactionsEntity>()
      )
    );

    Optional<AccountEntity> recipient = Optional.of(
      new AccountEntity(
      UUID.randomUUID().toString(),
      "4321",
      1000000L,
      23L,
      new ArrayList<>(),
      new ArrayList<TransactionsEntity>(),
      new ArrayList<TransactionsEntity>()
      )
    );

    TransactionsEntity firstTransactionEntity = new TransactionsEntity(
      "1",
      sender.get().getId(),
      recipient.get().getId(),
      500000L,
      LocalDate.now(),
      LocalTime.now(),
      "testmessage",
      sender.get(),
      new AccountEntity()
    );

    TransactionsEntity secTransactionEntity = new TransactionsEntity(
      "2",
      sender.get().getId(),
      recipient.get().getId(),
      500000L,
      LocalDate.now(),
      LocalTime.now(),
      "testmessage",
      sender.get(),
      new AccountEntity()
    );

    when(accountRepository.findById(anyString()))
    .thenReturn(sender)
    .thenReturn(recipient)
    .thenReturn(sender)
    .thenReturn(recipient)
    .thenReturn(sender);

    when(transactionRepository.save(any()))
    .thenReturn(firstTransactionEntity)
    .thenReturn(secTransactionEntity);

    TransactionRequest firstTransactionRequest = new TransactionRequest (
      500000L,
      sender.get().getId(),
      recipient.get().getId(),
      "Test Transaction first"
    );

    TransactionRequest secTransactionRequest = new TransactionRequest (
      500000L,
      sender.get().getId(),
      recipient.get().getId(),
      "Test Transaction second"
    );
    
    //when
    transactionService.processNewTransaction(firstTransactionRequest);
    transactionService.processNewTransaction(secTransactionRequest);
    AccountResponse result = new AccountResponse(accountRepository.findById(anyString()).get());
    //then
    assertThat(result.getTransactionIds()).hasSize(2);


  }

}
