package com.franciszabala.simplecustomer.model;

import com.fasterxml.jackson.annotation.JsonSetter;

public class ResponseObject {
	private String code;
	private String status;
	private String remarks;

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
    @JsonSetter("error")
    public void setErrorMessage(String remarks) {
        if (this.status == null) {
            setRemarks(remarks);
        }
    }
    
    @JsonSetter("msg")
    public void setErrorMessageUsedByGLA(String remarks) {
        if (this.status == null) {
            setRemarks(remarks);
        }
    }
    
    @JsonSetter("code")
    public void setErrorStatusUsedByGLA(String status) {
        if (this.status == null) {
            setStatus(status);
        }
    }
    

	
	@Override
	public String toString() {
		return "ResponseObject [status=" + status + ", remarks=" + remarks
				+ "]";
	}
	
	
	public ResponseObject(String status, String remarks) {
		setStatus(status);
		setRemarks(remarks);
	}
	public ResponseObject() {
		super();
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
