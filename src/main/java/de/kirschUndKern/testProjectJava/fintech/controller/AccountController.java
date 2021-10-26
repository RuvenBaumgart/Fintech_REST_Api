package de.kirschUndKern.testProjectJava.fintech.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kirschUndKern.testProjectJava.fintech.exceptions.CustomerNotFoundException;
import de.kirschUndKern.testProjectJava.fintech.modell.AccountResponse;
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
}
