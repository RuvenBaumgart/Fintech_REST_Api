package de.kirschUndKern.testProjectJava.fintech.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import de.kirschUndKern.testProjectJava.fintech.modell.TransactionRequest;

@Entity
@Table (name = "transactions")
public class TransactionsEntity {
 
  @Id private final String id;
  private final String sourceAccountId;
  private final String destinationAccoutnId;
  private final Long amount;
  private final LocalDateTime transactionDate;


  public TransactionsEntity( 
    TransactionRequest tr, 
    String sourceAccountId
    ) {
      this.id = UUID.randomUUID().toString();
      this.sourceAccountId = sourceAccountId;
      this.destinationAccoutnId = tr.getDestinationAccoutnId();
      this.amount = tr.getAmount();
      this.transactionDate = LocalDateTime.now();
  }

  public String getId() {
    return id;
  }
  public String getSourceAccountId() {
    return sourceAccountId;
  }
  public String getDestinationAccountId() {
    return destinationAccoutnId;
  }
  public Long getAmount() {
    return amount;
  }
  public LocalDateTime getTransactionDate() {
    return transactionDate;
  }
}
