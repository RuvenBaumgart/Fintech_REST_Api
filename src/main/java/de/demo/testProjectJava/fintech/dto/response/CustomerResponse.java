package de.demo.testProjectJava.fintech.dto.response;

import de.demo.testProjectJava.fintech.entities.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class CustomerResponse {
  private String customerId;
  private String firstname;
  private String secondname;
  private String salutation;
  private Integer rating;
  private AddressResponse address;

  public CustomerResponse(CustomerEntity customer){
    this.customerId = customer.getId();
    this.firstname = customer.getFirstname();
    this.secondname = customer.getSecondname();
    this.salutation = customer.getSalutation();
    this.rating = customer.getRating();
    this.address = new AddressResponse(customer.getAddress());
  }
}
