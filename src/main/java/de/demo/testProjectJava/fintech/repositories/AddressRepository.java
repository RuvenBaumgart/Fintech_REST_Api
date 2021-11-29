package de.demo.testProjectJava.fintech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.demo.testProjectJava.fintech.entities.AddressEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, String>{
  
}
