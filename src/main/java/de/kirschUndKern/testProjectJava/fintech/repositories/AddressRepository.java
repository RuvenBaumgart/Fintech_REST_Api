package de.kirschUndKern.testProjectJava.fintech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.kirschUndKern.testProjectJava.fintech.entities.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, String>{
  
}
