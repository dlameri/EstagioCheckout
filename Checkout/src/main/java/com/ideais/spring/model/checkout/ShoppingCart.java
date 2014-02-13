package com.ideais.spring.model.checkout;

import java.util.ArrayList;
import java.util.List;

import com.ideais.spring.model.stock.Product;

public class ShoppingCart {
	private List<ShoppingCartLine> shoppingCartLines = new ArrayList<ShoppingCartLine>();
	
	public ShoppingCart() {
		
	}
	
	public Double calculateTotalPrice() {
		Double totalPrice = 0.0;
		
		for (int i = 0; i < shoppingCartLines.size(); i++) {
			totalPrice += shoppingCartLines.get(i).calculatePrice();
		}
		
		return totalPrice;
	}
	
	public void add(ShoppingCartLine shoppingCartLine) {
		shoppingCartLines.add(shoppingCartLine);
	}
	
	public void remove(Product product) {
		
	}	
}