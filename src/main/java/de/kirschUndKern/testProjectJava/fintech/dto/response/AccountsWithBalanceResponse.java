package de.kirschUndKern.testProjectJava.fintech.dto.response;

import de.kirschUndKern.testProjectJava.fintech.entities.AccountEntity;

public class AccountsWithBalanceResponse {
  private final String id;
  private final Long balanceInCents;

  public AccountsWithBalanceResponse(AccountEntity ae){
   this.id = ae.getId();
   this.balanceInCents = ae.getBalanceInCent();
  }

  public String getId(){
    return this.id;
  };

  public Long getBalanceInCents(){
    return this.balanceInCents;
  };

}
