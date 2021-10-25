package de.kirschUndKern.testProjectJava.fintech.modell;

import de.kirschUndKern.testProjectJava.fintech.entities.CreditsEntity;
import de.kirschUndKern.testProjectJava.fintech.entities.CustomerEntity;

public class CreditExceededResponse {

  private final Long originalCreditAmount;
  private final Long currentCreditAmount;
  private final String firstname;
  private final String secondname;

  public CreditExceededResponse(CreditsEntity credit, CustomerEntity customer){
    this.originalCreditAmount = credit.getOriginalCreditAmount();
    this.currentCreditAmount = credit.getOriginalCreditAmount();
    this.firstname = customer.getFirstname();
    this.secondname = customer.getSecondname();
  }

  public Long getOriginalCreditAmount() {
    return originalCreditAmount;
  }

  public Long getCurrentCreditAmount() {
    return currentCreditAmount;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getSecondname() {
    return secondname;
  }
  
}
