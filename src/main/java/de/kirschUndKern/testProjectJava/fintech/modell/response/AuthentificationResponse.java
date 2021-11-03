package de.kirschUndKern.testProjectJava.fintech.modell.response;

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
