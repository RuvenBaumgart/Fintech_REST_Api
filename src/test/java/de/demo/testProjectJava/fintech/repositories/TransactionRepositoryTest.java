package de.demo.testProjectJava.fintech.repositories;

import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import de.demo.testProjectJava.fintech.entities.TransactionsEntity;


import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class TransactionRepositoryTest {
  @Autowired TransactionRepository transactionRespository;

  @Test
  public void findAllTransactionsOnEmptyDb(){
    List<TransactionsEntity> transactions = transactionRespository.findAll();
    assertThat(transactions).isEmpty();
  }
}
