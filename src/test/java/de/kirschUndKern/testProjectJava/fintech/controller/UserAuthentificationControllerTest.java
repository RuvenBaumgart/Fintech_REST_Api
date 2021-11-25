package de.kirschUndKern.testProjectJava.fintech.controller;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import static org.assertj.core.api.Assertions.*;


import de.kirschUndKern.testProjectJava.fintech.service.UserAuthentificationService;
import de.kirschUndKern.testProjectJava.fintech.service.UserService;

import de.kirschUndKern.testProjectJava.fintech.utilities.JWTTokenUtil;
import de.kirschUndKern.testProjectJava.fintech.utilities.MyAuthenticationEntryPoint;

import net.minidev.json.JSONObject;

//@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(UserAuthentificationController.class)
public class UserAuthentificationControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserAuthentificationService userAuthentificationService;
  
  @MockBean
  private UserService userService;

  @MockBean
  private JWTTokenUtil jwtTokenUtil;

  @MockBean
  private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

  //Test is currently not valid test because the environement settings need to be loaded properly but
  //Currently the filter and security settings can not be loaded
  
  @Test 
  public void unkwonUserShouldNotBeAbleToAuthentificate() throws Exception{
    //given only one users is allowed to log in
    //when unregisteredUser calls api 
    MockHttpServletResponse  response = mockMvc.perform(
      post("/authentification")
      .contentType(MediaType.APPLICATION_JSON)
      .content(getUserCredentials(true).toString()))
      .andReturn().getResponse();
    
      assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
  }

  private Object getUserCredentials(boolean b) {
    if(!b){ // give back a non eligable user
      return unauthorizedUser();
    } else { // give back authorized user
      return authorizedUser();
    }
  }

  private Object authorizedUser(){
    JSONObject authorizedUser = new JSONObject();
    authorizedUser.put("user", "admin");
    authorizedUser.put("password","password");
    return authorizedUser;
  }

  private Object unauthorizedUser(){
    JSONObject unauthorizedUser = new JSONObject();
    unauthorizedUser.put("user","someUser");
    unauthorizedUser.put("password","wrongpassword");
    return unauthorizedUser;
  }




}
