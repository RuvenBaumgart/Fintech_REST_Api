package de.kirschUndKern.testProjectJava.fintech.services;

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

import de.kirschUndKern.testProjectJava.fintech.dto.request.TransactionRequest;
import de.kirschUndKern.testProjectJava.fintech.dto.response.AccountResponse;
import de.kirschUndKern.testProjectJava.fintech.dto.response.TransactionsFullResponse;
import de.kirschUndKern.testProjectJava.fintech.entities.AccountEntity;
import de.kirschUndKern.testProjectJava.fintech.entities.TransactionsEntity;
import de.kirschUndKern.testProjectJava.fintech.exceptions.BankAccountNotFoundException;
import de.kirschUndKern.testProjectJava.fintech.repositories.AccountRepository;
import de.kirschUndKern.testProjectJava.fintech.repositories.CustomerRepository;
import de.kirschUndKern.testProjectJava.fintech.repositories.TransactionRepository;
import de.kirschUndKern.testProjectJava.fintech.service.TransactionService;

public class TransactionServiceTest {
  
  private TransactionService transactionService;
  private AccountRepository accountRepository;
  private TransactionRepository transactionRepository;
  private CustomerRepository customerRepository;

  @BeforeEach
  public void init(){
    accountRepository = Mockito.mock(AccountRepository.class);
    transactionRepository = Mockito.mock(TransactionRepository.class);
    customerRepository = Mockito.mock(CustomerRepository.class);
    transactionService = new TransactionService(
      accountRepository, 
      transactionRepository,
      customerRepository
    );
  }

  @Test
  public void isCreatingTransaction() throws BankAccountNotFoundException{
    Optional<AccountEntity> sender = Optional.of(
      new AccountEntity(
      UUID.randomUUID().toString(),
      "1234",
      1000000L,
      23L,
      new ArrayList<>(),
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
      new ArrayList<TransactionsEntity>()
      )
    );

    TransactionsEntity transactionEntity = new TransactionsEntity(
      "12312",
      sender.get().getId(),
      recipient.get().getId(),
      500000L,
      LocalDate.now(),
      LocalTime.now(),
      "testmessage",
      sender.get()
    );

    when(accountRepository.findById(anyString()))
    .thenReturn(sender)
    .thenReturn(recipient);
    when(transactionRepository.save(any())).thenReturn(transactionEntity);
    

    TransactionRequest transactionRequest = new TransactionRequest (
      500000L,
      sender.get().getId(),
      recipient.get().getId(),
      "Test Transaction"
    );


    TransactionsFullResponse results = transactionService.processNewTransaction(transactionRequest);
    
    assertThat(results).usingRecursiveComparison()
    .ignoringFields("transactionTime","id")
    .isEqualTo(new TransactionsFullResponse(transactionEntity));
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
      sender.get()
    );

    TransactionsEntity secTransactionEntity = new TransactionsEntity(
      "2",
      sender.get().getId(),
      recipient.get().getId(),
      500000L,
      LocalDate.now(),
      LocalTime.now(),
      "testmessage",
      sender.get()
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
    assertThat(result.getTransactions()).hasSize(2);


  }

}
