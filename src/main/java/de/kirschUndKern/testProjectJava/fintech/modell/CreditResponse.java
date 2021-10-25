package de.kirschUndKern.testProjectJava.fintech.modell;

import java.text.SimpleDateFormat;
import de.kirschUndKern.testProjectJava.fintech.entities.CreditsEntity;

public class CreditResponse {
  private final String id;
  private final SimpleDateFormat originalTerm;
  private final SimpleDateFormat remainingTerm;
  private final Long originalCreditAmount;
  private final Long currentCreditAmount;

  public CreditResponse(CreditsEntity ce){
    this.id = ce.getId();
    this.originalTerm = ce.getOriginalTerm();
    this.remainingTerm = ce.getRemainingTerm();
    this.originalCreditAmount = ce.getOriginalCreditAmount();
    this.currentCreditAmount = ce.getCurrentCreditAmount();
  }

  public String getId() {
    return id;
  }
  public SimpleDateFormat getOriginalTerm() {
    return originalTerm;
  }
  public SimpleDateFormat getRemainingTerm() {
    return remainingTerm;
  }
  public Long getOriginalCreditAmount() {
    return originalCreditAmount;
  }
  public Long getCurrentCreditAmount() {
    return currentCreditAmount;
  }
}
