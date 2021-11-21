package de.kirschUndKern.testProjectJava.fintech.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
  private String firstname;
  private String secondname;
  private String salutation;
  private String dateOfBirth; // MM/dd/yyyy
  private AddressRequest address;
}
