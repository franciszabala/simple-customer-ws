package com.franciszabala.simplecustomer.service;

import java.util.List;

import com.franciszabala.simplecustomer.model.Customer;

public interface CustomerService {
	public List<Customer> getAllCustomers();
	public List<Customer> getCustomersByLastName(String lastName);
	public Customer getCustomerById(Long id);
	
	public Customer saveCustomer(Customer customer);
	public List<Customer> saveCustomers(List<Customer> listOfCustomers);
	
	public Customer updateCustomer(Customer customer);
	public boolean deleteCustomerById(Long id);
	
	

}
