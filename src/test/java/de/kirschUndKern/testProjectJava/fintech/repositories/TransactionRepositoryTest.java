package de.kirschUndKern.testProjectJava.fintech.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.*;
import de.kirschUndKern.testProjectJava.fintech.entities.TransactionsEntity;

@DataJpaTest
public class TransactionRepositoryTest {
  @Autowired TransactionRepository transactionRespository;

  @Test
  public void findAllTransactionsOnEmptyDb(){
    List<TransactionsEntity> transactions = transactionRespository.findAll();
    assertThat(transactions).isEmpty();
  }

  @Test
  public void findAllTransactionsAfterSaving(){
    //given
    final TransactionsEntity transaction = new TransactionsEntity(
      UUID.randomUUID().toString(),
      "1234",
      "4321",
      500000L,
      LocalDate.now(),
      LocalTime.now(),
      "Test Transaction"
    );
    transactionRespository.save(transaction);

    //when
    TransactionsEntity expected = transactionRespository.findAll().get(0);

    //then
    assertThat(transaction).usingRecursiveComparison().isEqualTo(expected);
  }

  @Test 
  public void getBackAllSavedTransactions(){
    final TransactionsEntity firstTransaction = new TransactionsEntity(
      UUID.randomUUID().toString(),
      "1234",
      "4321",
      500000L,
      LocalDate.now(),
      LocalTime.now(),
      "Test Transaction"
    );
    final TransactionsEntity secTransaction = new TransactionsEntity(
      UUID.randomUUID().toString(),
      "4321",
      "4321",
      500000L,
      LocalDate.now(),
      LocalTime.now(),
      "Test Transaction"
    );

    List<TransactionsEntity> results = transactionRespository.saveAll(Arrays.asList(firstTransaction,secTransaction));

    assertThat(results).usingRecursiveComparison().isEqualTo(Arrays.asList(firstTransaction,secTransaction));
  }

}
