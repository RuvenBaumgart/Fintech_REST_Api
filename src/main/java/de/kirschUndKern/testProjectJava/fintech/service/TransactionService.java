package de.kirschUndKern.testProjectJava.fintech.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;



import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import de.kirschUndKern.testProjectJava.fintech.entities.AccountEntity;
import de.kirschUndKern.testProjectJava.fintech.entities.TransactionsEntity;
import de.kirschUndKern.testProjectJava.fintech.exceptions.BankAccountNotFoundException;
import de.kirschUndKern.testProjectJava.fintech.modell.TransactionRequest;
import de.kirschUndKern.testProjectJava.fintech.modell.TransactionsFullResponse;
import de.kirschUndKern.testProjectJava.fintech.repositories.AccountRepository;

import de.kirschUndKern.testProjectJava.fintech.repositories.TransactionRepository;

@Service
public class TransactionService {
  
  public final AccountRepository accountRepository;
  public final TransactionRepository transactionRepository;
  

  public TransactionService(
    AccountRepository accountRepository,
    TransactionRepository transactionRepository
  ){
    this.accountRepository = accountRepository;
    this.transactionRepository = transactionRepository;
  }

  public List<TransactionsFullResponse> createNewTransactionForCustomer(String customerId, TransactionRequest transactionRequest) throws BankAccountNotFoundException {
    Optional<AccountEntity> sourceAccount = accountRepository.findByCustomerId(customerId);
    Optional<AccountEntity> destinationAccount = accountRepository.findById(transactionRequest.getDestinationAccoutnId());
  
    if(sourceAccount.isPresent() && destinationAccount.isPresent()){
      AccountEntity sender = sourceAccount.get();
      AccountEntity recipient = destinationAccount.get();
      
      TransactionsEntity newSourceTransaction = createTransaction(sender, recipient, customerId, transactionRequest);

      TransactionsEntity newDestinationTransaction = createTransaction(recipient, sender, customerId, transactionRequest);


      List<TransactionsEntity> transactions = transactionRepository.saveAll(Arrays.asList(newSourceTransaction, newDestinationTransaction));

      List<TransactionsFullResponse> transactionResponse = transactions.stream()
      .map(transaction -> new TransactionsFullResponse(transaction))
      .collect(Collectors.toList());

      return transactionResponse;

    } else {
      throw new BankAccountNotFoundException(
        "SourceAccount with id: " + customerId + " not found or DestinatiounAccount with id: " + transactionRequest.getDestinationAccoutnId() + "not found", HttpStatus.BAD_REQUEST);
    }
  }

  private TransactionsEntity createTransaction(AccountEntity source, AccountEntity destination, String requestinCustomerId, TransactionRequest transactionRequest){
    Long amount = transactionRequest.getAmountInCent();
    
    TransactionsEntity newTransaction = new TransactionsEntity(
      UUID.randomUUID().toString(),
      source.getId(),
      destination.getId(),
      requestinCustomerId == source.getCustomerId() ? amount * -1 : amount,
      LocalDate.now(),
      LocalTime.now(),
      transactionRequest.getMessage()
    );
    return newTransaction;
  }
 
}
