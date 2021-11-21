package de.kirschUndKern.testProjectJava.fintech.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import de.kirschUndKern.testProjectJava.fintech.dto.request.CreditRequest;
import de.kirschUndKern.testProjectJava.fintech.dto.request.CreditUpdateRequest;
import de.kirschUndKern.testProjectJava.fintech.dto.response.CreditResponse;
import de.kirschUndKern.testProjectJava.fintech.exceptions.CustomerNotFoundException;
import de.kirschUndKern.testProjectJava.fintech.service.CreditService;

@RestController
public class CreditController {
  
  private final CreditService creditService;
  
  public CreditController (
    CreditService creditService
  ){
    this.creditService = creditService;
  }

  @PostMapping("/credits/{id}")
  public CreditResponse createCreditForCustomer(
    @PathVariable(name ="id") String customerId,
    @RequestBody CreditRequest newCreditRequest
  )throws CustomerNotFoundException{
    return creditService.createCreditForCustomer(customerId, newCreditRequest);
  }

  @GetMapping("/credits/{id}")
  public List<CreditResponse> getAllCredits(
    @PathVariable (name="id") String customerId
  ){
    return creditService.getAllCreditsById(customerId);
  }

  @PutMapping("/credits/{id}")
  public CreditResponse payoffCredit(
    @PathVariable String id,
    @RequestBody CreditUpdateRequest creditUpdateRequest
  ) throws Exception{
    return creditService.createUpdateCredit(id, creditUpdateRequest);
  }

  @GetMapping("/credits")
  public List<CreditResponse> getAllCredits(){
    return creditService.getAllCredits();
  }
}
