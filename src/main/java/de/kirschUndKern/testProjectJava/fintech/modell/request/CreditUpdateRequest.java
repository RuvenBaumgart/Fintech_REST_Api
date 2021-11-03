package de.kirschUndKern.testProjectJava.fintech.modell.request;

public class CreditUpdateRequest {
  private final Long amountInCent;
  private String sourceAccountId;

  public CreditUpdateRequest(
    Long amountInCent,
    String sourceAccountId
  ){
    this.amountInCent = amountInCent;
    this.sourceAccountId = sourceAccountId;
  }

  public Long getAmountInCent(){
    return amountInCent;
  }

  public String getSourceAccountId(){
    return sourceAccountId;
  }
}
