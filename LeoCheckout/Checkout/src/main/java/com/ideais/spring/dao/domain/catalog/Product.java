package com.ideais.spring.dao.domain.catalog;

import java.math.BigDecimal;

public class Product {
	private Long id;
	private String name;
	private BigDecimal priceFrom;
	private BigDecimal priceFor;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPriceFrom() {
		return priceFrom;
	}

	public void setPriceFrom(BigDecimal priceFrom) {
		this.priceFrom = priceFrom;
	}

	public BigDecimal getPriceFor() {
		return priceFor;
	}

	public void setPriceFor(BigDecimal priceFor) {
		this.priceFor = priceFor;
	}

}