package de.demo.testProjectJava.fintech.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
  private Long amountInCent;
  private String sourceAccountId;
  private String destinationAccountId;
  private String message;
}
