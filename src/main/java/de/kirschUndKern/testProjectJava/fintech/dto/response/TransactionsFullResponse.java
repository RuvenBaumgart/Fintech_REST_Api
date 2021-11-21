package de.kirschUndKern.testProjectJava.fintech.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

import de.kirschUndKern.testProjectJava.fintech.entities.TransactionsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor

public class TransactionsFullResponse {
  private String id;
  private String sourceAccountId;
  private String destinationAccountId;
  private Long amountInCent;
  private LocalDate transactionDate;
  private LocalTime transactionTime;

  public TransactionsFullResponse(TransactionsEntity transaction){
    this.id = transaction.getId();
    this.sourceAccountId = transaction.getSourceAccountId();
    this.destinationAccountId = transaction.getDestinationAccoutnId();
    this.amountInCent = transaction.getAmountInCent();
    this.transactionDate = transaction.getTransactionDate();
    this.transactionTime = transaction.getTransactionTime();
  }

}
