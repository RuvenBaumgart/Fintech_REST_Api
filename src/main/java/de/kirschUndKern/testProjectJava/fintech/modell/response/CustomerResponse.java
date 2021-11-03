package de.kirschUndKern.testProjectJava.fintech.modell.response;

import de.kirschUndKern.testProjectJava.fintech.entities.CustomerEntity;

public class CustomerResponse {
  private final String customerId;
  private final String firstname;
  private final String secondname;
  private final String salutation;
  private final Integer rating;


  public CustomerResponse(
    String customerId,
    String firstname, 
    String secondname, 
    String salutation,
    Integer rating
    ) {
      this.customerId = customerId;
      this.firstname = firstname;
      this.secondname = secondname;
      this.salutation = salutation;
      this.rating = rating;
    
  }

  public CustomerResponse(CustomerEntity customer){
    this.customerId = customer.getId();
    this.firstname = customer.getFirstname();
    this.secondname = customer.getSecondname();
    this.salutation = customer.getSalutation();
    this.rating = customer.getRating();
  }

  public String getFirstname() {
    return firstname;
  }
  public String getSecondname() {
    return secondname;
  }
  public String getSalutation() {
    return salutation;
  }

  public Integer getRating(){
    return rating;
  }

  public String getCustomerId(){
    return customerId;
  }
}
