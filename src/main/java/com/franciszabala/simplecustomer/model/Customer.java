package com.franciszabala.simplecustomer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

@Entity
public class Customer {
	@Id @GeneratedValue  
	public Long id;
	public String lastName;
	public String firstName;
	
	@Transient
	public PhoneNumber phoneNumber;
	
	public String getPhoneNumberRaw() {
		return phoneNumberRaw;
	}

	public void setPhoneNumberRaw(String phoneNumberRaw) {
		this.phoneNumberRaw = phoneNumberRaw;
	}
	
	public String phoneNumberRaw;

	
	
	
	public Long getId() {
		return id;
	}
	
	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
