package de.kirschUndKern.testProjectJava.fintech.modell;

public class TransactionRequest {
  private final Long amountInCent;
  private final String destinationAccountId;
  private final String message;


  public TransactionRequest(
    Long amountInCent,
    String destinationAccountId,
    String message
  ){
    this.amountInCent = amountInCent;
    this.destinationAccountId = destinationAccountId;
    this.message = message;
  }
  public Long getAmountInCent(){
    return this.amountInCent;
  }

  public String getDestinationAccoutnId(){
    return this.destinationAccountId;
  }

  public String getMessage(){
    return this.message;
  }
}
