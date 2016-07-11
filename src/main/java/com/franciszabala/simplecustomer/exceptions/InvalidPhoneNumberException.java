package com.franciszabala.simplecustomer.exceptions;

public class InvalidPhoneNumberException extends AppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6809745678779543865L;

	private String phoneNumber;
	
	public InvalidPhoneNumberException(int status, int code, String message,
			String developerMessage, String link) {
		super(status, code, message, developerMessage, link);
	}
	
	public InvalidPhoneNumberException(int status, int code, String message,
			String developerMessage, String link, String mobileNum) {
		super(status, code, message, developerMessage, link);
		setPhoneNumber(mobileNum);
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
