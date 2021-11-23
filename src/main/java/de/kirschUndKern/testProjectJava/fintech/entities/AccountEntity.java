package de.kirschUndKern.testProjectJava.fintech.entities;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "accounts")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {
  @Id private String id;
  private String customerId;
  private Long balanceInCent;
  private Long sumOfTransactions;
  @ElementCollection 
  private List<String> transactionIds;

  public AccountEntity(
    AccountEntity accountEntity, 
    Long amountInCent, 
    List<String> newTransactionIds
    ){
      this.id = accountEntity.getId();
      this.customerId = accountEntity.getCustomerId();
      this.balanceInCent= accountEntity.getBalanceInCent() + amountInCent;
      this.sumOfTransactions = accountEntity.getSumOfTransactions() + 1;
      this.transactionIds = newTransactionIds;

  }
}
