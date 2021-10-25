package de.kirschUndKern.testProjectJava.fintech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.kirschUndKern.testProjectJava.fintech.entities.CreditsEntity;

public interface CreditsRepository extends JpaRepository<CreditsEntity, String>{
  
}
