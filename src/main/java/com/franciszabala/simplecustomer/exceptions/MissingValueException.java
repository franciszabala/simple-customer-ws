package com.franciszabala.simplecustomer.exceptions;

public class MissingValueException extends AppException {

	
	public MissingValueException(int status, int code, String message,
			String developerMessage, String link) {
		super(status, code, message, developerMessage, link);
	}
	
}
