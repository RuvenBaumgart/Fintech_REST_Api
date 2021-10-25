package de.kirschUndKern.testProjectJava.fintech.entities;

import java.sql.Date;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import de.kirschUndKern.testProjectJava.fintech.modell.CustomerRequest;
import de.kirschUndKern.testProjectJava.fintech.entities.AddressEntity;

@Entity
@Table (name = "customers")
public class CustomerEntity {
  @Id private String id;
  private String firstname;
  private String secondname;
  private String salutation;
  private Date birthday;
  private String addressId;
  private Integer rating;

  public CustomerEntity(){
  }

  public CustomerEntity( CustomerRequest cr){
    this.id = UUID.randomUUID().toString();
    this.firstname = cr.getFirstname();
    this.secondname = cr.getSecondname();
    this.salutation = cr.getSalutation();
    this.birthday = createBirthday(cr.getDateOfBirthString());
    this.addressId = UUID.randomUUID().toString();
    this.rating = 2;
  }

  private Date createBirthday(String dateOfBirthString) {
    return null;
  }

  public String getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getSecondname() {
    return secondname;
  }

  public Date getBirthday() {
    return birthday;
  }

  public String getAddressId() {
    return addressId;
  }

  public Integer getRating() {
    return rating;
  }

  public String getSalutation(){
    return this.salutation;
  }

}
