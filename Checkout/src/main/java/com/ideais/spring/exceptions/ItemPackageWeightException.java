package com.ideais.spring.exceptions;

public class ItemPackageWeightException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public ItemPackageWeightException() { 
		super(); 
	}
	
	public ItemPackageWeightException(String message) { 
		super(message); 
	}  
	
	public ItemPackageWeightException(String message, Throwable cause) { 
		super(message, cause); 
	} 
	
	public ItemPackageWeightException(Throwable cause) { 
		super(cause); 
	}
	
}