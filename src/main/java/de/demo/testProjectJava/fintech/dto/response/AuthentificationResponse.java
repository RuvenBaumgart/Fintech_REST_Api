package de.demo.testProjectJava.fintech.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class AuthentificationResponse {
  private String jwtToken;
}
