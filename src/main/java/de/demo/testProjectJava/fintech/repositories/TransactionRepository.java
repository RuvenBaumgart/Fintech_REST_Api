package de.demo.testProjectJava.fintech.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import de.demo.testProjectJava.fintech.entities.TransactionsEntity;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionsEntity, String> {

  @Query("SELECT ALL t FROM TransactionsEntity t WHERE t.transactionDate = :date")
  List<TransactionsEntity> findAllByDate(LocalDate date);

}
