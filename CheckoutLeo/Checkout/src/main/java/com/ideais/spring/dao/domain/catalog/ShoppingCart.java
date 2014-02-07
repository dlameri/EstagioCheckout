package com.ideais.spring.dao.domain.catalog;

import java.util.List;

public class ShoppingCart {
	private Long id;
	private List<Sku> skus;
	private Double totalPrice;
	
	public List<Sku> getSkus() {
		return skus;
	}
	
	public void setSkus(List<Sku> skus) {
		this.skus = skus;
		calculeTotalPrice();
	}
	
	public Double getTotalPrice() {
		return totalPrice;
	}
	
	private void calculeTotalPrice() {
		Double total = 0.0;
		
		for (int i = 0; i < skus.size(); i++) {
			total += skus.get(i).getPriceFor();
		}
		
		totalPrice = total;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}