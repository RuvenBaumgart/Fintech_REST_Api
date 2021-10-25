package de.kirschUndKern.testProjectJava.fintech.modell;

import java.time.LocalDateTime;

import de.kirschUndKern.testProjectJava.fintech.entities.TransactionsEntity;

public class TransactionsFullResponse {
  private final String id;
  private final String sourceAccountId;
  private final String destinationAccountId;
  private final Long amount;
  private final LocalDateTime transactionDate;

  public TransactionsFullResponse(TransactionsEntity transaction){
    this.id = transaction.getId();
    this.sourceAccountId = transaction.getSourceAccountId();
    this.destinationAccountId = transaction.getDestinationAccountId();
    this.amount = transaction.getAmount();
    this.transactionDate = transaction.getTransactionDate();
  }
  public String getId() {
    return id;
  }
  public String getSourceAccountId() {
    return sourceAccountId;
  }
  public String getDestinationAccountId() {
    return destinationAccountId;
  }
  public Long getAmount() {
    return amount;
  }
  public LocalDateTime getTransactionDate() {
    return transactionDate;
  }
}
