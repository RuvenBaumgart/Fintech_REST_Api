package de.kirschUndKern.testProjectJava.fintech.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreditUpdateRequest {
  private Long amountInCent;
  private String sourceAccountId;
}
