package com.franciszabala.simplecustomer.exceptions;

public class CustomerNotFoundException extends AppException {
	
	private Long customerId;
	
	public CustomerNotFoundException(int status, int code, String message,
			String developerMessage, String link, Long customerId) {
		super(status, code, message, developerMessage, link);
		setCustomerId(customerId);
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
}

