package de.kirschUndKern.testProjectJava.fintech.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import de.kirschUndKern.testProjectJava.fintech.modell.request.AuthentificationRequest;
import de.kirschUndKern.testProjectJava.fintech.modell.response.AuthentificationResponse;
import de.kirschUndKern.testProjectJava.fintech.utilities.JWTTokenUtil;


@Service
public class UserAuthentificationService {

  private final AuthenticationManager authenticationManager;
  private final UserService userService;
  private final JWTTokenUtil jwtTokenUtil;

  public UserAuthentificationService(
    AuthenticationManager authenticationManager,
    UserService userService,
    JWTTokenUtil jwtTokenUtil
  ){
    this.authenticationManager = authenticationManager;
    this.userService = userService;
    this.jwtTokenUtil = jwtTokenUtil;
  }
  
  public Optional<AuthentificationResponse> authentificate(AuthentificationRequest request) throws Exception {
    authenticate(request.getUsername(), request.getPassword());
    final UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
    final String token = jwtTokenUtil.generateToken(userDetails);
    return Optional.of(new AuthentificationResponse(token));    
  }

  private void authenticate(String username, String password) throws Exception {
    try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
  }
  
}
