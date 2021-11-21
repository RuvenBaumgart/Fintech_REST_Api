package de.kirschUndKern.testProjectJava.fintech.dto.response;

import java.util.List;

import de.kirschUndKern.testProjectJava.fintech.entities.AccountEntity;
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

  public AccountResponse(AccountEntity accountEntity){
    this.id = accountEntity.getId();
    this.customerId = accountEntity.getCustomerId();
    this.balanceInCent = accountEntity.getBalanceInCent();
    this.sumOfTransactions = accountEntity.getSumOfTransactions();
    this.transactionIds = accountEntity.getTransactionIds();
  }

}
