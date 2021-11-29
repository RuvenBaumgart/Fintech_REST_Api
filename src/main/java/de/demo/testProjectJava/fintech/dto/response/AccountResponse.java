package de.demo.testProjectJava.fintech.dto.response;

import java.util.List;

import de.demo.testProjectJava.fintech.entities.AccountEntity;
import de.demo.testProjectJava.fintech.entities.TransactionsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class AccountResponse {
  private String id;
  private String customerId;
  private Long balanceInCent;
  private Long sumOfTransactions;
  private List<String> transactionIds;
  private List<TransactionsEntity> debitTransactions;
  private List<TransactionsEntity> creditTransactions;

  public AccountResponse(AccountEntity accountEntity){
    this.id = accountEntity.getId();
    this.customerId = accountEntity.getCustomerId();
    this.balanceInCent = accountEntity.getBalanceInCent();
    this.sumOfTransactions = accountEntity.getSumOfTransactions();
    this.transactionIds = accountEntity.getTransactionIds();
    this.creditTransactions = accountEntity.getCreditTransactions();
    this.debitTransactions = accountEntity.getDebitTransactions();
  }

}
