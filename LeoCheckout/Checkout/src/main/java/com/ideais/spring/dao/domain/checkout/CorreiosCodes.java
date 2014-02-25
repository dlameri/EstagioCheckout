package com.ideais.spring.dao.domain.checkout;

public enum CorreiosCodes {
	PAC("41106"), 
	SEDEX("40010"), 
	SEDEX_10("40215"), 
	E_SEDEX("81019");
	
    private final String freightServiceCode;
    
    CorreiosCodes(String freightServiceCode) {
        this.freightServiceCode = freightServiceCode; 
    }

	public String getFreightServiceCode() {
		return freightServiceCode;
	}
	
}