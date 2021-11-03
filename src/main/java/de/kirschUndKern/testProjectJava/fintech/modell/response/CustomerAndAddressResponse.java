package de.kirschUndKern.testProjectJava.fintech.modell.response;

import de.kirschUndKern.testProjectJava.fintech.entities.AddressEntity;
import de.kirschUndKern.testProjectJava.fintech.entities.CustomerEntity;


public class CustomerAndAddressResponse {
  private final String customerId;
  private final String firstname;
  private final String secondname;
  private final String salutation;
  private final Integer rating;
  private final AddressResponse address;

  public CustomerAndAddressResponse(
    String customerId,
    String firstname, 
    String secondname, 
    String salutation,
    Integer rating,
    AddressResponse address
    ) {
      this.customerId = customerId;
      this.firstname = firstname;
      this.secondname = secondname;
      this.salutation = salutation;
      this.rating = rating;
      this.address = address;
  }

  public CustomerAndAddressResponse(CustomerEntity customer, AddressEntity address){
    this.customerId = customer.getId();
    this.firstname = customer.getFirstname();
    this.secondname = customer.getSecondname();
    this.salutation = customer.getSalutation();
    this.rating = customer.getRating();
    this.address = new AddressResponse(address);
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
  public AddressResponse getAddress() {
    return address;
  }
  public Integer getRating(){
    return rating;
  }

  public String getCustomerId(){
    return customerId;
  }
}
