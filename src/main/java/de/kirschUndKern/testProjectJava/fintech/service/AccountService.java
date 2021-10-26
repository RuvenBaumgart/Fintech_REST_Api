package de.kirschUndKern.testProjectJava.fintech.service;

import de.kirschUndKern.testProjectJava.fintech.entities.AccountEntity;
import de.kirschUndKern.testProjectJava.fintech.entities.CustomerEntity;
import de.kirschUndKern.testProjectJava.fintech.exceptions.BankAccountNotFoundException;
import de.kirschUndKern.testProjectJava.fintech.exceptions.CustomerNotFoundException;
import de.kirschUndKern.testProjectJava.fintech.modell.AccountResponse;
import de.kirschUndKern.testProjectJava.fintech.repositories.AccountRepository;
import de.kirschUndKern.testProjectJava.fintech.repositories.CustomerRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

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

  public AccountResponse updateAccountBalance(String accountId , Long amount, String transactionId) throws BankAccountNotFoundException{
    Optional<AccountEntity> account = accountRepository.findById(accountId);
    if(account.isPresent()){
      AccountEntity newAccount = new AccountEntity(account.get(), amount, transactionId);
      accountRepository.save(newAccount);
      return new AccountResponse(newAccount);
    } else {
      throw new BankAccountNotFoundException("The Account with the id " + accountId + "is not existing", HttpStatus.BAD_REQUEST);
    }
  }
}
