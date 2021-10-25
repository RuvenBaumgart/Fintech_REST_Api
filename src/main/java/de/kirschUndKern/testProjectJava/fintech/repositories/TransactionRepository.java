package de.kirschUndKern.testProjectJava.fintech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.kirschUndKern.testProjectJava.fintech.entities.TransactionsEntity;

public interface TransactionRepository extends JpaRepository<TransactionsEntity, String> {
  
}
