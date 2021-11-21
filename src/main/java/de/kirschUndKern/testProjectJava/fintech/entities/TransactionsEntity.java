package de.kirschUndKern.testProjectJava.fintech.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import de.kirschUndKern.testProjectJava.fintech.dto.request.TransactionRequest;

@Entity
@Table (name = "transactions")
public class TransactionsEntity {
 
  @Id private String id;
  private String sourceAccountId;
  private String destinationAccoutnId;
  private Long amountInCent;
  private LocalDate transactionDate;
  private LocalTime transactionTime;
  private String message;


  public TransactionsEntity(){
  }

  public TransactionsEntity(
    String id, 
    String sourceAccountId, 
    String destinationAccoutnId, 
    Long amountInCent,
    LocalDate transactionDate, 
    LocalTime transactionTime, 
    String message
    ){
      this.id = id;
      this.sourceAccountId = sourceAccountId;
      this.destinationAccoutnId = destinationAccoutnId;
      this.amountInCent = amountInCent;
      this.transactionDate = transactionDate;
      this.transactionTime = transactionTime;
      this.message = message;
}
  
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
  public Long getAmountInCent() {
    return amountInCent;
  }
  public LocalDate getTransactionDate() {
    return transactionDate;
  }

  public LocalTime getTransactionTime(){
    return transactionTime;
  }
  public String getMessage(){
    return message;
  }

}
