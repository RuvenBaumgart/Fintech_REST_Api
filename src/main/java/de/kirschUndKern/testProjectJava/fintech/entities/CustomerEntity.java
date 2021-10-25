package de.kirschUndKern.testProjectJava.fintech.entities;

import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import de.kirschUndKern.testProjectJava.fintech.modell.CustomerRequest;
import de.kirschUndKern.testProjectJava.fintech.service.CustomerService;
import de.kirschUndKern.testProjectJava.fintech.exceptions.WrongDateFormatException;

@Entity
@Table (name = "customers")
public class CustomerEntity {
  @Id private String id;
  private String firstname;
  private String secondname;
  private String salutation;
  private LocalDate birthday;
  private Integer rating;

  public CustomerEntity(){
  }

  public CustomerEntity(
    String id,
    String firstname,
    String secondname,
    String salutation,
    LocalDate birthday,
    Integer rating
  ){
    this.id = id;
    this.firstname = firstname;
    this.secondname = secondname;
    this.salutation = salutation;
    this.birthday = birthday;
    this.rating = rating;
  }

  public CustomerEntity( CustomerRequest cr) throws WrongDateFormatException{
    this.id = UUID.randomUUID().toString();
    this.firstname = cr.getFirstname();
    this.secondname = cr.getSecondname();
    this.salutation = cr.getSalutation();
    this.birthday = CustomerService.convertToDate(cr.getDateOfBirth());
    this.rating = 2;
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

  public LocalDate getBirthday() {
    return birthday;
  }

  public Integer getRating() {
    return rating;
  }

  public String getSalutation(){
    return this.salutation;
  }

}
