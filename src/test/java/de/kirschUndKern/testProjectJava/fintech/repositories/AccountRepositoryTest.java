package de.kirschUndKern.testProjectJava.fintech.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import de.kirschUndKern.testProjectJava.fintech.entities.AccountEntity;

@DataJpaTest
public class AccountRepositoryTest {
  @Autowired AccountRepository accountRepository;

  @Test
  public void findAllAccountsOnEmptyDb(){
    List<AccountEntity> accounts = accountRepository.findAll();
    assertThat(accounts).isEmpty();
  }

  @Test
  public void findAllAccountsAfterSavingOne(){
    //given
    final AccountEntity account = new AccountEntity(
      UUID.randomUUID().toString(),
      "123",
      10000L,
      23L,
      new ArrayList<String>()
    );
     accountRepository.save(account);
    //when
    AccountEntity result = accountRepository.findAll().get(0);
    //then 
    assertThat(result).usingRecursiveComparison().isEqualTo(account);
  }

  @Test
  public void findAllByCustomerId(){
    //given
    final AccountEntity accountOne = new AccountEntity(
     " UUID.randomUUID().toString()",
      "123",
      10000L,
      23L,
      new ArrayList<String>()
    );

    final AccountEntity accountTwo = new AccountEntity(
      UUID.randomUUID().toString(),
      "123",
      10000L,
      23L,
      new ArrayList<String>()
    );

    final AccountEntity accountThree = new AccountEntity(
      UUID.randomUUID().toString(),
      "233",
      10000L,
      23L,
      new ArrayList<String>()
    );
     accountRepository.saveAll(Arrays.asList(accountOne, accountTwo, accountThree));
    //when
    Optional<List<AccountEntity>> results = accountRepository.findAllByCustomerId("123");
    //then 
    assertThat(results.get()).usingRecursiveComparison().isEqualTo(Arrays.asList(accountOne, accountTwo));
  }

  
};
