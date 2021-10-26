package de.kirschUndKern.testProjectJava.fintech.modell;
import de.kirschUndKern.testProjectJava.fintech.entities.CreditsEntity;

public class CreditResponse {
  private final String id;
  private final String customerId;
  private final Long originalTermInMonths;
  private final Long remainingTermInMonths;
  private final Long originalCreditAmount;
  private final Long currentCreditAmount;

  public CreditResponse(CreditsEntity creditEntity){
    this.id = creditEntity.getId();
    this.customerId = creditEntity.getCustomerId();
    this.originalTermInMonths = creditEntity.getOriginalTermInMonths();
    this.remainingTermInMonths = creditEntity.getRemainingTermInMonths();
    this.originalCreditAmount = creditEntity.getOriginalCreditAmount();
    this.currentCreditAmount = creditEntity.getCurrentCreditAmount();
  }

  public String getId() {
    return id;
  }
  public Long getOriginalTermInMonths() {
    return originalTermInMonths;
  }
  public Long getRemainingTermInMonths() {
    return remainingTermInMonths;
  }
  public Long getOriginalCreditAmount() {
    return originalCreditAmount;
  }
  public Long getCurrentCreditAmount() {
    return currentCreditAmount;
  }
  public String getCustomerId(){
    return customerId;
  }
}
