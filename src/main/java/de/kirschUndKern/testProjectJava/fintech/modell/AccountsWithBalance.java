package de.kirschUndKern.testProjectJava.fintech.modell;

import de.kirschUndKern.testProjectJava.fintech.entities.AccountEntity;

public class AccountsWithBalance {
  private final String id;
  private final Long balanceInCents;

  public AccountsWithBalance(AccountEntity ae){
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
