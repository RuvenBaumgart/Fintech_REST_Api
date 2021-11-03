package de.kirschUndKern.testProjectJava.fintech.entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;

import de.kirschUndKern.testProjectJava.fintech.modell.request.AddressRequest;

import javax.persistence.Id;

@Entity
@Table (name = "addresses")
public class AddressEntity {
  @Id private String id; 
  private String customerId;
  private String city;
  private String street;
  private String province;
  private String zipCode;
  private String country;


  public AddressEntity(){
  }

  public AddressEntity(
    String id, 
    String customerId, 
    String city, 
    String street, 
    String province, 
    String zipCode,
    String country) {
      this.id = id;
      this.customerId = customerId;
      this.city = city;
      this.street = street;
      this.province = province;
      this.zipCode = zipCode;
      this.country = country;
  }

  public AddressEntity(String customerId){
    this.id = UUID.randomUUID().toString();
    this.customerId = customerId;
    this.city = null;
    this.street = null;
    this.province = null;
    this.zipCode = null;
    this.country = null;
  }

  public AddressEntity(AddressRequest ar, String customerId){
    this.id = UUID.randomUUID().toString();
    this.customerId = customerId;
    this.city = ar.getCity();
    this.street = ar.getStreet();
    this.province = ar.getProvince();
    this.zipCode = ar.getZipCode();
    this.country = ar.getCountry();
  }
  
  public String getId() {
    return id;
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
