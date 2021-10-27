package de.kirschUndKern.testProjectJava.fintech.modell;

import de.kirschUndKern.testProjectJava.fintech.entities.CustomerEntity;
import de.kirschUndKern.testProjectJava.fintech.entities.TransactionsEntity;

public class TransactionsForCustomerResponse {
  private final String accountSourceId;
  private final String customerFirstname;
  private final String customerSecondname;
  private final String accountDestinationId;
  private final String customerFirstnameDestination;
  private final String customerSecondnameDesitnation;

  public TransactionsForCustomerResponse(
    TransactionsEntity transaction,
    CustomerEntity sourceCustomer,
    CustomerEntity destinationCustomer){
      this.accountSourceId = transaction.getSourceAccountId();
      this.customerFirstname = sourceCustomer.getFirstname();
      this.customerSecondname = sourceCustomer.getSecondname();
      this.accountDestinationId = transaction.getDestinationAccountId();
      this.customerFirstnameDestination = destinationCustomer.getFirstname();
      this.customerSecondnameDesitnation = destinationCustomer.getSecondname();
    }


    public String getAccountSourceId() {
      return accountSourceId;
    }
  
    public String getCustomerFirstname() {
      return customerFirstname;
    }
  
    public String getCustomerSecondname() {
      return customerSecondname;
    }
  
    public String getAccountDestinationId() {
      return accountDestinationId;
    }
  
    public String getCustomerFirstnameDestination() {
      return customerFirstnameDestination;
    }
  
    public String getCustomerSecondnameDesitnation() {
      return customerSecondnameDesitnation;
    }

}
