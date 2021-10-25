package de.kirschUndKern.testProjectJava.fintech.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import de.kirschUndKern.testProjectJava.fintech.exceptions.WrongDateFormatException;
import de.kirschUndKern.testProjectJava.fintech.modell.CustomerAndAddressResponse;
import de.kirschUndKern.testProjectJava.fintech.modell.CustomerRequest;
import de.kirschUndKern.testProjectJava.fintech.service.CustomerService;

@RestController
public class CustomerController {
  
  private final CustomerService customerService;

  public CustomerController(
    CustomerService customerService
  ){
    this.customerService = customerService;
  }

  @PostMapping
  public CustomerAndAddressResponse createCustomer(
    @RequestBody CustomerRequest customerRequest
  )throws WrongDateFormatException {
    return customerService.createCustomerWithAddress(customerRequest, customerRequest.getAddress());
  }


}
