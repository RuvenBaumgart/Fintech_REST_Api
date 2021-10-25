package de.kirschUndKern.testProjectJava.fintech.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import de.kirschUndKern.testProjectJava.fintech.entities.AddressEntity;
import de.kirschUndKern.testProjectJava.fintech.entities.CustomerEntity;
import de.kirschUndKern.testProjectJava.fintech.exceptions.WrongDateFormatException;
import de.kirschUndKern.testProjectJava.fintech.modell.CustomerRequest;
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
}