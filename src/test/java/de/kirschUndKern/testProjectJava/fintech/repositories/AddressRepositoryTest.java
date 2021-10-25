package de.kirschUndKern.testProjectJava.fintech.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.UUID;

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

  @Test 
  public void findAllAddressesAfterSavingAddress(){
    final AddressEntity address = new AddressEntity(
      UUID.randomUUID().toString(),
      "1",
      "MusterCity",
      "MusterStreet",
      "MusterProvince",
      "MusterZipCode",
      "MusterCountry"
    );

   addressRepository.save(address);

   AddressEntity expected = addressRepository.findAll().get(0);
    assertThat(address).usingRecursiveComparison().isEqualTo(expected);
  }
}
