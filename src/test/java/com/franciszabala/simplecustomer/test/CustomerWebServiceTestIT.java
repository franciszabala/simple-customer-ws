package com.franciszabala.simplecustomer.test;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.franciszabala.simplecustomer.SimpleCustomerWsApplication;
import com.franciszabala.simplecustomer.model.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SimpleCustomerWsApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
// Separate profile for web tests to avoid clashing databases
public class CustomerWebServiceTestIT {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void testGetAllCustomers() throws Exception {
		this.mvc.perform(get("/customer/all")).andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(3)));
	}
	
	@Test
	public void testGetCustomersByLastName() throws Exception {
		String lastName = "drake";
		this.mvc.perform(get("/customer/lastname/"+lastName)).andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$..lastName", Matchers.hasItems("Drake")));
	}
	
	@Test
	public void testSaveCustomer() throws Exception {
		Customer customer = new Customer();
		customer.setFirstName("Tali'Zorah");
		customer.setLastName("nar Rayya");
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(customer);
		
		this.mvc.perform(post("/customer/save")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(jsonInString))
				.andExpect(status().isCreated())
				// id is not initially supplied but is updated after being saved
				.andExpect(jsonPath("$.id", Matchers.not(""))) 
				.andExpect(jsonPath("$.lastName", Matchers.equalToIgnoringCase("nar Rayya")));
	}
	
	@Test
	public void testUpdateCustomer() throws Exception {
		Customer customer = new Customer();
		customer.setFirstName("Anakin");
		customer.setLastName("Skywalker");
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(customer);
		
		//Save first
		MvcResult result = this.mvc.perform(post("/customer/save")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(jsonInString))
				.andExpect(status().isCreated())
				// id is not initially supplied but is updated after being saved
				.andExpect(jsonPath("$.id", Matchers.not(""))) 
				.andExpect(jsonPath("$.lastName", Matchers.equalToIgnoringCase("Skywalker")))
				.andReturn();
		
		String updatedCustomerJson =  result.getResponse().getContentAsString();
		
		Customer updateCustomer = mapper.readValue(updatedCustomerJson, Customer.class);
		updateCustomer.setFirstName("Luke");
		
		updatedCustomerJson = mapper.writeValueAsString(updateCustomer);
		
		this.mvc.perform(put("/customer/update")
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.content(updatedCustomerJson))
					.andExpect(status().isCreated())
					.andExpect(jsonPath("$.id", Matchers.is(updateCustomer.getId().intValue()))) 
					.andExpect(jsonPath("$.firstName", Matchers.equalToIgnoringCase("Luke")));
		
		
	}

}
