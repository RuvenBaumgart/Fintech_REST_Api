package de.kirschUndKern.testProjectJava.fintech.services;

import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import de.kirschUndKern.testProjectJava.fintech.repositories.TransactionRepository;
import de.kirschUndKern.testProjectJava.fintech.service.TransactionService;

public class TransactionServiceTest {
  
  private TransactionService transactionService;
  private AccountRepository accountRepository;
  private TransactionRepository transactionRepository;

  @BeforeEach
  public void init(){
    accountRepository = Mockito.mock(AccountRepository.class);
    transactionRepository = Mockito.mock(TransactionRepository.class);
    transactionService = new TransactionService(
      accountRepository, 
      transactionRepository
    );
  }

  @Test
  public void isCreatingTwoComplimentaryTransactions() throws BankAccountNotFoundException{
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

    TransactionsEntity firstTransactionResponse = new TransactionsEntity(
      "12312",
      sender.get().getId(),
      recipient.get().getId(),
      500000L,
      LocalDate.now(),
      LocalTime.now(),
      "testmessage"
    );

    TransactionsEntity secTransactionResponse = new TransactionsEntity(
      "12312",
      recipient.get().getId(),
      sender.get().getId(),
      -500000L,
      LocalDate.now(),
      LocalTime.now(),
      "testmessage"
    );
    
    when(accountRepository.findByCustomerId(anyString())).thenReturn(sender);
    when(accountRepository.findById(anyString())).thenReturn(recipient);
    when(transactionRepository.saveAll(anyIterable())).thenReturn(Arrays.asList(firstTransactionResponse, secTransactionResponse));
    
    TransactionRequest transactionRequest = new TransactionRequest (
      500000L,
      "2345234",
      "Test Transaction"
    );


    List<TransactionsFullResponse> results = transactionService.createNewTransactionForCustomer("12312", transactionRequest);
    
    assertThat(results).usingRecursiveComparison().isEqualTo(Arrays.asList(new TransactionsFullResponse(firstTransactionResponse), new TransactionsFullResponse(secTransactionResponse)));
  }

}
