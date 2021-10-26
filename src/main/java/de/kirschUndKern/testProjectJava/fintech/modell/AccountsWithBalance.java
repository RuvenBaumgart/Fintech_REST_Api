package de.kirschUndKern.testProjectJava.fintech.modell;

import de.kirschUndKern.testProjectJava.fintech.entities.AccountEntity;

public class AccountsWithBalance {
  private final String id;
  private final Long balance;

  public AccountsWithBalance(AccountEntity ae){
   this.id = ae.getId();
   this.balance = ae.getBalanceInCent();
  }

  public String getId(){
    return this.id;
  };

  public Long getBalance(){
    return this.balance;
  };

}
