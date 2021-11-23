package de.kirschUndKern.testProjectJava.fintech.entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import de.kirschUndKern.testProjectJava.fintech.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import de.kirschUndKern.testProjectJava.fintech.dto.request.CustomerRequest;
import de.kirschUndKern.testProjectJava.fintech.exceptions.WrongDateFormatException;

@Entity
@Table (name = "customers")
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class CustomerEntity {
  @Id private String id;
  private String firstname;
  private String secondname;
  private String salutation;
  private LocalDate birthday;
  private Integer rating;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private AddressEntity address;

  public CustomerEntity( CustomerRequest cr) throws WrongDateFormatException{
    this.id = UUID.randomUUID().toString();
    this.firstname = cr.getFirstname();
    this.secondname = cr.getSecondname();
    this.salutation = cr.getSalutation();
    this.birthday = CustomerService.convertToDate(cr.getDateOfBirth());
    this.rating = 2;
  }

  public void setAddress(AddressEntity address){
    this.address = address;
  }

}
