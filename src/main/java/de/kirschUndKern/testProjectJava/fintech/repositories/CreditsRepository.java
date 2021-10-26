package de.kirschUndKern.testProjectJava.fintech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.kirschUndKern.testProjectJava.fintech.entities.CreditsEntity;

@Repository
public interface CreditsRepository extends JpaRepository<CreditsEntity, String>{
  
}
