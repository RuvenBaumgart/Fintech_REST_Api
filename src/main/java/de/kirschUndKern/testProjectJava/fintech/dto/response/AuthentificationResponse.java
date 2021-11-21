package de.kirschUndKern.testProjectJava.fintech.dto.response;

public class AuthentificationResponse {
  private final String jwtToken;

  public AuthentificationResponse(
    String jwtToken
  ){
    this.jwtToken = jwtToken;
  };

  public String getJwtToken(){
    return jwtToken;
  }
}
