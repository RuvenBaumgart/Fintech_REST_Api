package de.kirschUndKern.testProjectJava.fintech.service;

import de.kirschUndKern.testProjectJava.fintech.entities.AccountEntity;
import de.kirschUndKern.testProjectJava.fintech.entities.CustomerEntity;
import de.kirschUndKern.testProjectJava.fintech.exceptions.BankAccountNotFoundException;
import de.kirschUndKern.testProjectJava.fintech.exceptions.CustomerNotFoundException;
import de.kirschUndKern.testProjectJava.fintech.modell.AccountResponse;
import de.kirschUndKern.testProjectJava.fintech.modell.TransactionsFullResponse;
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
        new ArrayList<String>()
      );
      return new AccountResponse(accountRepository.save(newAccount));
    } else {
      throw new CustomerNotFoundException("Customer with id: " + customerId + " not fount", HttpStatus.BAD_REQUEST);
    }
  };

  public AccountResponse updateAccountBalance(String accountId, Long amount, String transactionId) throws BankAccountNotFoundException
    {
      Optional<AccountEntity> account = accountRepository.findById(accountId);
      
      if(account.isPresent()){
        AccountEntity newAccount = new AccountEntity(account.get(), amount, appendTransactionId(account.get().getTransactionIds(), transactionId));
        accountRepository.save(newAccount);
        return new AccountResponse(newAccount);
      } else {
        throw new BankAccountNotFoundException("The Account with the id " + accountId + "is not existing", HttpStatus.BAD_REQUEST);
      }
    }

  private List<String> appendTransactionId(List<String> oldTransactionIds, String newTransactionId)
  {
    List<String> newTransactionIds = new ArrayList<>();
    newTransactionIds.addAll(oldTransactionIds);
    newTransactionIds.add(newTransactionId);
    return newTransactionIds;
  }

  public void processNewTransaction(TransactionsFullResponse newTransaction) throws BankAccountNotFoundException {
    updateAccountBalance(newTransaction.getSourceAccountId(), newTransaction.getAmountInCent() * -1, newTransaction.getId());
    updateAccountBalance(newTransaction.getDestinationAccountId(), newTransaction.getAmountInCent(), newTransaction.getId());
  }

  public List<AccountResponse> getAccountsBy(String customerId){
    List<AccountEntity> accounts;
    if(customerId == "Institution"){
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
      sumOfBalanceInCent += account.getbalanceInCent(); 
    }
    return sumOfBalanceInCent;
  };
}
