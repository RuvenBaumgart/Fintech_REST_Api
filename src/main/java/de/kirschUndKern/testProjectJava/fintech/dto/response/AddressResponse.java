package de.kirschUndKern.testProjectJava.fintech.dto.response;
import de.kirschUndKern.testProjectJava.fintech.entities.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class AddressResponse {

  private String city;
  private String street;
  private String province;
  private String zipCode;
  private String country;

  public AddressResponse(AddressEntity ae){
    this.city = ae.getCity();
    this.street = ae.getStreet();
    this.province = ae.getProvince();
    this.zipCode = ae.getZipCode();
    this.country = ae.getZipCode();
  }

}
