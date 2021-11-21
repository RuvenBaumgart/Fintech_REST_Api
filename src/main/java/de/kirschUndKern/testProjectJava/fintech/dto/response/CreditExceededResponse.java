package de.kirschUndKern.testProjectJava.fintech.dto.response;

import de.kirschUndKern.testProjectJava.fintech.entities.CreditsEntity;
import de.kirschUndKern.testProjectJava.fintech.entities.CustomerEntity;

public class CreditExceededResponse {

  private final Long originalCreditAmountInCents;
  private final Long currentCreditAmountInCents;
  private final String firstname;
  private final String secondname;

  public CreditExceededResponse(CreditsEntity credit, CustomerEntity customer){
    this.originalCreditAmountInCents = credit.getOriginalCreditAmount();
    this.currentCreditAmountInCents = credit.getOriginalCreditAmount();
    this.firstname = customer.getFirstname();
    this.secondname = customer.getSecondname();
  }

  public Long getOriginalCreditAmount() {
    return originalCreditAmountInCents;
  }

  public Long getCurrentCreditAmount() {
    return currentCreditAmountInCents;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getSecondname() {
    return secondname;
  }
  
}
