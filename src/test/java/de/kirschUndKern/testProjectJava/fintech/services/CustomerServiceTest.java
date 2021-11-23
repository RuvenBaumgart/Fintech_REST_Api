package de.kirschUndKern.testProjectJava.fintech.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import de.kirschUndKern.testProjectJava.fintech.dto.request.AddressRequest;
import de.kirschUndKern.testProjectJava.fintech.dto.request.CustomerRequest;
import de.kirschUndKern.testProjectJava.fintech.dto.response.CustomerAndAddressResponse;
import de.kirschUndKern.testProjectJava.fintech.dto.response.CustomerResponse;
import de.kirschUndKern.testProjectJava.fintech.entities.AddressEntity;
import de.kirschUndKern.testProjectJava.fintech.entities.CustomerEntity;
import de.kirschUndKern.testProjectJava.fintech.exceptions.WrongDateFormatException;
import de.kirschUndKern.testProjectJava.fintech.repositories.AddressRepository;
import de.kirschUndKern.testProjectJava.fintech.repositories.CustomerRepository;
import de.kirschUndKern.testProjectJava.fintech.service.CustomerService;

public class CustomerServiceTest {

  //Context
  CustomerService customerService;
  CustomerRepository customerRepository;
  AddressRepository addressRepository;

  @BeforeEach
  public void init(){
    customerRepository = Mockito.mock(CustomerRepository.class);
    addressRepository = Mockito.mock(AddressRepository.class);
  
    customerService = new CustomerService(
      customerRepository,
      addressRepository
    );
  };  
  @Test 
   public void isDateStringCorrectConvertetToDateObject() throws WrongDateFormatException{
    //given
    String date = "22/05/2020";
    LocalDate expectedDate = LocalDate.of(2020,05,22);
    //when
    LocalDate convertedDate = CustomerService.convertToDate(date);
    //then
    assertThat(convertedDate).isEqualTo(expectedDate);
  }

  @Test
    public void isInvalidDateThrowingCorrectException(){
    //given
    String date = "22.05.2020";
    assertThatThrownBy(() -> CustomerService.convertToDate(date)).isInstanceOf(WrongDateFormatException.class);
  }

  @Test
    public void returnSavedCustomerResponse() throws WrongDateFormatException{
  //given
  CustomerEntity customerEntity = new CustomerEntity(
    UUID.randomUUID().toString(),
    "Max",
    "Mustermann",
    "Mr.",
    CustomerService.convertToDate("12/04/1988"),
    2,
    new AddressEntity()
  );

  AddressRequest addressRequest = new AddressRequest("Musterhausen" , "Musterstraße","NRW", "59821", "Germany");
  CustomerRequest customerRequest = new CustomerRequest(
    "Max",
    "Mustermann",
    "Mr.",
    "12/04/1988",
    addressRequest
  );

  AddressEntity addressEntity = new AddressEntity(
    UUID.randomUUID().toString(),
    customerEntity.getId(),
    addressRequest.getCity(),
    addressRequest.getStreet(),
    addressRequest.getProvince(),
    addressRequest.getCountry(),
    addressRequest.getZipCode(),
    customerEntity
  );

  when(customerRepository.save(any())).thenReturn(customerEntity);
  when(addressRepository.save(any())).thenReturn(addressEntity);
  CustomerAndAddressResponse customerAddressResponse = new CustomerAndAddressResponse (customerEntity, addressEntity);

  //when
  CustomerAndAddressResponse result = customerService.createCustomerWithAddress(customerRequest, addressRequest);
  assertThat(result).usingRecursiveComparison().isEqualTo(customerAddressResponse);
  };

  @Test
  public void findAllCustomers() throws WrongDateFormatException{
    CustomerEntity customerEntity = new CustomerEntity(
    UUID.randomUUID().toString(),
    "Max",
    "Mustermann",
    "Mr.",
    CustomerService.convertToDate("12/04/1988"),
    2,
    new AddressEntity()
  );

  CustomerResponse customerResponse = new CustomerResponse(customerEntity);
  
  when(customerRepository.findAll()).thenReturn(Arrays.asList(customerEntity));

  List<CustomerEntity> results = customerRepository.findAll();
  List<CustomerResponse> resultsResponse = results.stream()
  .map(entity -> new CustomerResponse(entity))
  .collect(Collectors.toList());
  
  assertThat(resultsResponse).usingRecursiveComparison().isEqualTo(Arrays.asList(customerResponse));
  }

  
}
