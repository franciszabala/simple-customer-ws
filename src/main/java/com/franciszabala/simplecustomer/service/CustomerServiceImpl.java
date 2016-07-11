package com.franciszabala.simplecustomer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franciszabala.simplecustomer.exceptions.AppException;
import com.franciszabala.simplecustomer.exceptions.CustomerNotFoundException;
import com.franciszabala.simplecustomer.exceptions.InvalidPhoneNumberException;
import com.franciszabala.simplecustomer.exceptions.MissingValueException;
import com.franciszabala.simplecustomer.model.Customer;
import com.franciszabala.simplecustomer.repository.CustomerRepository;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerRepository customerRepository;
	
	private PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
	
	
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
		
		if (customer.getPhoneNumberRaw() == null || customer.getPhoneNumberRaw().isEmpty()) {
			throw new MissingValueException(400, 4003, "Phone number should not be empty", "", "");
		}
		
		try {
			PhoneNumber contactNumObj = phoneUtil.parse(customer.getPhoneNumberRaw(), "");
			customer.setPhoneNumber(contactNumObj);
		} catch (NumberParseException e) {
			throw new InvalidPhoneNumberException(400, 4004, "Invalid phone number", "", "", customer.getPhoneNumberRaw());
		}
		
		
		return true;
	}
		

}
