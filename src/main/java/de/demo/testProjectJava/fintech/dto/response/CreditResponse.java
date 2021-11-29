package de.demo.testProjectJava.fintech.dto.response;
import de.demo.testProjectJava.fintech.entities.CreditsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class CreditResponse {
  private String id;
  private String customerId;
  private Long originalTermInMonths;
  private Long remainingTermInMonths;
  private Long originalCreditAmount;
  private Long currentCreditAmount;

  public CreditResponse(CreditsEntity creditEntity){
    this.id = creditEntity.getId();
    this.customerId = creditEntity.getCustomerId();
    this.originalTermInMonths = creditEntity.getOriginalTermInMonths();
    this.remainingTermInMonths = creditEntity.getRemainingTermInMonths();
    this.originalCreditAmount = creditEntity.getOrignialCreditAmountInCents();
    this.currentCreditAmount = creditEntity.getCurrentCreditAmountInCents();
  }
}
