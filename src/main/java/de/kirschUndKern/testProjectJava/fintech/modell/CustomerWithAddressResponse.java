package de.kirschUndKern.testProjectJava.fintech.modell;

import de.kirschUndKern.testProjectJava.fintech.entities.CustomerEntity;
import de.kirschUndKern.testProjectJava.fintech.modell.AddressResponse;

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

  public CustomerWithAddressResponse(CustomerEntity ce){
    this.firstname = ce.getFirstname();
    this.secondname = ce.getSecondname();
    this.salutation = ce.getSalutation();
    this.address = new AddressResponse (ce.getAddress());
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
