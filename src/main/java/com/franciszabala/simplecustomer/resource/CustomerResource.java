package com.franciszabala.simplecustomer.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.franciszabala.simplecustomer.exceptions.AppException;
import com.franciszabala.simplecustomer.exceptions.CustomerNotFoundException;
import com.franciszabala.simplecustomer.exceptions.InvalidPhoneNumberException;
import com.franciszabala.simplecustomer.exceptions.MissingValueException;
import com.franciszabala.simplecustomer.model.Customer;
import com.franciszabala.simplecustomer.model.ResponseObject;
import com.franciszabala.simplecustomer.service.CustomerService;



@RestController
@RequestMapping("/customer")
public class CustomerResource {

	
	private CustomerService simpleCustomerService;
	
	@Autowired
	public CustomerResource(CustomerService simpleCustomerService) {
		super();
		this.simpleCustomerService = simpleCustomerService;
	}


	@RequestMapping("all")
	@ResponseBody
	@Transactional(readOnly = true)
	public List<Customer> getAllCustomers() {
		return simpleCustomerService.getAllCustomers();
	}
	
	@RequestMapping("id/{id}")
	@ResponseBody
	@Transactional(readOnly = true)
	public Customer getCustomersByLastName(@PathVariable Long id) throws AppException {
		return simpleCustomerService.getCustomerById(id);
	}
	
	@RequestMapping("lastname/{lastName}")
	@ResponseBody
	@Transactional(readOnly = true)
	public List<Customer> getCustomersByLastName(@PathVariable String lastName) {
		return simpleCustomerService.getCustomersByLastName(lastName);
	}
	
	@RequestMapping(value = "save/list", method = RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Customer>  saveCustomers(@RequestBody List<Customer> listOfCustomers) {
		return simpleCustomerService.saveCustomers(listOfCustomers);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "save", method = RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Customer saveCustomer(@RequestBody Customer customer) throws AppException {
		return simpleCustomerService.saveCustomer(customer);
	}
	

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "update", method = RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Customer updateCustomer(@RequestBody Customer customer) {
		return simpleCustomerService.updateCustomer(customer);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "delete/{customerId}", method = RequestMethod.DELETE)
	public Map<String,Object> updateCustomer(@PathVariable Long customerId) {
		simpleCustomerService.deleteCustomerById(customerId);
		Map<String, Object> returnObject =  new HashMap<String,Object>();
		returnObject.put("status", "success");
		returnObject.put("remarks", "Deleted customer id: "+customerId.intValue());
		return returnObject;
	}
	
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	 @ExceptionHandler(MissingValueException.class)
	 public ResponseObject handleMissingValue(MissingValueException nne) {
		 ResponseObject responseObject = new ResponseObject();
		 responseObject.setCode(String.valueOf(nne.getCode()));
		 responseObject.setStatus(String.valueOf(nne.getStatus()));
		 responseObject.setRemarks(nne.getMessage());
		 return responseObject;
	 }
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	 @ExceptionHandler(CustomerNotFoundException.class)
	 public ResponseObject handleCustomerNotFound(CustomerNotFoundException nne) {
		 ResponseObject responseObject = new ResponseObject();
		 responseObject.setCode(String.valueOf(nne.getCode()));
		 responseObject.setStatus(String.valueOf(nne.getStatus()));
		 responseObject.setRemarks("Customer with ID " + nne.getCustomerId() + " not found.");
		 return responseObject;
	 }
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	 @ExceptionHandler(InvalidPhoneNumberException.class)
	 public ResponseObject handleInvalidPhoneNumber(InvalidPhoneNumberException nne) {
		 ResponseObject responseObject = new ResponseObject();
		 responseObject.setCode(String.valueOf(nne.getCode()));
		 responseObject.setStatus(String.valueOf(nne.getStatus()));
		 responseObject.setRemarks(nne.getPhoneNumber() +" is not valid.");
		 return responseObject;
	 }
}
