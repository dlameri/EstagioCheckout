package com.ideais.spring.domain.checkout.json;

import java.util.ArrayList;
import java.util.List;

import com.ideais.spring.domain.checkout.ShoppingCart;

public class Cart {
	
	private List<CartItem> cartItems;
	private Integer totalQuantity = 0;
	
	public Cart(List<CartItem> cartItems, Integer totalQuantity) {
		this.cartItems = cartItems;
		this.totalQuantity = totalQuantity;
	}
	
	public Cart(ShoppingCart shoppingCart) {
		cartItems = new ArrayList<CartItem>();
		
		for (int i = 0; i < shoppingCart.getShoppingCartLines().size(); i++) {
			cartItems.add(new CartItem(shoppingCart.getShoppingCartLines().get(i).getItem(), shoppingCart.getShoppingCartLines().get(i).getQuantity()));
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
		
		for (int i = 0; i < cartItems.size(); i++) {
			quantity += cartItems.get(i).getQuantity();
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