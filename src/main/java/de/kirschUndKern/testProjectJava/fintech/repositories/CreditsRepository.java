package de.kirschUndKern.testProjectJava.fintech.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import de.kirschUndKern.testProjectJava.fintech.entities.CreditsEntity;

@Repository
public interface CreditsRepository extends JpaRepository<CreditsEntity, String>{

  @Query("SELECT ALL cre FROM CreditsEntity cre WHERE cre.customerId = :customerId")
  List<CreditsEntity> findAllByCustomerId(String customerId);

  @Query("SELECT ALL cre FROM CreditsEntity cre WHERE cre.remainingTermInMonths < 0.0")
  List<CreditsEntity> findAllThatExceededOriginalTerm();
  
}
