package de.kirschUndKern.testProjectJava.fintech.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import de.kirschUndKern.testProjectJava.fintech.service.CreditService;
import de.kirschUndKern.testProjectJava.fintech.service.UserService;
import de.kirschUndKern.testProjectJava.fintech.utilities.JWTRequestFilter;
import de.kirschUndKern.testProjectJava.fintech.utilities.MyAuthenticationEntryPoint;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.json.JSONObject;

import static org.assertj.core.api.Assertions.*;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(CreditController.class)
public class CreditControllerTest {
  
  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  private CreditService creditService;
   
  @MockBean
  private UserService userService;
  @MockBean
  private JWTRequestFilter jwtRequestFilter;
  @MockBean
  private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

  @Test
  public void getAllCreditsReturnStatusIsOk() throws Exception {
    MockHttpServletResponse response = mockMvc.perform(
      get("/credits")).andReturn().getResponse();
    
  assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
  }

  @Test
  public void putCreditWithIdReturnsStatusIsOk() throws Exception{
    MockHttpServletResponse response = mockMvc.perform(
      put("/credits/someId")
      .contentType(MediaType.APPLICATION_JSON)
      .content(getCreditRequest().toString())
      ).andReturn().getResponse();
    
      assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
  }

  @Test
  public void postCreditsWithIdReturnsStatusIsOk() throws Exception{
    MockHttpServletResponse response = mockMvc.perform(
      post("/credits/someId")
      .contentType(MediaType.APPLICATION_JSON)
      .content(getCreditUpdateRequest().toString())
      ).andReturn().getResponse();
    
      assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
  }

  @Test
  public void getCreditWithIdReturnsStatusIsOk() throws Exception{
    MockHttpServletResponse response = mockMvc.perform(
      get("/credits/someId")).andReturn().getResponse();
    
      assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
  }

  private JSONObject getCreditRequest() throws Exception{
    JSONObject json = new JSONObject();
    json.put("creditAmountInCents", 0L);
    json.put("runtimeInMonths", 0L);
    return json;
  }

  private JSONObject getCreditUpdateRequest() throws Exception{
    JSONObject json = new JSONObject();
    json.put("amountInCent", 0L);
    json.put("sourceAccountId", "someId");
    return json;
   }

  
}
