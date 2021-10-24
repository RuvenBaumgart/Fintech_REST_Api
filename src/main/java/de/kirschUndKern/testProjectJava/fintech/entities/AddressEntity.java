package de.kirschUndKern.testProjectJava.fintech.entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import de.kirschUndKern.testProjectJava.fintech.modell.AddressRequest;

@Entity
@Table (name = "addresses")
public class AddressEntity {
  @Id private final String id; 
  private final String customerId;
  private final String city;
  private final String street;
  private final String province;
  private final String zipCode;
  private final String country;

  public AddressEntity(){
    this.id = UUID.randomUUID().toString();
    this.customerId = null;
    this.city = null;
    this.street = null;
    this.province = null;
    this.zipCode = null;
    this.country = null;
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
