package de.kirschUndKern.testProjectJava.fintech.modell;

import de.kirschUndKern.testProjectJava.fintech.entities.AddressEntity;
import de.kirschUndKern.testProjectJava.fintech.entities.CustomerEntity;


public class CustomerWithAddressResponse {
  private final String firstname;
  private final String secondname;
  private final String salutation;
  private final AddressResponse address;

  public CustomerWithAddressResponse(
    String firstname, 
    String secondname, 
    String salutation, 
    AddressResponse address
    ) {
      this.firstname = firstname;
      this.secondname = secondname;
      this.salutation = salutation;
      this.address = address;
  }

  public CustomerWithAddressResponse(CustomerEntity customer, AddressEntity address){
    this.firstname = customer.getFirstname();
    this.secondname = customer.getSecondname();
    this.salutation = customer.getSalutation();
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
}
