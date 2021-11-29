package de.demo.testProjectJava.fintech.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.demo.testProjectJava.fintech.dto.request.CustomerRequest;
import de.demo.testProjectJava.fintech.dto.response.CustomerAndAddressResponse;
import de.demo.testProjectJava.fintech.dto.response.CustomerResponse;
import de.demo.testProjectJava.fintech.exceptions.WrongDateFormatException;
import de.demo.testProjectJava.fintech.service.CustomerService;

import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {
  
  private final CustomerService customerService;

  public CustomerController(
    CustomerService customerService
  ){
    this.customerService = customerService;
  }


  @GetMapping("/customers/{secondname}")
  public List<CustomerResponse> getAllCustomersBySeondname(
    @PathVariable String secondname,
    @RequestParam(required = false) String sort
  ){
    return customerService.findAllCustomersByName(secondname, sort);
  }

  @GetMapping("/customers/grouped")
  public Map<Integer, List<CustomerResponse>> getAllCustomersGroupedBy(
    @RequestParam(name = "grouped") String groupedBy
  ){
    return customerService.findAllCustomersGroupedBy(groupedBy);
  }

  @GetMapping("/customers")
  public List<CustomerResponse> getAllCustomers(){
    return customerService.findAllCustomers();
  }

  @PostMapping ("/customers")
  public CustomerAndAddressResponse createCustomer(
    @RequestBody CustomerRequest customerRequest
  )throws WrongDateFormatException {
    return customerService.createCustomerWithAddress(customerRequest, customerRequest.getAddress());
  }
  

}
