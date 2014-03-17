package com.ideais.spring.dao.domain.checkout;

public enum CorreiosCodes {
	
	/* construtor do enum (código do serviço, peso máximo, valor default para o peso máximo) */	
	PAC("41106", 30), 
	SEDEX("40010", 30), 
	SEDEX_10("40215", 10), 
	E_SEDEX("81019", 15);
	
    private final String freightServiceCode;
    private final Integer maximumWeight;
    
    CorreiosCodes(String freightServiceCode, Integer maximumWeight) {
        this.freightServiceCode = freightServiceCode; 
        this.maximumWeight = maximumWeight;
    }

	public String getFreightServiceCode() {
		return freightServiceCode;
	}
	
	public Integer maximumWeight() {
		return maximumWeight;
	}
	
}