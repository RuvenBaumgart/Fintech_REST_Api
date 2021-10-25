package de.kirschUndKern.testProjectJava.fintech.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kirschUndKern.testProjectJava.fintech.service.CustomerService;

@RestController
public class CustomerController {
  
  private final CustomerService customerService;

  public CustomerController(
    CustomerService customerService
  ){
    this.customerService = customerService;
  }



}
