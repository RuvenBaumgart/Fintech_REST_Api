package de.kirschUndKern.testProjectJava.fintech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.kirschUndKern.testProjectJava.fintech.entities.TransactionsEntity;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionsEntity, String> {
  
}
