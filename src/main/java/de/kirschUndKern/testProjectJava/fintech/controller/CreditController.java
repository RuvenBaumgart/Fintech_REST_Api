package de.kirschUndKern.testProjectJava.fintech.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.kirschUndKern.testProjectJava.fintech.exceptions.CustomerNotFoundException;
import de.kirschUndKern.testProjectJava.fintech.modell.CreditRequest;
import de.kirschUndKern.testProjectJava.fintech.modell.CreditResponse;
import de.kirschUndKern.testProjectJava.fintech.service.CreditService;

@RestController
public class CreditController {
  
  private final CreditService creditService;
  
  public CreditController (
    CreditService creditService
  ){
    this.creditService = creditService;
  }

  @PostMapping("/credits")
  public CreditResponse createCreditForCustomer(
    @PathVariable(name ="id") String customerId,
    @RequestBody CreditRequest newCreditRequest
  )throws CustomerNotFoundException{
    return creditService.createCreditForCustomer(customerId, newCreditRequest);
  }
}
