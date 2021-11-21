package de.kirschUndKern.testProjectJava.fintech.entities;

import java.util.UUID;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Component
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class UserEntity {
    private String userId;
    private String username;
    private String hashedPassword;

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
}
