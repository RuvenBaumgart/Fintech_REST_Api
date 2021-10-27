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

import de.kirschUndKern.testProjectJava.fintech.entities.AccountEntity;
import de.kirschUndKern.testProjectJava.fintech.entities.TransactionsEntity;
import de.kirschUndKern.testProjectJava.fintech.exceptions.BankAccountNotFoundException;
import de.kirschUndKern.testProjectJava.fintech.modell.TransactionRequest;
import de.kirschUndKern.testProjectJava.fintech.modell.TransactionsFullResponse;
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
      new ArrayList<>()
      )
    );

    Optional<AccountEntity> recipient = Optional.of(
      new AccountEntity(
      UUID.randomUUID().toString(),
      "4321",
      1000000L,
      23L,
      new ArrayList<>()
      )
    );

    TransactionsEntity transactionResponse = new TransactionsEntity(
      "12312",
      sender.get().getId(),
      recipient.get().getId(),
      500000L,
      LocalDate.now(),
      LocalTime.now(),
      "testmessage"
    );

    when(accountRepository.findByCustomerId(anyString())).thenReturn(sender);
    when(accountRepository.findById(anyString())).thenReturn(recipient);
    when(transactionRepository.save(any())).thenReturn(transactionResponse);
    

    TransactionRequest transactionRequest = new TransactionRequest (
      500000L,
      "1234123",
      "2345234",
      "Test Transaction"
    );


    TransactionsFullResponse results = transactionService.processNewTransaction("12312", transactionRequest);
    
    assertThat(results).usingRecursiveComparison().isEqualTo(new TransactionsFullResponse(transactionResponse));
  }

  @Test
  public void listAllTransactionForGivenCustomer(){

  }

}
