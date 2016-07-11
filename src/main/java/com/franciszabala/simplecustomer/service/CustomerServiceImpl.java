package com.franciszabala.simplecustomer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franciszabala.simplecustomer.exceptions.AppException;
import com.franciszabala.simplecustomer.exceptions.CustomerNotFoundException;
import com.franciszabala.simplecustomer.exceptions.MissingValueException;
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
	public Customer saveCustomer(Customer customer) throws AppException {
		customerValidator(customer);
		return customerRepository.save(customer);
	}

	@Override
	public List<Customer> saveCustomers(List<Customer> listOfCustomers) {
		customerRepository.save(listOfCustomers);
		return listOfCustomers;
	}
	
	@Override
	public Customer getCustomerById(Long id) throws AppException {
		Customer customer = customerRepository.findOne(id);
		if (customer == null) {
			throw new CustomerNotFoundException(400, 4010, "Customer not found.", "", "", id);
		}
		return customer;
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

	private boolean customerValidator(Customer customer) throws AppException {
		
		if (customer.getLastName() == null || customer.getLastName().isEmpty()) {
			throw new MissingValueException(400, 4001, "Last name should not be empty", "", "");
		}
		
		if (customer.getFirstName() == null || customer.getFirstName().isEmpty()) {
			throw new MissingValueException(400, 4002, "First name should not be empty", "", "");
		}
		
		return true;
	}
		

}
