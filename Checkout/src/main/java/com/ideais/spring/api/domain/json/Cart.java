package com.ideais.spring.api.domain.json;

import java.util.ArrayList;
import java.util.List;
import com.ideais.spring.dao.domain.checkout.ShoppingCart;

public class Cart {
	
	private List<CartItem> cartItems;
	private Integer totalQuantity = 0;
	
	public Cart(ShoppingCart shoppingCart) {
		cartItems = new ArrayList<CartItem>();
		
		for (int i = 0; i < shoppingCart.getShoppingCartLines().size(); i++) {
			cartItems.add(new CartItem(shoppingCart.getShoppingCartLines().get(i).getItem(), shoppingCart.getShoppingCartLines().get(i).getQuantity()));
		}
	}
	
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