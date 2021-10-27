package de.kirschUndKern.testProjectJava.fintech.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import de.kirschUndKern.testProjectJava.fintech.entities.AccountEntity;
import de.kirschUndKern.testProjectJava.fintech.entities.CustomerEntity;
import de.kirschUndKern.testProjectJava.fintech.entities.TransactionsEntity;
import de.kirschUndKern.testProjectJava.fintech.exceptions.BankAccountNotFoundException;
import de.kirschUndKern.testProjectJava.fintech.exceptions.TransactionNotFoundException;
import de.kirschUndKern.testProjectJava.fintech.modell.TransactionRequest;
import de.kirschUndKern.testProjectJava.fintech.modell.TransactionsForCustomerResponse;
import de.kirschUndKern.testProjectJava.fintech.modell.TransactionsFullResponse;
import de.kirschUndKern.testProjectJava.fintech.repositories.AccountRepository;
import de.kirschUndKern.testProjectJava.fintech.repositories.CustomerRepository;
import de.kirschUndKern.testProjectJava.fintech.repositories.TransactionRepository;

@Service
public class TransactionService {
  
  public final AccountRepository accountRepository;
  public final TransactionRepository transactionRepository;
  public final CustomerRepository customerRepository;
  

  public TransactionService(
    AccountRepository accountRepository,
    TransactionRepository transactionRepository,
    CustomerRepository customerRepository
  ){
    this.accountRepository = accountRepository;
    this.transactionRepository = transactionRepository;
    this.customerRepository = customerRepository;
  }

  public TransactionsFullResponse processNewTransaction(String customerId, TransactionRequest request) throws BankAccountNotFoundException {
    
    Optional<AccountEntity> source = accountRepository.findById(request.getSourceAccountId());
    Optional<AccountEntity> destination = accountRepository.findById(request.getDestinationAccoutnId());
  
    if(source.isPresent() && destination.isPresent()){
      
      //Check needs to be made if initiating customer is owner of the accounts
      TransactionsEntity savedTransaction = saveNewTransaction(
        source.get().getId(), 
        destination.get().getId(),
        request.getAmountInCent(), 
        request.getMessage()
      );


      return new TransactionsFullResponse(savedTransaction);
    } else {
      throw new BankAccountNotFoundException(
        "SourceAccount with id: " 
        + customerId + " not found or DestinatiounAccount with id: " 
        + request.getDestinationAccoutnId() 
        + "not found", HttpStatus.BAD_REQUEST);
    }
  }
  
  public TransactionsEntity createTransaction(String sourceId, String destinationId, Long amountInCent, String message){
  
    //The requesting Customer is always asking for sending money not receiving
    TransactionsEntity newTransaction = new TransactionsEntity(
      UUID.randomUUID().toString(),
      sourceId,
      destinationId,
      amountInCent,
      LocalDate.now(),
      LocalTime.now(),
      message
    );
    return newTransaction;
  }

  public TransactionsEntity saveNewTransaction (String sourceId, String destinationId, Long amountInCent, String message){
    return transactionRepository.save(createTransaction(sourceId, destinationId, amountInCent, message)
    );
  }

  public List<TransactionsFullResponse> getAllTransactionsBy(String date) {
    LocalDate localdate = LocalDate.parse(date);
    List<TransactionsEntity> transactions = transactionRepository.findAllByDate(localdate);
    
    List<TransactionsFullResponse> transactionsResponse = transactions.stream()
    .map(transactionEntity -> new TransactionsFullResponse(transactionEntity)).
    collect(Collectors.toList());
    return transactionsResponse;

  }

  public List<TransactionsForCustomerResponse> getAllTransactions(String customerId, Integer pageNo, Integer pageSize, String sortBy) throws TransactionNotFoundException {
    Pageable page = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
    Page<TransactionsEntity> pagedResult = transactionRepository.findAll(page);

    if(pagedResult.hasContent()){
      List<TransactionsEntity> transactionsPaged = pagedResult.getContent();

      List<TransactionsForCustomerResponse> transactionResponsePaged = buildResults(transactionsPaged);

      return transactionResponsePaged;
    } else {
      throw new TransactionNotFoundException("No Transactions for the given parameters found", HttpStatus.BAD_REQUEST);
    }
  }

  private List<TransactionsForCustomerResponse> buildResults(List<TransactionsEntity> transactionsPaged) {
    
    List<CustomerEntity> customers = customerRepository.findAll();
    List<AccountEntity> accounts = accountRepository.findAll();
    List<TransactionsForCustomerResponse> transactionsResponse = new ArrayList<>();

    for(TransactionsEntity transaction : transactionsPaged){
      String sourceAccoundId = transaction.getSourceAccountId();
      String destinationAccountId = transaction.getDestinationAccountId();

      Optional<AccountEntity> sourceAccount = accounts.stream()
      .filter(account -> account.getCustomerId().equals(sourceAccoundId))
      .findFirst();

      Optional<AccountEntity> destinationAccount = accounts.stream()
      .filter(account -> account.getCustomerId().equals(destinationAccountId))
      .findFirst();

      if(sourceAccount.isPresent() && destinationAccount.isPresent()){
        Optional<CustomerEntity> sourceCustomer = customers.stream()
        .filter(customer -> customer.getId().equals(sourceAccount.get().getCustomerId()))
        .findFirst();

        Optional<CustomerEntity> destinationCustomer = customers.stream()
        .filter(customer -> customer.getId().equals(destinationAccount.get().getCustomerId()))
        .findFirst();

        if(sourceCustomer.isPresent() && destinationCustomer.isPresent()){
          transactionsResponse.add(new TransactionsForCustomerResponse(transaction, sourceCustomer.get(), destinationCustomer.get()));
        }
      }
    }
    return transactionsResponse;
  }
}
