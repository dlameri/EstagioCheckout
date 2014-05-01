package com.ideais.spring.exceptions;

public class FreightZipCodeException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public FreightZipCodeException() { 
		super(); 
	}
	
	public FreightZipCodeException(String message) { 
		super(message); 
	}  
	
	public FreightZipCodeException(String message, Throwable cause) { 
		super(message, cause); 
	} 
	
	public FreightZipCodeException(Throwable cause) { 
		super(cause); 
	}
	
}