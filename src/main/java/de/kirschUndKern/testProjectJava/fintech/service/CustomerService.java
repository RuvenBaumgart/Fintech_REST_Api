package de.kirschUndKern.testProjectJava.fintech.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import de.kirschUndKern.testProjectJava.fintech.entities.AddressEntity;
import de.kirschUndKern.testProjectJava.fintech.entities.CustomerEntity;
import de.kirschUndKern.testProjectJava.fintech.exceptions.WrongDateFormatException;
import de.kirschUndKern.testProjectJava.fintech.modell.CustomerRequest;
import de.kirschUndKern.testProjectJava.fintech.modell.CustomerResponse;
import de.kirschUndKern.testProjectJava.fintech.repositories.AddressRepository;
import de.kirschUndKern.testProjectJava.fintech.repositories.CustomerRepository;
import de.kirschUndKern.testProjectJava.fintech.modell.AddressRequest;
import de.kirschUndKern.testProjectJava.fintech.modell.CustomerAndAddressResponse;

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
      addressRequest.getCountry()
    );
    return new CustomerAndAddressResponse(customerRepository.save(newCustomer), addressRepository.save(newAddress));
  }

  public List<CustomerAndAddressResponse> findAllCustomersByName(String secondname, String filtertag) {
    List<CustomerEntity> customers;
    if(filtertag =="true"){
       customers = customerRepository.findAllByName(secondname);
    } else {
      customers = customerRepository.findAllByNameOrdered(secondname);
    }
    List<AddressEntity> addresses = addressRepository.findAll();
    List<CustomerAndAddressResponse> customerAndAddressResponse = new ArrayList<>();

    //Performance must be considered in the future O(n*log(n))
    for(CustomerEntity customer : customers){
      for(AddressEntity address : addresses){
        if(address.getCustomerId() == customer.getId())
          customerAndAddressResponse.add(new CustomerAndAddressResponse(customer, address));
      }
    }

    return customerAndAddressResponse;
  }

  public List<CustomerResponse> findAllCustomers() {
    List<CustomerEntity> customers = customerRepository.findAll();
    List<CustomerResponse> customerResponse = customers.stream()
    .map(customerEntity -> new CustomerResponse(customerEntity))
    .collect(Collectors.toList());
    return customerResponse;
  }
}