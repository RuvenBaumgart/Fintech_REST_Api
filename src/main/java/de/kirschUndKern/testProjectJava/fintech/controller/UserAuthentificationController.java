package de.kirschUndKern.testProjectJava.fintech.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.kirschUndKern.testProjectJava.fintech.modell.request.AuthentificationRequest;
import de.kirschUndKern.testProjectJava.fintech.modell.response.AuthentificationResponse;
import de.kirschUndKern.testProjectJava.fintech.service.UserAuthentificationService;

@RestController
public class UserAuthentificationController{
  private final UserAuthentificationService userAuthentificationService;
  
  public UserAuthentificationController(
    UserAuthentificationService userAuthentificationService
  ){
    this.userAuthentificationService = userAuthentificationService;
  }

  @PostMapping("/authentification")
  public ResponseEntity<AuthentificationResponse> doAuthentification(
    @RequestBody AuthentificationRequest request
  ) throws Exception{
    Optional<AuthentificationResponse> response = userAuthentificationService.authentificate(request);
    if(response.isPresent()){
      return ResponseEntity.ok(response.get());
    } else {
      return ResponseEntity.notFound().build();
    }
    
  }

}