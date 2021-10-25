package de.kirschUndKern.testProjectJava.fintech.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.UUID;

import de.kirschUndKern.testProjectJava.fintech.entities.CustomerEntity;
import de.kirschUndKern.testProjectJava.fintech.service.CustomerService;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
public class CustomerRepositoryTest {
  @Autowired
    CustomerRepository customerRepository;

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
      2
    );
    customerRepository.save(customer);
    //when
    CustomerEntity customers = customerRepository.findAll().get(0);
    //
    
    assertThat(customers).usingRecursiveComparison().isEqualTo(customer);
  }
}
