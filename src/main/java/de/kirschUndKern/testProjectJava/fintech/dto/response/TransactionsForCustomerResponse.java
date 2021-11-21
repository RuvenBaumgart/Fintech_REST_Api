package de.kirschUndKern.testProjectJava.fintech.dto.response;

import de.kirschUndKern.testProjectJava.fintech.entities.CustomerEntity;
import de.kirschUndKern.testProjectJava.fintech.entities.TransactionsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class TransactionsForCustomerResponse {
  private String accountSourceId;
  private String customerFirstname;
  private String customerSecondname;
  private String accountDestinationId;
  private String customerFirstnameDestination;
  private String customerSecondnameDesitnation;

  public TransactionsForCustomerResponse(
    TransactionsEntity transaction,
    CustomerEntity sourceCustomer,
    CustomerEntity destinationCustomer){
      this.accountSourceId = transaction.getSourceAccountId();
      this.customerFirstname = sourceCustomer.getFirstname();
      this.customerSecondname = sourceCustomer.getSecondname();
      this.accountDestinationId = transaction.getDestinationAccoutnId();
      this.customerFirstnameDestination = destinationCustomer.getFirstname();
      this.customerSecondnameDesitnation = destinationCustomer.getSecondname();
    }
}
