package de.demo.testProjectJava.fintech.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import de.demo.testProjectJava.fintech.dto.request.TransactionRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "transactions")
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class TransactionsEntity {
 
  @Id private String id;
  private String sourceAccountId;
  private String destinationAccoutnId;
  private Long amountInCent;
  private LocalDate transactionDate;
  private LocalTime transactionTime;
  private String message;
  @ManyToOne(
    fetch = FetchType.LAZY,
    optional = false,
    targetEntity = AccountEntity.class
  )
  @JoinColumn(
    name = "sender_id")
  @JsonBackReference
  private AccountEntity sourceAccount; //for debit

  @ManyToOne(
    fetch = FetchType.LAZY,
    optional = false,
    targetEntity = AccountEntity.class
  )
  @JoinColumn (
  name = "receiver_id")
  @JsonBackReference
  private AccountEntity destinationAccount; //for credit

  
  public TransactionsEntity( 
    TransactionRequest tr, 
    String sourceAccountId
    ) {
      this.id = UUID.randomUUID().toString();
      this.sourceAccountId = sourceAccountId;
      this.destinationAccoutnId = tr.getDestinationAccountId();
      this.amountInCent = tr.getAmountInCent();
      this.transactionDate = LocalDate.now();
      this.transactionTime = LocalTime.now();
      this.message = tr.getMessage();
  };

  public TransactionsEntity(
    TransactionRequest tr
  ){
    this.id = UUID.randomUUID().toString();
    this.sourceAccountId = tr.getSourceAccountId();
    this.destinationAccoutnId = tr.getDestinationAccountId();
    this.amountInCent = tr.getAmountInCent();
    this.transactionDate = LocalDate.now();
    this.transactionTime = LocalTime.now();
    this.message = tr.getMessage();
  }
}
