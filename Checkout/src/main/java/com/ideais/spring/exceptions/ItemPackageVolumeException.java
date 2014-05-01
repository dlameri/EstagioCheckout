package com.ideais.spring.exceptions;

public class ItemPackageVolumeException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public ItemPackageVolumeException() { 
		super(); 
	}
	
	public ItemPackageVolumeException(String message) { 
		super(message); 
	}  
	
	public ItemPackageVolumeException(String message, Throwable cause) { 
		super(message, cause); 
	} 
	
	public ItemPackageVolumeException(Throwable cause) { 
		super(cause); 
	}
	
}