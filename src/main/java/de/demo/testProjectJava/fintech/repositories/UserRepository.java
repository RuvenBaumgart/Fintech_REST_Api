package de.demo.testProjectJava.fintech.repositories;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import de.demo.testProjectJava.fintech.dto.request.UserRequest;
import de.demo.testProjectJava.fintech.entities.UserEntity;

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
