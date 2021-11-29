package de.demo.testProjectJava.fintech.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreditRequest {
  private Long creditAmountInCents;
  private Long runtimeInMonths;
}
