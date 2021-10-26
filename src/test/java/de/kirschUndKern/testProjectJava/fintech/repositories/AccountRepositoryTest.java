package de.kirschUndKern.testProjectJava.fintech.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
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
    AccountEntity expected = accountRepository.findAll().get(0);
    
    //then 
    assertThat(expected).usingRecursiveComparison().isEqualTo(account);
  
  }

  
};
