package com.ideais.spring.api.service.model.json;

import com.ideais.spring.dao.domain.checkout.stock.Item;

public class CartItem {
	
	private Long cartItemId;
	private Integer quantity;
	
	public CartItem(Item item, Integer quantity) {
		cartItemId = item.getId();
		this.quantity = quantity;
	}
	
	public Long getCartItemId() {
		return cartItemId;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
}