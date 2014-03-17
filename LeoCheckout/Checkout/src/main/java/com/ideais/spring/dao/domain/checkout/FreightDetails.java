package com.ideais.spring.dao.domain.checkout;

import java.math.BigDecimal;
import java.util.Date;

public class FreightDetails {
	
	private BigDecimal freightValue;
	private String storeZipCode;
	private String destinationZipCode;
	private String serviceType;
	private Date deliveryDate;
	
	public FreightDetails(String serviceType, String destinationZipCode, String storeZipCode) {
		this.serviceType = serviceType;
		this.destinationZipCode = destinationZipCode;
		this.storeZipCode = storeZipCode;
	}
	
	public BigDecimal getFreightValue() {
		return freightValue;
	}
	
	public void setFreightValue(BigDecimal freightValue) {
		this.freightValue = freightValue;
	}
	
	public String getStoreZipCode() {
		return storeZipCode;
	}
	
	public void setStoreZipCode(String storeZipCode) {
		this.storeZipCode = storeZipCode;
	}
	
	public String getDestinationZipCode() {
		return destinationZipCode;
	}
	
	public void setDestinationZipCode(String destinationZipCode) {
		this.destinationZipCode = destinationZipCode;
	}
	
	public String getServiceType() {
		return serviceType;
	}
	
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
}