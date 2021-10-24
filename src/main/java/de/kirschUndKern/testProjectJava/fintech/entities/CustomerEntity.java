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
  private final String firstname;
  private final String secondname;
  private final String salutation;
  private final Date birthday;
  private final AddressEntity address;
  private final Integer rating;

  public CustomerEntity(){
    this.id = UUID.randomUUID().toString();
    this.firstname = null;
    this.secondname = null;
    this.salutation = null;
    this.birthday = null;
    this.address = new AddressEntity(this.id);
    this.rating= 2;
  }

  public CustomerEntity( CustomerRequest cr){
    this.id = UUID.randomUUID().toString();
    this.firstname = cr.getFirstname();
    this.secondname = cr.getSecondname();
    this.salutation = cr.getSalutation();
    this.birthday = createBirthday(cr.getDateOfBirthString());
    this.address = new AddressEntity(cr.getAddress(), this.id);
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

  public AddressEntity getAddress() {
    return address;
  }

  public Integer getRating() {
    return rating;
  }

  public String getSalutation(){
    return this.salutation;
  }

}
