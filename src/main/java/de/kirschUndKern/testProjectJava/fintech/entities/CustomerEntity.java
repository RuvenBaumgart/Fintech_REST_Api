package de.kirschUndKern.testProjectJava.fintech.entities;

import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
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

  public CustomerEntity( CustomerRequest cr) throws WrongDateFormatException{
    this.id = UUID.randomUUID().toString();
    this.firstname = cr.getFirstname();
    this.secondname = cr.getSecondname();
    this.salutation = cr.getSalutation();
    this.birthday = CustomerService.convertToDate(cr.getDateOfBirth());
    this.rating = 2;
  }

}
