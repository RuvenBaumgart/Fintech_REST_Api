package de.kirschUndKern.testProjectJava.fintech.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

  @PostMapping ("/customers")
  public CustomerAndAddressResponse createCustomer(
    @RequestBody CustomerRequest customerRequest
  )throws WrongDateFormatException {
    return customerService.createCustomerWithAddress(customerRequest, customerRequest.getAddress());
  }

  @GetMapping("/customers/{secondname}")
  public List<CustomerAndAddressResponse> getAllCustomers(
    @PathVariable String secondname,
    @RequestParam(required = false) String sort
  ){
    return customerService.findAllCustomersByName(secondname, sort);
  }

}
