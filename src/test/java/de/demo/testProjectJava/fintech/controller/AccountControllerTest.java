package de.demo.testProjectJava.fintech.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import de.demo.testProjectJava.fintech.service.AccountService;
import de.demo.testProjectJava.fintech.service.UserService;
import de.demo.testProjectJava.fintech.utilities.JWTRequestFilter;
import de.demo.testProjectJava.fintech.utilities.MyAuthenticationEntryPoint;
import de.demo.testProjectJava.fintech.testConfiguration.TestSecurityConfig;

/*
* import the security class did not solved the error "Argument must not be null"
* adding the AutoConfiureMockMvc(addFilters = false) is bypassing the filtering and is not causing the exception to be thrown anymore
*/
@Import(TestSecurityConfig.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(AccountController.class)
public class AccountControllerTest{
  
  @Autowired
  private MockMvc mvc;

  @MockBean
  private AccountService accountService;
  @MockBean
  private UserService userService;
  @MockBean
  private JWTRequestFilter jwtRequestFilter;
  @MockBean
  private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

  @Test
  public void callTheAccountControllerEndpointAndGetStatusOk() throws Exception {
    MockHttpServletResponse response = mvc.perform(
      get("/accounts/id")).andReturn().getResponse(); 

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
  }

    @Test
    public void callToGetAccountsBalanceIsReturningStatusOk() throws Exception {
      MockHttpServletResponse response = mvc.perform(
        get("/accounts/balance")).andReturn().getResponse(); 

      assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void callToGetAccountsIdBalanceIsReturningStatusOk() throws Exception {
      MockHttpServletResponse response = mvc.perform(
        get("/accounts/someId/balance")).andReturn().getResponse(); 

      assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void callToPostAccountsIdReturningStatusOk() throws Exception {
      MockHttpServletResponse response = mvc.perform(
        post("/accounts/someId")).andReturn().getResponse(); 

      assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    
  
}
