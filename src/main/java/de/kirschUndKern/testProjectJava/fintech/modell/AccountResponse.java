package de.kirschUndKern.testProjectJava.fintech.modell;

import java.util.List;

import de.kirschUndKern.testProjectJava.fintech.entities.AccountEntity;

public class AccountResponse {
  private final String id;
  private final String customerId;
  private final Long balanceInCent;
  private final Long sumOfTransactions;
  private final List<String> transactionIds;

  public AccountResponse(
    String id, 
    String customerId, 
    Long balanceInCent, 
    Long sumOfTransactions,
    List<String> transactionIds) {
      this.id = id;
      this.customerId = customerId;
      this.balanceInCent = balanceInCent;
      this.sumOfTransactions = sumOfTransactions;
      this.transactionIds = transactionIds;
  }

  public AccountResponse(AccountEntity accountEntity){
    this.id = accountEntity.getId();
    this.customerId = accountEntity.getCustomerId();
    this.balanceInCent = accountEntity.getBalanceInCent();
    this.sumOfTransactions = accountEntity.getsumOfTransactions();
    this.transactionIds = accountEntity.getTransactionIds();
  }

  public String getId() {
    return id;
  }
  public String getCustomerId() {
    return customerId;
  }
  public Long getbalanceInCent() {
    return balanceInCent;
  }
  public Long getsumOfTransactions() {
    return sumOfTransactions;
  }
  public List<String> getTransactionIds() {
    return transactionIds;
  }

}
