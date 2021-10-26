package de.kirschUndKern.testProjectJava.fintech.services;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.*;

import de.kirschUndKern.testProjectJava.fintech.repositories.CreditsRepository;
import de.kirschUndKern.testProjectJava.fintech.repositories.CustomerRepository;
import de.kirschUndKern.testProjectJava.fintech.service.CreditService;

public class CreditServiceTest {
  
  private CreditService creditService;
  private CustomerRepository customerRepository;
  private CreditsRepository creditRepository;

  @BeforeEach
  public void init(){
    customerRepository = Mockito.mock(CustomerRepository.class);
    creditRepository = Mockito.mock(CreditsRepository.class);
    creditService = new CreditService(customerRepository, creditRepository);
  }

  @Test
  public void calculateCorrectRemainingRuntime(){
    //given"
    LocalDate startDate = LocalDate.parse("2020-01-01");
    LocalDate currentDate = LocalDate.parse("2021-01-01");
    Long originalRuntime = 24L;
    Long expectedRuntime = 12L;

    //when
    Long result = creditService.calculateRemainingRuntime(startDate, currentDate, originalRuntime);

    //then
    assertThat(result).isEqualTo(expectedRuntime);
  }
}
