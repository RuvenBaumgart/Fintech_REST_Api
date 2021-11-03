package de.kirschUndKern.testProjectJava.fintech.modell.request;

public class AuthentificationRequest {
  private final String username;
  private final String password;

  public AuthentificationRequest(
    String username,
    String password
  ){
    this.username = username;
    this.password = password;
  }

  public String getUsername(){
    return username;
  }

  public String getPassword(){
    return password;
  }
}
