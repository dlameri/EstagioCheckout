package com.ideais.spring.api.service.model.json;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private List<CartItem> cartItems;
	private Integer totalQuantity = 0;
	
	public Cart() {
		cartItems = new ArrayList<CartItem>();
	}
	
	public void addStockItem(CartItem cartItem) {
		if (cartItem != null) {
			cartItems.add(cartItem);
			calculateTotalQuantity();
		}
	}
	
	public void calculateTotalQuantity() {
		Integer quantity = 0;
		
		for (int i = 0; i < cartItems.size(); i++) {
			quantity += cartItems.get(i).getQuantity();
		}
		
		totalQuantity = quantity;
	}
	
	public Integer getTotalQuantity() {
		return totalQuantity;
	}
	
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	
}