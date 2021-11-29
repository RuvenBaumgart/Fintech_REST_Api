package de.demo.testProjectJava.fintech.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;


import de.demo.testProjectJava.fintech.service.CustomerService;
import de.demo.testProjectJava.fintech.service.UserService;
import de.demo.testProjectJava.fintech.utilities.JWTRequestFilter;
import de.demo.testProjectJava.fintech.utilities.MyAuthenticationEntryPoint;
import net.minidev.json.JSONObject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.assertj.core.api.Assertions.*;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;
    
    @MockBean
    private UserService userService;
    @MockBean
    private JWTRequestFilter jwtRequestFilter;
    @MockBean
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Test
    public void getCustomerSecondNameReturnsStatusIsOk()throws Exception{
        MockHttpServletResponse response = mockMvc.perform(
            get("/customers/anySecondname")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void getCustomerGroupedByReturnStatusIsOk()throws Exception{
        MockHttpServletResponse response = mockMvc.perform(
            get("/customers/groupedBy")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void getCustomesReturnsStatusIsOk()throws Exception{
        MockHttpServletResponse response = mockMvc.perform(
            get("/customers")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void addNewCustomerReturnedStatusIsOk() throws Exception{
        MockHttpServletResponse response = mockMvc.perform(
            post("/customers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getCustomerRequest().toString()))
            .andReturn().getResponse();
        
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    private Object getCustomerRequest() {
        JSONObject customerRequest = new JSONObject();
        JSONObject addressRequest = new JSONObject();
        addressRequest.put("city","Malibu");
        addressRequest.put("street","Malibu Point 10880");
        addressRequest.put("province","California");
        addressRequest.put("zipCode","90265");
        addressRequest.put("country","United States");

        customerRequest.put("firstname", "Tony");
        customerRequest.put("secondname", "Stark");
        customerRequest.put("salutation", "Mr");
        customerRequest.put("dateOfBirth","19/05/1970");
        customerRequest.put("address", addressRequest);
        return customerRequest;
    }
}
