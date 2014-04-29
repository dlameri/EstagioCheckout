package com.ideais.spring.domain.checkout;

import java.math.BigDecimal;

public enum CorreiosCodes {
	
	PAC("41106", 30, new BigDecimal(130.00)), 
	SEDEX("40010", 30, new BigDecimal(130.00)), 
	SEDEX_10("40215", 10, new BigDecimal(130.00)), 
	E_SEDEX("81019", 15, new BigDecimal(130.00));
	
    private final String freightServiceCode;
    private final Integer maximumWeight;
    private final BigDecimal maximumFreight;
    
    CorreiosCodes(String freightServiceCode, Integer maximumWeight, BigDecimal maximumFreight) {
        this.freightServiceCode = freightServiceCode; 
        this.maximumWeight = maximumWeight;
        this.maximumFreight = maximumFreight;
    }

	public String getFreightServiceCode() {
		return freightServiceCode;
	}
	
	public Integer getMaximumWeight() {
		return maximumWeight;
	}

	public BigDecimal getMaximumFreight() {
		return maximumFreight;
	}
	
}