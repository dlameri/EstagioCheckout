package com.ideais.spring.domain.checkout;

import java.math.BigDecimal;

public enum CorreiosCodes {
	
	PAC("41106", 30, new BigDecimal(130.00), new BigDecimal(20.0), "3"), 
	SEDEX("40010", 30, new BigDecimal(130.00), new BigDecimal(20.0), "3"), 
	SEDEX_10("40215", 10, new BigDecimal(130.00), new BigDecimal(20.0), "3"), 
	E_SEDEX("81019", 15, new BigDecimal(130.00), new BigDecimal(20.0), "3");
	
    private final String freightServiceCode;
    private final Integer maximumWeight;
    private final BigDecimal maximumFreight;
    private final BigDecimal defaultFreight;
    private final String defaultDays;

	CorreiosCodes(String freightServiceCode, Integer maximumWeight, BigDecimal maximumFreight, BigDecimal defaultFreight, String defaultDays) {
        this.freightServiceCode = freightServiceCode; 
        this.maximumWeight = maximumWeight;
        this.maximumFreight = maximumFreight;
        this.defaultFreight = defaultFreight;
        this.defaultDays = defaultDays;
    }

	public String getDefaultDays() {
		return defaultDays;
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
	
    public BigDecimal getDefaultFreight() {
		return defaultFreight;
	}
	
}