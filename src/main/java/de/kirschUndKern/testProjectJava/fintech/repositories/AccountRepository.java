package de.kirschUndKern.testProjectJava.fintech.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import de.kirschUndKern.testProjectJava.fintech.entities.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {
  
  @Query("SELECT acc FROM AccountEntity acc WHERE acc.customerId = :customerId")
  Optional<AccountEntity> findByCustomerId(String customerId);
  

}
