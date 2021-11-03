package de.kirschUndKern.testProjectJava.fintech.modell.request;

public class AddressRequest {
  private final String city;
  private final String street;
  private final String province;
  private final String zipCode;
  private final String country;


  public AddressRequest(
    String city, 
    String street, 
    String province, 
    String zipCode,
    String country) {
      this.city = city;
      this.street = street;
      this.province = province;
      this.zipCode = zipCode;
      this.country = country;
  }
  
  public String getCity() {
    return city;
  }
  public String getStreet() {
    return street;
  }
  public String getProvince() {
    return province;
  }
  public String getZipCode() {
    return zipCode;
  }
  public String getCountry() {
    return country;
  }
}
