package com.ideais.spring.api.service.model.json;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ShoppingCart {
	private Long id;
	private List<Sku> skus;
	private BigDecimal totalPrice;
	private BigDecimal freight = BigDecimal.ZERO;
	private Date scheduledDelivery;
	private Address address;
	
	public List<Sku> getSkus() {
		return skus;
	}
	
	public void setSkus(List<Sku> skus) {
		this.skus = skus;
		calculeTotalPrice();
	}
	
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	
	private void calculeTotalPrice() {
		BigDecimal total = BigDecimal.ZERO;
		
		for (int i = 0; i < skus.size(); i++) {			
			total = total.add(skus.get(i).getProduct().getPriceFor());
		}
		
		totalPrice = total.add(freight);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public BigDecimal getFreight() {
		return freight;
	}
	
	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}
	
	public Date getScheduledDelivery() {
		return scheduledDelivery;
	}

	public void setScheduledDelivery(Date scheduledDelivery) {
		this.scheduledDelivery = scheduledDelivery;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
}