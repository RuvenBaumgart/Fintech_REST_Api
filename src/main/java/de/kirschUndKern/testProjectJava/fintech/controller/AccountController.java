package de.kirschUndKern.testProjectJava.fintech.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kirschUndKern.testProjectJava.fintech.exceptions.BankAccountNotFoundException;
import de.kirschUndKern.testProjectJava.fintech.exceptions.CustomerNotFoundException;
import de.kirschUndKern.testProjectJava.fintech.modell.response.AccountResponse;
import de.kirschUndKern.testProjectJava.fintech.service.AccountService;

@RestController
public class AccountController {
  
  private final AccountService accountService;

  public AccountController(
    AccountService accountService
  ){
    this.accountService = accountService;
  }

  @PostMapping("/accounts/{id}")
  public AccountResponse createNewAccountForId(
    @PathVariable(name ="id") String customerId
  ) throws CustomerNotFoundException{
    return accountService.saveNewAccountForCustomer(customerId);
  };

  @GetMapping("/accounts/{id}")
  public List<AccountResponse> getAllAccounts(
    @PathVariable (name ="id") String customerId
  )throws BankAccountNotFoundException{
    return accountService.getAccountsBy(customerId);
  };

  @GetMapping("/accounts/{id}/balance")
  public Long getSumBalanceOfAllAccountsForUser(
    @PathVariable (name = "id") String customerId
  ){
    return accountService.getAccountsAndCalculateSum(customerId);
  };

  @GetMapping("/accounts/balance")
  public Long getSumBalanceOfAllAccounts(){
    return accountService.getAccountsAndCalculateSum("Institution");
  }

}
