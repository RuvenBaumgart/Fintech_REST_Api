package de.kirschUndKern.testProjectJava.fintech.modell.request;


public class CustomerRequest {
  private final String firstname;
  private final String secondname;
  private final String salutation;
  private final String dateOfBirth; // MM/dd/yyyy
  private final AddressRequest address;

  public CustomerRequest(
    String firstname,
    String secondname,
    String salutation,
    String dateOfBirth,
    AddressRequest address
  ){
    this.firstname = firstname;
    this.secondname = secondname;
    this.salutation = salutation;
    this.dateOfBirth = dateOfBirth;
    this.address = address;
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
 
  public String getDateOfBirth() {
    return dateOfBirth;
  }
  
  public AddressRequest getAddress() {
    return address;
  }
}
