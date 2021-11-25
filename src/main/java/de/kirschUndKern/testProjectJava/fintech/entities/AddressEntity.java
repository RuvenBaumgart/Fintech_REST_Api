package de.kirschUndKern.testProjectJava.fintech.entities;

import java.util.UUID;


import javax.persistence.Entity;
import javax.persistence.Table;



import de.kirschUndKern.testProjectJava.fintech.dto.request.AddressRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Table (name = "addresses")
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class AddressEntity {
  @Id private String id; 
  private String CustomerId;
  private String city;
  private String street;
  private String province;
  private String zipCode;
  private String country;
  @OneToOne(mappedBy = "address")
  private CustomerEntity customer;
 

  public AddressEntity(String customerId){
    this.id = UUID.randomUUID().toString();
    this.CustomerId = customerId;
    this.city = null;
    this.street = null;
    this.province = null;
    this.zipCode = null;
    this.country = null;
    new CustomerEntity();
  }

  public AddressEntity(AddressRequest ar){
    this.id = UUID.randomUUID().toString();
    this.city = ar.getCity();
    this.street = ar.getStreet();
    this.province = ar.getProvince();
    this.zipCode = ar.getZipCode();
    this.country = ar.getCountry();
    new CustomerEntity();
  }


}
