package de.demo.testProjectJava.fintech.repositories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import de.demo.testProjectJava.fintech.entities.AddressEntity;
import de.demo.testProjectJava.fintech.entities.CustomerEntity;
import de.demo.testProjectJava.fintech.exceptions.WrongDateFormatException;

import de.demo.testProjectJava.fintech.service.CustomerService;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;



@DataJpaTest
public class CustomerRepositoryTest {
  @Autowired
    CustomerRepository customerRepository;
  @Autowired
    AddressRepository addressRepository;
  @Test
  public void findAllCustomersOnEmptyDb(){
    //given
    //when
    List<CustomerEntity> customers = customerRepository.findAll();
    //then
    assertThat(customers).isEmpty();
  }

  @Test
  public void findAllCustomersAfterSavingCustomer() throws Exception{
    //given
   final CustomerEntity customer = new CustomerEntity(
      UUID.randomUUID().toString(),
      "Donald",
      "Duck",
      "Mr.",
      CustomerService.convertToDate("13/03/1988"),
      2,
      null
    );
    customerRepository.save(customer);
    //when
    CustomerEntity customers = customerRepository.findAll().get(0);
    //
    
    assertThat(customers).usingRecursiveComparison().isEqualTo(customer);
  }

  @Test
  public void findAllCustomersByName() throws Exception{
    //given
    final CustomerEntity customerA = new CustomerEntity(
      UUID.randomUUID().toString(),
      "Donald",
      "Duck",
      "Mr.",
      CustomerService.convertToDate("13/03/1988"),
      2,
      null
    );

    final CustomerEntity customerB = new CustomerEntity(
      UUID.randomUUID().toString(),
      "Max",
      "Mustermann",
      "Mr.",
      CustomerService.convertToDate("12/03/1944"),
      2,
      null
    );

    final CustomerEntity customerC = new CustomerEntity(
      UUID.randomUUID().toString(),
      "Paul",
      "Mustermann",
      "Mr.",
      CustomerService.convertToDate("04/04/1955"),
      2,
      null
    );

    customerRepository.saveAll(Arrays.asList(customerA, customerB, customerC));

    //when
    List<CustomerEntity> customers = customerRepository.findAllByName("Mustermann");
    //then
    assertThat(customers).usingRecursiveComparison().isEqualTo(Arrays.asList(customerB, customerC));
  };

  @Test
  public void getPersonWithAddress() throws WrongDateFormatException{
    //given
    CustomerEntity customer = new CustomerEntity(
      "1",
      "Tony",
      "Stark",
      "Mr.",
      CustomerService.convertToDate("19/05/1970"),
      2,
      null
    );

    AddressEntity address = new AddressEntity(
      "1",
      customer.getId(),
      "Malibu",
      "Malibu Point 10880",
      "California",
      "90265",
      "United States",
      new CustomerEntity()
    );

    customer.setAddress(address);

    //when
    addressRepository.save(address);
    customerRepository.save(customer);

    //then
    CustomerEntity completeCustomer = customerRepository.getById("1");
    assertThat(completeCustomer.getAddress()).isNotNull();
    assertThat(completeCustomer.getAddress().getCity()).isEqualTo("Malibu");
  }

}
