package de.kirschUndKern.testProjectJava.fintech.modell;

public class CreditRequest {
  private final Long creditAmountInCents;
  private final Long runtimeInMonths;

  public CreditRequest(Long creditAmountInCents, Long runtimeInMonths) {
    this.creditAmountInCents = creditAmountInCents;
    this.runtimeInMonths = runtimeInMonths;
  }

  public Long getCreditAmountInCents() {
    return creditAmountInCents;
  }

  public Long getRuntimeInMonths() {
    return runtimeInMonths;
  }
}
