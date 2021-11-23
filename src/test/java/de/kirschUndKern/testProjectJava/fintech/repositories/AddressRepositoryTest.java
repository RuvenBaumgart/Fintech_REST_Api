package de.kirschUndKern.testProjectJava.fintech.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import de.kirschUndKern.testProjectJava.fintech.entities.AddressEntity;


import static org.assertj.core.api.Assertions.*;


@DataJpaTest
public class AddressRepositoryTest {
  @Autowired AddressRepository addressRepository;

  @Test
  public void findAllAddressesOnEmptyDb(){
    //given
    //when
    List<AddressEntity> addresses = addressRepository.findAll();
    //then
    assertThat(addresses).isEmpty();
  }
}
