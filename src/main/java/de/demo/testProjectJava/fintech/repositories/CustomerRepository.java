package de.demo.testProjectJava.fintech.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import de.demo.testProjectJava.fintech.entities.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

  @Query("SELECT cus FROM CustomerEntity cus WHERE cus.secondname = :secondname")
  List<CustomerEntity> findAllByName(String secondname);
  
  @Query("SELECT cus FROM CustomerEntity cus WHERE cus.secondname = :secondname ORDER BY firstname")
  List<CustomerEntity> findAllByNameOrdered(String secondname);
  
}
