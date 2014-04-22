package com.ideais.spring.domain.json;

import com.ideais.spring.domain.Item;
import com.ideais.spring.domain.ShoppingCartLine;

public class CartItem {
	
	private Long cartItemId;
	private Integer quantity;
	
	public CartItem() {}
	
	public CartItem(Long cartItemId, Integer quantity) {
		this.cartItemId = cartItemId;
		this.quantity = quantity;
	}
	
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