package com.franciszabala.simplecustomer.test;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.franciszabala.simplecustomer.SimpleCustomerWsApplication;
import com.franciszabala.simplecustomer.model.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SimpleCustomerWsApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class CustomerWebServiceErrorsITTest {
	
	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void testSaveCustomer_emptyLastName() throws Exception {
		Customer customer = new Customer();
		customer.setFirstName("Tali'Zorah");
		customer.setLastName("");
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(customer);
		
		this.mvc.perform(post("/customer/save")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(jsonInString))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.code", Matchers.equalTo("4000")));
	}
	
	@Test
	public void testSaveCustomer_emptyFirstName() throws Exception {
		Customer customer = new Customer();
		customer.setFirstName("Tali'Zorah");
		customer.setLastName("");
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(customer);
		
		this.mvc.perform(post("/customer/save")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(jsonInString))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.code", Matchers.equalTo("4001")));
	}
}
