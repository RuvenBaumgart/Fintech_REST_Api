package de.kirschUndKern.testProjectJava.fintech.entities;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class AccountEntity {
  @Id private String id;
  private String customerId;
  private Long balanceInCent;
  private Long sumOfTransactions;
  @ElementCollection private List<String> transactionIds;

  public AccountEntity(){
  }
  
  public AccountEntity(
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

  public AccountEntity(
    AccountEntity accountEntity, 
    Long amountInCent, 
    List<String> newTransactionIds
    ){
      this.id = accountEntity.getId();
      this.customerId = accountEntity.getCustomerId();
      this.balanceInCent= accountEntity.getBalanceInCent() + amountInCent;
      this.sumOfTransactions = accountEntity.getsumOfTransactions() + 1;
      this.transactionIds = newTransactionIds;

  }

  public String getId() {
    return id;
  }
  public String getCustomerId() {
    return customerId;
  }
  public Long getBalanceInCent() {
    return balanceInCent;
  }
  public Long getsumOfTransactions() {
    return sumOfTransactions;
  }
  public List<String> getTransactionIds() {
    return transactionIds;
  }


}
