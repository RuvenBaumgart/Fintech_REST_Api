package de.kirschUndKern.testProjectJava.fintech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.kirschUndKern.testProjectJava.fintech.entities.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
  
}
