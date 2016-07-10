package com.franciszabala.simplecustomer.repository;

import org.springframework.data.repository.CrudRepository;

import com.franciszabala.simplecustomer.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{

}
