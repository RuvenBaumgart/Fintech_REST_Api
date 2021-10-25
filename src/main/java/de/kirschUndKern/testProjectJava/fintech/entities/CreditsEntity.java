package de.kirschUndKern.testProjectJava.fintech.entities;

import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "credits")
public class CreditsEntity {
  @Id private final String id;
  private final String customerId;
  private final SimpleDateFormat dataOfCreation;
  private final SimpleDateFormat originalTerm;
  private final SimpleDateFormat remainingTerm;
  private final Long orignialCreditAmount;
  private final Long currentCreditAmount;

  public CreditsEntity(
    String customerId, 
    SimpleDateFormat dataOfCreation, 
    SimpleDateFormat originalTerm,
    SimpleDateFormat remainingTerm, 
    Long orignialCreditAmount, 
    Long currentCreditAmount
    ) {
      this.id = UUID.randomUUID().toString();
      this.customerId = customerId;
      this.dataOfCreation = dataOfCreation;
      this.originalTerm = originalTerm;
      this.remainingTerm = remainingTerm;
      this.orignialCreditAmount = orignialCreditAmount;
      this.currentCreditAmount = currentCreditAmount;
  }

  public String getId() {
    return id;
  }
  public String getCustomerId() {
    return customerId;
  }
  public SimpleDateFormat getDataOfCreation() {
    return dataOfCreation;
  }
  public SimpleDateFormat getOriginalTerm() {
    return originalTerm;
  }
  public SimpleDateFormat getRemainingTerm() {
    return remainingTerm;
  }
  public Long getOriginalCreditAmount() {
    return orignialCreditAmount;
  }
  public Long getCurrentCreditAmount() {
    return currentCreditAmount;
  }
}
