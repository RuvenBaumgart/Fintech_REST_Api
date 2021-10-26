package de.kirschUndKern.testProjectJava.fintech.entities;

import java.time.LocalDate;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "credits")
public class CreditsEntity {
  @Id private  String id;
  private String customerId;
  private LocalDate dataOfCreation;
  private Long originalTermInMonths;
  private Long remainingTermInMonths;
  private Long orignialCreditAmountInCents;
  private Long currentCreditAmountInCents;

  public CreditsEntity(){
  }
  
  public CreditsEntity(
    String id,
    String customerId, 
    LocalDate dataOfCreation,
    Long originalTermInMonths,
    Long remainingTermInMonths, 
    Long orignialCreditAmount, 
    Long currentCreditAmount
    ) {
      this.id = id;
      this.customerId = customerId;
      this.dataOfCreation = dataOfCreation;
      this.originalTermInMonths = originalTermInMonths;
      this.remainingTermInMonths = remainingTermInMonths;
      this.orignialCreditAmountInCents = orignialCreditAmount;
      this.currentCreditAmountInCents = currentCreditAmount;
  }


  public String getId() {
    return id;
  }
  public String getCustomerId() {
    return customerId;
  }
  public LocalDate getDataOfCreation() {
    return dataOfCreation;
  }
  public Long getOriginalTermInMonths() {
    return originalTermInMonths;
  }
  public Long getRemainingTermInMonths() {
    return remainingTermInMonths;
  }
  public Long getOriginalCreditAmount() {
    return orignialCreditAmountInCents;
  }
  public Long getCurrentCreditAmount() {
    return currentCreditAmountInCents;
  }
}
