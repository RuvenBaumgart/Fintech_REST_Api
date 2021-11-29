package de.demo.testProjectJava.fintech.dto.response;

import de.demo.testProjectJava.fintech.entities.AddressEntity;
import de.demo.testProjectJava.fintech.entities.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class CustomerAndAddressResponse {
  private String customerId;
  private String firstname;
  private String secondname;
  private String salutation;
  private Integer rating;
  private AddressResponse address;

  public CustomerAndAddressResponse(CustomerEntity customer, AddressEntity address){
    this.customerId = customer.getId();
    this.firstname = customer.getFirstname();
    this.secondname = customer.getSecondname();
    this.salutation = customer.getSalutation();
    this.rating = customer.getRating();
    this.address = new AddressResponse(address);
  }
}
