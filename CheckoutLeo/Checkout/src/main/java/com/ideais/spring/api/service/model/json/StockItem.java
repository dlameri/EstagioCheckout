package com.ideais.spring.api.service.model.json;

import com.ideais.spring.dao.domain.catalog.Sku;

public class StockItem {
	private Long SkuId;
	private Integer quantity;
	
	public StockItem(Sku sku) {
		SkuId = sku.getId();
		quantity = sku.getQuantity();
	}
	
	public Long getSkuId() {
		return SkuId;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
}