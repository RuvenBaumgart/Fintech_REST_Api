package de.kirschUndKern.testProjectJava.fintech.entities;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
  @OneToMany(
    mappedBy = "sourceAccount"
  )
  @ElementCollection
  @JasonManagedReference
  private List<TransactionsEntity> debitTransactions;

  @OneToMany(
    mappedBy = "destinationAccount"
  )
  @ElementCollection
  private List<TransactionsEntity> creditTransactions;


  public void setCreditTransactions(List<TransactionsEntity> newCreditTransactions){
    this.creditTransactions = newCreditTransactions;
  }

  public void setDebitTransactions(List<TransactionsEntity> newDebitTransactions){
    this.debitTransactions = newDebitTransactions;
  }

  public void setTransactionIds(List<String> newTransactionIds){
    this.transactionIds = newTransactionIds;
  }

  public void setBalance(Long newBalanceInCent){
    this.balanceInCent = newBalanceInCent;
  }
}
