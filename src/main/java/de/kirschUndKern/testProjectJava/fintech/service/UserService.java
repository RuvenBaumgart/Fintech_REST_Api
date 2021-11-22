package de.kirschUndKern.testProjectJava.fintech.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.kirschUndKern.testProjectJava.fintech.dto.request.UserRequest;
import de.kirschUndKern.testProjectJava.fintech.entities.UserEntity;
import de.kirschUndKern.testProjectJava.fintech.repositories.UserRepository;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor

public class UserService implements UserDetailsService{
  private final String user = "admin";
  private final String password = "password";
	
  @Autowired 
	private UserRepository userRepository;

  @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    userRepository.saveUser(new UserRequest(user, password));
    UserEntity user = userRepository.getUser();
		if (user.getUsername().equals(username)) {
			return new User(user.getUsername(), user.getHashedPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}