package de.kirschUndKern.testProjectJava.fintech.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.*;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import de.kirschUndKern.testProjectJava.fintech.entities.CreditsEntity;

@DataJpaTest
public class CreditsRepositoryTest {
  @Autowired CreditsRepository creditsRepository;

  @Test
  public void findAllCreditsOnEmptyDb(){
    List<CreditsEntity> credits = creditsRepository.findAll();
    assertThat(credits).isEmpty();
  }

  @Test
  public void findAllCreditsAfterSaving(){
    final CreditsEntity credit = new CreditsEntity(
      UUID.randomUUID().toString(),
      "1233",
      LocalDate.parse("2020-02-01"),
      12L,
      12L,
      12L,
      12L
    );

    creditsRepository.save(credit);

    CreditsEntity expected = creditsRepository.findAll().get(0);
    assertThat(credit).usingRecursiveComparison().isEqualTo(expected);

  }

  @Test
  public void findAllByCustomerId(){
    final CreditsEntity creditOne = new CreditsEntity(
      UUID.randomUUID().toString(),
      "1233",
      LocalDate.parse("2020-02-01"),
      12L,
      12L,
      12L,
      12L
    );

    final CreditsEntity creditTwo = new CreditsEntity(
      UUID.randomUUID().toString(),
      "1233",
      LocalDate.parse("2020-02-01"),
      12L,
      12L,
      12L,
      12L
    );

    creditsRepository.saveAll(Arrays.asList(creditOne, creditTwo));

    List<CreditsEntity> result = creditsRepository.findAllByCustomerId("1233");
    
    assertThat(result).usingRecursiveComparison().isEqualTo(Arrays.asList(creditOne, creditTwo));
  }
}
