package com.ideais.spring.domain.checkout.json;

import com.ideais.spring.domain.checkout.Item;
import com.ideais.spring.domain.checkout.ShoppingCartLine;

public class CartItem {
	
	private Long cartItemId;
	private Integer quantity;
	
	public CartItem() {}
	
	public CartItem(Long cartItemId, Integer quantity) {
		this.cartItemId = cartItemId;
		this.quantity = quantity;
	}
	
	public CartItem(ShoppingCartLine shoppingCartLine) {
    	cartItemId = shoppingCartLine.getItem().getItemId();
		quantity = shoppingCartLine.getQuantity();
	}
	
	public CartItem(Item item, Integer quantity) {
		cartItemId = item.getItemId();
		this.quantity = quantity;
	}
	
	public Long getCartItemId() {
		return cartItemId;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
}