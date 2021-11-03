package de.kirschUndKern.testProjectJava.fintech.modell.response;

import java.time.LocalDate;
import java.time.LocalTime;

import de.kirschUndKern.testProjectJava.fintech.entities.TransactionsEntity;

public class TransactionsFullResponse {
  private final String id;
  private final String sourceAccountId;
  private final String destinationAccountId;
  private final Long amountInCent;
  private final LocalDate transactionDate;
  private final LocalTime transactionTime;

  public TransactionsFullResponse(
    String id, 
    String sourceAccountId, 
    String destinationAccountId, 
    Long amountInCent,
    LocalDate transactionDate, 
    LocalTime transactionTime) 
    {
      this.id = id;
      this.sourceAccountId = sourceAccountId;
      this.destinationAccountId = destinationAccountId;
      this.amountInCent = amountInCent;
      this.transactionDate = transactionDate;
      this.transactionTime = transactionTime;
  }

  public TransactionsFullResponse(TransactionsEntity transaction){
    this.id = transaction.getId();
    this.sourceAccountId = transaction.getSourceAccountId();
    this.destinationAccountId = transaction.getDestinationAccountId();
    this.amountInCent = transaction.getAmountInCent();
    this.transactionDate = transaction.getTransactionDate();
    this.transactionTime = transaction.getTransactionTime();
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
  public Long getAmountInCent() {
    return amountInCent;
  }
  public LocalDate getTransactionDate() {
    return transactionDate;
  }

  public LocalTime getTransactionTime(){
    return transactionTime;
  }
}
