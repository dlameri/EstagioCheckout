package com.ideais.spring.domain.checkout.json;

import java.util.ArrayList;
import java.util.List;

import com.ideais.spring.domain.checkout.ShoppingCart;
import com.ideais.spring.domain.checkout.ShoppingCartLine;

public class Cart {
	
	private List<CartItem> cartItems;
	private Integer totalQuantity = 0;
	
	public Cart(List<CartItem> cartItems, Integer totalQuantity) {
		this.cartItems = cartItems;
		this.totalQuantity = totalQuantity;
	}
	
	public Cart(ShoppingCart shoppingCart) {
		cartItems = new ArrayList<CartItem>();
		
		for (ShoppingCartLine line : shoppingCart.getShoppingCartLines()) {
			cartItems.add( new CartItem(line.getItem(), line.getQuantity()) );
		}
		
		calculateTotalQuantity();
	}
	
	public Cart() {
		cartItems = new ArrayList<CartItem>();
	}
	
	public void addCartItem(CartItem cartItem) {
		if (cartItem != null) {
			cartItems.add(cartItem);
			calculateTotalQuantity();
		}
	}
	
	private void calculateTotalQuantity() {
		Integer quantity = 0;
		
		for (CartItem item : cartItems) {
			quantity += item.getQuantity();
		}
		
		totalQuantity = quantity;
	}
	
	public Integer getTotalQuantity() {
		calculateTotalQuantity();
		
		return totalQuantity;
	}
	
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	
}