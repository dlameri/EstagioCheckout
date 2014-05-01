package com.ideais.spring.exceptions;

public class FreightException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public FreightException() { 
		super(); 
	}
	
	public FreightException(String message) { 
		super(message); 
	}  
	
	public FreightException(String message, Throwable cause) { 
		super(message, cause); 
	} 
	
	public FreightException(Throwable cause) { 
		super(cause); 
	}
	
}