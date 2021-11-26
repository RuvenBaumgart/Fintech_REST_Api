package de.kirschUndKern.testProjectJava.fintech.service;

import de.kirschUndKern.testProjectJava.fintech.dto.response.AccountResponse;
import de.kirschUndKern.testProjectJava.fintech.entities.AccountEntity;
import de.kirschUndKern.testProjectJava.fintech.entities.CustomerEntity;
import de.kirschUndKern.testProjectJava.fintech.entities.TransactionsEntity;
import de.kirschUndKern.testProjectJava.fintech.exceptions.BankAccountNotFoundException;
import de.kirschUndKern.testProjectJava.fintech.exceptions.CustomerNotFoundException;
import de.kirschUndKern.testProjectJava.fintech.repositories.AccountRepository;
import de.kirschUndKern.testProjectJava.fintech.repositories.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
  public final AccountRepository accountRepository;
  public final CustomerRepository customerRepository;

  public AccountService(
    AccountRepository accountRepository,
    CustomerRepository customerRepository
  ){
    this.accountRepository = accountRepository;
    this.customerRepository = customerRepository;
  };

  public AccountResponse saveNewAccountForCustomer(String customerId) throws CustomerNotFoundException {
    Optional<CustomerEntity> customer = customerRepository.findById(customerId);
    if(customer.isPresent()){
      AccountEntity newAccount = new AccountEntity(
        UUID.randomUUID().toString(),
        customer.get().getId(),
        0L,
        0L,
        new ArrayList<String>(),
        new ArrayList<TransactionsEntity>(),
        new ArrayList<TransactionsEntity>()
      );
      return new AccountResponse(accountRepository.save(newAccount));
    } else {
      throw new CustomerNotFoundException("Customer with id: " + customerId + " not fount", HttpStatus.BAD_REQUEST);
    }
  };

  public void updateAccounts(TransactionsEntity transaction) throws BankAccountNotFoundException
    {
     AccountEntity sourceAccount = transaction.getSourceAccount();
     AccountEntity destinationAccount = transaction.getDestinationAccount();
     
    sourceAccount.setTransactionIds(appendTransaction(sourceAccount.getTransactionIds(), transaction.getId()));
    destinationAccount.setTransactionIds(appendTransaction(destinationAccount.getTransactionIds(), transaction.getId()));

    sourceAccount.setDebitTransactions(appendTransaction(sourceAccount.getDebitTransactions(), transaction));
    destinationAccount.setCreditTransactions(appendTransaction(destinationAccount.getCreditTransactions(), transaction));

    sourceAccount.setBalance(updateAccountBalance(sourceAccount, transaction.getAmountInCent() * -1));
    destinationAccount.setBalance(updateAccountBalance(destinationAccount, transaction.getAmountInCent()));

    accountRepository.save(sourceAccount);
    accountRepository.save(destinationAccount);

    }


  public Long updateAccountBalance(AccountEntity account, Long amount){
    Long currentBalance = account.getBalanceInCent();
    Long newBalance = currentBalance + amount;
    return newBalance;
  }


  public static <T> List<T> appendTransaction(List<T> oldTransactionIds, T newTransactionId)
  {
    List<T> newTransactionIds = new ArrayList<>();
    newTransactionIds.addAll(oldTransactionIds);
    newTransactionIds.add(newTransactionId);
    return newTransactionIds;
  }


  public List<AccountResponse> getAccountsBy(String customerId){
    List<AccountEntity> accounts;
    if(customerId.equals("Institution")){
      accounts = accountRepository.findAll();
    } else {
      accounts = accountRepository.findAllByCustomerId(customerId).get();
    }
    
    List<AccountResponse> accountResponse = accounts.stream()
    .map(accountEntity -> new AccountResponse(accountEntity))
    .collect(Collectors.toList());

    return accountResponse;
  }

  public Long getAccountsAndCalculateSum(String customerId){

    List<AccountResponse> accounts = getAccountsBy(customerId);
    Long sumOfBalanceInCent = 0L;
    for(AccountResponse account : accounts){
      sumOfBalanceInCent += account.getBalanceInCent(); 
    }
    return sumOfBalanceInCent;
  };
}
