package de.kirschUndKern.testProjectJava.fintech.controller;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import de.kirschUndKern.testProjectJava.fintech.service.AccountService;
import de.kirschUndKern.testProjectJava.fintech.service.TransactionService;
import de.kirschUndKern.testProjectJava.fintech.service.UserService;
import de.kirschUndKern.testProjectJava.fintech.utilities.JWTRequestFilter;
import de.kirschUndKern.testProjectJava.fintech.utilities.MyAuthenticationEntryPoint;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @MockBean 
    private AccountService accountService;
    
    @MockBean
    private UserService userService;
    @MockBean
    private JWTRequestFilter jwtRequestFilter;
    @MockBean
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Test 
    public void getAllTransactionsReturnsStatusIsOk() throws Exception{
        MockHttpServletResponse response = mockMvc.perform(
            get("/transactions")
            .param("date", "somedate"))
            .andReturn()
            .getResponse();
        
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test 
    public void getAllTransactionsForIDReturnsStatusIsOk() throws Exception{
        MockHttpServletResponse response = mockMvc.perform(
            get("/transactions/someId")
            .param("pageno", "1")
            .param("pagesize", "10")
            .param("sortby", "sortBy"))
            .andReturn()
            .getResponse();
        
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

}
