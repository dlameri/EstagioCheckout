package com.ideais.spring.dao.domain.checkout;

import java.math.BigDecimal;
import java.util.Date;

public class FreightDetails {
	
	private BigDecimal freightValue = BigDecimal.ZERO;
	private String storeZipCode;
	private String destinationZipCode;
	private String serviceType;
	private String deliveryDays;
	
	public FreightDetails(String serviceType, String destinationZipCode, String storeZipCode) {
		this.serviceType = serviceType;
		this.destinationZipCode = destinationZipCode;
		this.storeZipCode = storeZipCode;
	}
	
	public FreightDetails() {}
	
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
	
	public String getDeliveryDays() {
		if (deliveryDays == null) {
			return "Digite o CEP acima para calcular o prazo de entrega.";
		}
		
		return formatDeliveryDays(deliveryDays);
	}
	
	private String formatDeliveryDays(String deliveryDays) {
		if (Integer.parseInt(deliveryDays) > 1) {
			return deliveryDays + " dias úteis.";
		}
		
		return deliveryDays + " dia útil.";
	}

	public void setDeliveryDays(String deliveryDays) {
		this.deliveryDays = deliveryDays;
	}

	public boolean wasCalculated() {
		return deliveryDays != null && storeZipCode != null && serviceType != null && destinationZipCode != null;
	}
	
}