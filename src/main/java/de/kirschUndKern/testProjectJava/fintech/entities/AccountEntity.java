package de.kirschUndKern.testProjectJava.fintech.entities;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.List;

@Entity
@Table(name = "accounts")
public class AccountEntity {
  @Id private String id;
  private String customerId;
  private Long balance;
  private Long transactionsCarriedOut;
  @ElementCollection private List<String> transactionIds;

  public AccountEntity(){
  }
  
  public AccountEntity(
    String id, 
    String customerId, 
    Long balance, 
    Long transactionsCarriedOut,
    List<String> transactionIds) {
      this.id = id;
      this.customerId = customerId;
      this.balance = balance;
      this.transactionsCarriedOut = transactionsCarriedOut;
      this.transactionIds = transactionIds;
  }

  public String getId() {
    return id;
  }
  public String getCustomerId() {
    return customerId;
  }
  public Long getBalance() {
    return balance;
  }
  public Long getTransactionsCarriedOut() {
    return transactionsCarriedOut;
  }
  public List<String> getTransactionIds() {
    return transactionIds;
  }
}
