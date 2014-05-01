package com.ideais.spring.exceptions;

public class MissingQuantityStockException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public MissingQuantityStockException() { 
		super(); 
	}
	
	public MissingQuantityStockException(String message) { 
		super(message); 
	}  
	
	public MissingQuantityStockException(String message, Throwable cause) { 
		super(message, cause); 
	} 
	
	public MissingQuantityStockException(Throwable cause) { 
		super(cause); 
	}
	
}