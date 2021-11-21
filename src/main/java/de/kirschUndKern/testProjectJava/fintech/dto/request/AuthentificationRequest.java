package de.kirschUndKern.testProjectJava.fintech.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthentificationRequest {
  private String username;
  private String password;
}
