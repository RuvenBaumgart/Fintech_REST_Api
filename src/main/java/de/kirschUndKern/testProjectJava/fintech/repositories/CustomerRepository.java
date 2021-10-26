package de.kirschUndKern.testProjectJava.fintech.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import de.kirschUndKern.testProjectJava.fintech.entities.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

  @Query("SELECT cus FROM CustomerEntity cus WHERE cus.secondname = :secondname")
  List<CustomerEntity> findAllByName(String secondname);
  
  @Query("SELECT cus FROM CustomerEntity cus WHERE cus.secondname = :secondname ORDER BY firstname")
  List<CustomerEntity> findAllByNameOrdered(String secondname);
}
