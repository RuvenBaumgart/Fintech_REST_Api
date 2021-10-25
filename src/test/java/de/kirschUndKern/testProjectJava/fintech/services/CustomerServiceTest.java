package de.kirschUndKern.testProjectJava.fintech.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.UUID;

import de.kirschUndKern.testProjectJava.fintech.entities.AddressEntity;
import de.kirschUndKern.testProjectJava.fintech.entities.CustomerEntity;
import de.kirschUndKern.testProjectJava.fintech.exceptions.WrongDateFormatException;
import de.kirschUndKern.testProjectJava.fintech.modell.AddressRequest;
import de.kirschUndKern.testProjectJava.fintech.modell.CustomerAddressResponse;
import de.kirschUndKern.testProjectJava.fintech.modell.CustomerRequest;
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
  public void returnSavedCustomerResponse() throws Exception{
  //given
  CustomerEntity customerEntity = new CustomerEntity(
    UUID.randomUUID().toString(),
    "Max",
    "Mustermann",
    "Mr.",
    CustomerService.convertToDate("12/04/1988"),
    2
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
    addressRequest.getZipCode()
    
  );

  when(customerRepository.save(any())).thenReturn(customerEntity);
  when(addressRepository.save(any())).thenReturn(addressEntity);



  CustomerAddressResponse customerAddressResponse = new CustomerAddressResponse (customerEntity, addressEntity);

  //when
  CustomerAddressResponse result = customerService.createCustomerWithAddress(customerRequest, addressRequest);

  //then
  /**
   * The address object are same based on the values bud somhow the object it self ist  * not the same
   */
  assertThat(result).usingRecursiveComparison().isEqualTo(customerAddressResponse);
  };
}
