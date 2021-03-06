package de.demo.testProjectJava.fintech.dto.response;

import de.demo.testProjectJava.fintech.entities.AccountEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class AccountsWithBalanceResponse {
  private String id;
  private Long balanceInCents;

  public AccountsWithBalanceResponse(AccountEntity ae){
   this.id = ae.getId();
   this.balanceInCents = ae.getBalanceInCent();
  }

}
