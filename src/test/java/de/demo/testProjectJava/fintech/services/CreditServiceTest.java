package de.demo.testProjectJava.fintech.services;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import de.demo.testProjectJava.fintech.repositories.AccountRepository;
import de.demo.testProjectJava.fintech.repositories.CreditsRepository;
import de.demo.testProjectJava.fintech.repositories.CustomerRepository;
import de.demo.testProjectJava.fintech.service.AccountService;
import de.demo.testProjectJava.fintech.service.CreditService;
import de.demo.testProjectJava.fintech.service.TransactionService;

import static org.assertj.core.api.Assertions.*;

public class CreditServiceTest {
  
  private CreditService creditService;
  private CustomerRepository customerRepository;
  private CreditsRepository creditRepository;
  private AccountRepository accountRepository;
  private TransactionService transactionService;
  private AccountService accountService;


  @BeforeEach
  public void init(){
    customerRepository = Mockito.mock(CustomerRepository.class);
    creditRepository = Mockito.mock(CreditsRepository.class);
    accountRepository = Mockito.mock(AccountRepository.class);
    transactionService = Mockito.mock(TransactionService.class);
    accountService = Mockito.mock(AccountService.class);

    creditService = new CreditService(
      customerRepository, 
      creditRepository,
      accountRepository,
      transactionService,
      accountService
      );
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
