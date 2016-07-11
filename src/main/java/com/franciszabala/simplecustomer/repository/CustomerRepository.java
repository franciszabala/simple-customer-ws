package com.franciszabala.simplecustomer.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.franciszabala.simplecustomer.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
	
	List<Customer> findByLastNameIgnoreCase(String lastName);

}
