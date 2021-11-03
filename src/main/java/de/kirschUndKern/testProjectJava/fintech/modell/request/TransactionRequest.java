package de.kirschUndKern.testProjectJava.fintech.modell.request;

public class TransactionRequest {
  private final Long amountInCent;
  private final String sourceAccountId;
  private final String destinationAccountId;
  private final String message;


  public TransactionRequest(
    Long amountInCent,
    String sourceAccountId, //if customer as more than one account
    String destinationAccountId,
    String message
  ){
    this.amountInCent = amountInCent;
    this.sourceAccountId = sourceAccountId;
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

  public String getSourceAccountId(){
    return this.sourceAccountId;
  }
}
