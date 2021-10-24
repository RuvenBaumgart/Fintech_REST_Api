package de.kirschUndKern.testProjectJava.fintech.modell;

public class TransactionRequest {
  private final Long amount;
  private final String destinationAccountId;


  public TransactionRequest(
    Long amount,
    String destinationAccountId
  ){
    this.amount = amount;
    this.destinationAccountId = destinationAccountId;
  }
  public Long getAmount(){
    return this.amount;
  }

  public String getDestinationAccoutnId(){
    return this.destinationAccountId;
  }
}
