package com.franciszabala.simplecustomer.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.franciszabala.simplecustomer.model.Customer;
import com.franciszabala.simplecustomer.service.SimpleCustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerResource {

	
	private SimpleCustomerService simpleCustomerService;
	
	@Autowired
	public CustomerResource(SimpleCustomerService simpleCustomerService) {
		super();
		this.simpleCustomerService = simpleCustomerService;
	}


	@RequestMapping("all")
	@ResponseBody
	@Transactional(readOnly = true)
	public List<Customer> getAllCustomers() {
		return this.simpleCustomerService.getAllCustomers();
	}
}
