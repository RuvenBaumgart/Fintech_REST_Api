package de.kirschUndKern.testProjectJava.fintech.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class AddressRequest {
  private String city;
  private String street;
  private String province;
  private String zipCode;
  private String country;
}
