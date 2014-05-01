package com.ideais.spring.exceptions;

public class ItemPackageDimensionException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public ItemPackageDimensionException() { 
		super(); 
	}
	
	public ItemPackageDimensionException(String message) { 
		super(message); 
	}  
	
	public ItemPackageDimensionException(String message, Throwable cause) { 
		super(message, cause); 
	} 
	
	public ItemPackageDimensionException(Throwable cause) { 
		super(cause); 
	}
	
}