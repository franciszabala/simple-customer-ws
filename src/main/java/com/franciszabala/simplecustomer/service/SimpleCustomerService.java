package com.franciszabala.simplecustomer.service;

import java.util.List;

import com.franciszabala.simplecustomer.model.Customer;

public interface SimpleCustomerService {
	public List<Customer> getAllCustomers();
	public Customer saveCustomer(Customer customer);
	public Customer getCustomerById(Long id);
}
