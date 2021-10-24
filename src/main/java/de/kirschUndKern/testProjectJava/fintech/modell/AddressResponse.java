package de.kirschUndKern.testProjectJava.fintech.modell;
import de.kirschUndKern.testProjectJava.fintech.entities.AddressEntity;

public class AddressResponse {
  private final String customerId;
  private final String city;
  private final String street;
  private final String province;
  private final String zipCode;
  private final String country;


  public AddressResponse(
    String customerId,
    String city, 
    String street, 
    String province, 
    String zipCode,
    String country) {
      this.customerId = customerId;
      this.city = city;
      this.street = street;
      this.province = province;
      this.zipCode = zipCode;
      this.country = country;
  }
  public AddressResponse(AddressEntity ae){
    this.customerId = ae.getCustomerId();
    this.city = ae.getCity();
    this.street = ae.getStreet();
    this.province = ae.getProvince();
    this.zipCode = ae.getZipCode();
    this.country = ae.getZipCode();
  }
  
  public String getCustomerId() {
    return customerId;
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
