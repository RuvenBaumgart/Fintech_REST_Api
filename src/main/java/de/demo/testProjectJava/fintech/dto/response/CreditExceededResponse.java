package de.demo.testProjectJava.fintech.dto.response;

import de.demo.testProjectJava.fintech.entities.CreditsEntity;
import de.demo.testProjectJava.fintech.entities.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor

public class CreditExceededResponse {

  private Long originalCreditAmountInCents;
  private Long currentCreditAmountInCents;
  private String firstname;
  private String secondname;

  public CreditExceededResponse(CreditsEntity credit, CustomerEntity customer){
    this.originalCreditAmountInCents = credit.getOrignialCreditAmountInCents();
    this.currentCreditAmountInCents = credit.getOrignialCreditAmountInCents();
    this.firstname = customer.getFirstname();
    this.secondname = customer.getSecondname();
  }
}
