package de.kirschUndKern.testProjectJava.fintech.modell.request;

public class UserRequest {
  private final String username;
  private final String password;

  public UserRequest(
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
