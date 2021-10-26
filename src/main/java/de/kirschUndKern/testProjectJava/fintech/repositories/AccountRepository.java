package de.kirschUndKern.testProjectJava.fintech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import de.kirschUndKern.testProjectJava.fintech.entities.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {
  

}
