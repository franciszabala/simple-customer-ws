package com.franciszabala.simplecustomer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franciszabala.simplecustomer.model.Customer;
import com.franciszabala.simplecustomer.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerRepository customerRepository;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return (List<Customer>) customerRepository.findAll();
	}
	
	@Override
	public List<Customer> getCustomersByLastName(String lastName) {
		return customerRepository.findByLastNameIgnoreCase(lastName);
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public List<Customer> saveCustomers(List<Customer> listOfCustomers) {
		customerRepository.save(listOfCustomers);
		return listOfCustomers;
	}
	
	@Override
	public Customer getCustomerById(Long id) {
		return customerRepository.findOne(id);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		 customerRepository.save(customer);
		 return customer;
	}

	@Override
	public boolean deleteCustomerById(Long id) {
		customerRepository.delete(id);
		return true;
	}

	

}
