package de.demo.testProjectJava.fintech.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import de.demo.testProjectJava.fintech.dto.request.AddressRequest;
import de.demo.testProjectJava.fintech.dto.request.CustomerRequest;
import de.demo.testProjectJava.fintech.dto.response.CustomerAndAddressResponse;
import de.demo.testProjectJava.fintech.dto.response.CustomerResponse;
import de.demo.testProjectJava.fintech.entities.AddressEntity;
import de.demo.testProjectJava.fintech.entities.CustomerEntity;
import de.demo.testProjectJava.fintech.exceptions.WrongDateFormatException;
import de.demo.testProjectJava.fintech.repositories.AddressRepository;
import de.demo.testProjectJava.fintech.repositories.CustomerRepository;

import java.util.List;
import java.util.Map;

@Service
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final AddressRepository addressRepository;

  public CustomerService(
    CustomerRepository customerRepository,
    AddressRepository addressRepository){
    this.customerRepository = customerRepository;
    this.addressRepository = addressRepository;
  }

  public static LocalDate convertToDate(String date) throws WrongDateFormatException{
    try{
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate convertedDate = LocalDate.parse(date,formatter);
    return convertedDate;
    } catch(Exception e){
      throw new WrongDateFormatException("The Data of Birth needs to be in the format dd/MM/yyyy", HttpStatus.BAD_REQUEST);
    }
  }
  
  public CustomerAndAddressResponse createCustomerWithAddress(CustomerRequest customerRequest, AddressRequest addressRequest) throws WrongDateFormatException{
    CustomerEntity newCustomer = new CustomerEntity(customerRequest);
    AddressEntity newAddress = new AddressEntity(
      UUID.randomUUID().toString(),
      newCustomer.getId(),
      addressRequest.getCity(),
      addressRequest.getStreet(),
      addressRequest.getProvince(),
      addressRequest.getZipCode(),
      addressRequest.getCountry(),
      newCustomer
    );
    newCustomer.setAddress(newAddress);
    return new CustomerAndAddressResponse(customerRepository.save(newCustomer), addressRepository.save(newAddress));
  }

  public List<CustomerResponse> findAllCustomersByName(String secondname, String filtertag) {
    List<CustomerEntity> customers;
    if(filtertag =="true"){
       customers = customerRepository.findAllByName(secondname);
    } else {
      customers = customerRepository.findAllByNameOrdered(secondname);
    }
    
    return customers.stream()
    .map(customerEntity -> new CustomerResponse(customerEntity))
    .collect(Collectors.toList());
  }

  public List<CustomerResponse> findAllCustomers() {
    List<CustomerEntity> customers = customerRepository.findAll();
    List<CustomerResponse> customerResponse = customers.stream()
    .map(customerEntity -> new CustomerResponse(customerEntity))
    .collect(Collectors.toList());
    return customerResponse;
  }

  public Map<Integer, List<CustomerResponse>> findAllCustomersGroupedBy(String groupedBy) {
    List<CustomerEntity> customers = customerRepository.findAll();
    List<CustomerResponse> customersResponse = customers.stream()
    .map(customerEntity -> new CustomerResponse(customerEntity))
    .collect(Collectors.toList());
    
    return customersResponse.stream()
    .collect(Collectors.groupingBy(customerResponse -> customerResponse.getRating()));
  }
}