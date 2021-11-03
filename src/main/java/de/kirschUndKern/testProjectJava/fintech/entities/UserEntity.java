package de.kirschUndKern.testProjectJava.fintech.entities;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class UserEntity {
    private String userId;
    private String username;
    private String hashedPassword;

    public UserEntity(){};
    
    public UserEntity(UserEntity user){
      this.userId = user.getUserId();
      this.username = user.getUsername();
      this.hashedPassword = user.getHashedPassword();
    }

    public UserEntity(
      String username,
      String hashedPassword
    ){
      this.userId = UUID.randomUUID().toString();
      this.username = username;
      this.hashedPassword = hashedPassword;
    }

    public String getUsername(){
      return username;
    }

    public String getHashedPassword(){
      return hashedPassword;
    }

    public String getUserId(){
      return userId;
    }
}
