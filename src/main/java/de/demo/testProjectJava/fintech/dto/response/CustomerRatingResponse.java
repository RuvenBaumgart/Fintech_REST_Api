
package de.demo.testProjectJava.fintech.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class CustomerRatingResponse {
  private String firstname;
  private String secondname;
  private String salutation;
  private Integer rating;
}
