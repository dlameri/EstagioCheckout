package com.ideais.spring.api.domain.json;

import com.ideais.spring.dao.domain.checkout.ShoppingCartLine;
import com.ideais.spring.dao.domain.checkout.stock.Item;

public class CartItem {
	
	private Long cartItemId;
	private Integer quantity;
	
	public CartItem(ShoppingCartLine shoppingCartLine) {
    	cartItemId = shoppingCartLine.getItem().getId();
		quantity = shoppingCartLine.getQuantity();
	}
	
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