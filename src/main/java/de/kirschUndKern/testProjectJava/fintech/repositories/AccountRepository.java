package de.kirschUndKern.testProjectJava.fintech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.kirschUndKern.testProjectJava.fintech.entities.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, String> {
  
}
