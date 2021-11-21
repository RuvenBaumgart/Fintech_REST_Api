package de.kirschUndKern.testProjectJava.fintech.repositories;

import org.springframework.security.crypto.password.PasswordEncoder;

import de.kirschUndKern.testProjectJava.fintech.dto.request.UserRequest;
import de.kirschUndKern.testProjectJava.fintech.entities.UserEntity;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository  {

  private static UserEntity onlyUser = new UserEntity();
  private final PasswordEncoder passwordEncoder;

  public UserRepository (
    PasswordEncoder passwordEncoder
  ){
    this.passwordEncoder = passwordEncoder;
  }
 
  
  public void saveUser(UserRequest newUserRequest){
   final  String hashedPassword = passwordEncoder.encode(newUserRequest.getPassword());
    onlyUser = new UserEntity(newUserRequest.getUsername(), hashedPassword);
  }

  public UserEntity getUser(){
    return onlyUser;
  }

}
