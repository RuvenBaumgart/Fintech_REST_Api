package de.kirschUndKern.testProjectJava.fintech.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import de.kirschUndKern.testProjectJava.fintech.dto.request.TransactionRequest;
import de.kirschUndKern.testProjectJava.fintech.dto.response.PageResponse;
import de.kirschUndKern.testProjectJava.fintech.dto.response.TransactionsForCustomerResponse;
import de.kirschUndKern.testProjectJava.fintech.dto.response.TransactionsFullResponse;
import de.kirschUndKern.testProjectJava.fintech.entities.AccountEntity;
import de.kirschUndKern.testProjectJava.fintech.entities.CustomerEntity;
import de.kirschUndKern.testProjectJava.fintech.entities.TransactionsEntity;
import de.kirschUndKern.testProjectJava.fintech.exceptions.BankAccountNotFoundException;
import de.kirschUndKern.testProjectJava.fintech.exceptions.CustomerNotFoundException;
import de.kirschUndKern.testProjectJava.fintech.repositories.AccountRepository;
import de.kirschUndKern.testProjectJava.fintech.repositories.CustomerRepository;
import de.kirschUndKern.testProjectJava.fintech.repositories.TransactionRepository;

@Service
public class TransactionService {

  private static Map<String, Comparator<TransactionsForCustomerResponse>> sortby = new HashMap<>();

  
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
    initSortbyMap();
  }

  public TransactionsFullResponse processNewTransaction(String customerId, TransactionRequest request) throws BankAccountNotFoundException {
    
    Optional<AccountEntity> source = accountRepository.findById(request.getSourceAccountId());
    Optional<AccountEntity> destination = accountRepository.findById(request.getDestinationAccountId());
  
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
        + request.getDestinationAccountId() 
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

  public List<TransactionsForCustomerResponse> getAllTransactionsForCustomer(String customerId) throws Exception {
    
    Optional<CustomerEntity> customer = customerRepository.findById(customerId);
    if(customer.isEmpty())
      throw new CustomerNotFoundException("Customer with id: " + customerId + "not found", HttpStatus.BAD_REQUEST);
   
      Optional<List<AccountEntity>> accounts = accountRepository.findAllByCustomerId(customerId);
    if(accounts.isEmpty())
      throw new BankAccountNotFoundException(
        "No Accounts for the given customer " 
        + customer.get().getFirstname() + " " 
        + customer.get().getSecondname() + "found" , 
        HttpStatus.BAD_REQUEST);
    
    List<String> transactions = accounts.get().stream()
    .flatMap(account -> account.getTransactionIds().stream())
    .collect(Collectors.toList());
      
    return createTransactionsList(transactions);
  };

  List<TransactionsForCustomerResponse> createTransactionsList(List<String> transactionIds){
    List<TransactionsEntity> transactions = transactionRepository.findAllById(transactionIds);
    List<TransactionsForCustomerResponse> result = new ArrayList<>();
    for(TransactionsEntity transaction : transactions){
      result.add(new TransactionsForCustomerResponse(
        transaction, 
        getCustomer(transaction.getSourceAccountId()),
        getCustomer(transaction.getDestinationAccoutnId())
        )
      );
    }
    return result;
  }

  
  private CustomerEntity getCustomer(String sourceAccountId) {
    AccountEntity account = accountRepository.getById(sourceAccountId);
    CustomerEntity customer = customerRepository.getById(account.getCustomerId());
    return customer;
  }

  public PageResponse<TransactionsForCustomerResponse> getPagedTransactions(String customerId, Integer pageNo, Integer pageSize, String sortBy) throws Exception {
    List<TransactionsForCustomerResponse> allTransactions =  getAllTransactionsForCustomer(customerId);
    return getPage(pageNo, pageSize, allTransactions, sortBy);
  }

  private PageResponse<TransactionsForCustomerResponse> getPage(Integer pageNo, Integer pageSize, List<TransactionsForCustomerResponse> transactions, String sortBy ){
    Integer skipPreviousTransactions = (pageNo - 1) * pageSize;
    List<TransactionsForCustomerResponse> transactionsSorted = transactions.stream()
    .sorted(getComparator(sortBy))
    .collect(Collectors.toList());

    List<TransactionsForCustomerResponse> transactionsPage = transactionsSorted
    .stream()
    .skip(skipPreviousTransactions)
    .limit(pageSize)
    .collect(Collectors.toList());
    PageResponse<TransactionsForCustomerResponse> page = new PageResponse<>(pageNo, transactionsPage.size(), transactions.size(), transactionsPage);
    return page;
  }

  private Comparator<TransactionsForCustomerResponse> getComparator(String sortingFunc){
    return sortby.get(sortingFunc);
  };

  private void initSortbyMap(){
    sortby.put("sender_firstname", Comparator.comparing(TransactionsForCustomerResponse::getCustomerFirstname));
    sortby.put("sender_secondname", Comparator.comparing(TransactionsForCustomerResponse::getCustomerSecondname));
    sortby.put("recepient_firstname", Comparator.comparing(TransactionsForCustomerResponse::getCustomerFirstnameDestination));
    sortby.put("recepient_secondname", Comparator.comparing(TransactionsForCustomerResponse::getCustomerSecondnameDesitnation));
  }
}
