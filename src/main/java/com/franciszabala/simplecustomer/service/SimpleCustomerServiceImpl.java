package com.franciszabala.simplecustomer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franciszabala.simplecustomer.model.Customer;
import com.franciszabala.simplecustomer.repository.CustomerRepository;

@Service
public class SimpleCustomerServiceImpl implements SimpleCustomerService {
	
	private CustomerRepository customerRepository;
	
	@Autowired
	public SimpleCustomerServiceImpl(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomerById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
