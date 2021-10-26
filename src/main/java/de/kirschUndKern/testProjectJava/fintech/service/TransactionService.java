package de.kirschUndKern.testProjectJava.fintech.service;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.Optional;
import java.util.UUID;




import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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

  public TransactionsFullResponse processNewTransaction(String customerId, TransactionRequest request) throws BankAccountNotFoundException {
    
    Optional<AccountEntity> source = accountRepository.findByCustomerId(customerId);
    Optional<AccountEntity> destination = accountRepository.findById(request.getDestinationAccoutnId());
  
    if(source.isPresent() && destination.isPresent()){
      TransactionsEntity savedTransaction = saveNewTransaction(source.get(), destination.get(), customerId, request);


      return new TransactionsFullResponse(savedTransaction);
    } else {
      throw new BankAccountNotFoundException(
        "SourceAccount with id: " 
        + customerId + " not found or DestinatiounAccount with id: " 
        + request.getDestinationAccoutnId() 
        + "not found", HttpStatus.BAD_REQUEST);
    }
  }
  
  private TransactionsEntity createTransaction(AccountEntity source, AccountEntity destination, String requestingCustomerId, TransactionRequest transactionRequest){
    Long amountInCent = transactionRequest.getAmountInCent();
    
    //The requesting Customer is always asking for sending money not receiving
    TransactionsEntity newTransaction = new TransactionsEntity(
      UUID.randomUUID().toString(),
      source.getId(),
      destination.getId(),
      amountInCent,
      LocalDate.now(),
      LocalTime.now(),
      transactionRequest.getMessage()
    );

    return newTransaction;
  }

  private TransactionsEntity saveNewTransaction (AccountEntity source, AccountEntity destination, String customerId, TransactionRequest request){
    return transactionRepository.save(createTransaction(source, destination, customerId, request));
  };
  
}
