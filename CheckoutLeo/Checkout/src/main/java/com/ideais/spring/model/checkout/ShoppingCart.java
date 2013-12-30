package com.ideais.spring.model.checkout;

import java.util.ArrayList;
import java.util.List;

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
	
	public void add(ShoppingCartLine shoppingCartLine) { //aficionar sessão do zero, criar sessão (?)
		ShoppingCartLine existingShoppingCartLine = contains(shoppingCartLine);
		
		if (existingShoppingCartLine != null) {
			existingShoppingCartLine.increaseByOne();
		} else {
			shoppingCartLines.add(shoppingCartLine);
		}
	}
	
	public void remove(ShoppingCartLine shoppingCartLine) {
		shoppingCartLines.remove(shoppingCartLine);
	}
	
	public void emptyShoppingCart() { //expirar sessão (?)
		shoppingCartLines.removeAll(shoppingCartLines);
	}
	
	public ShoppingCartLine contains(ShoppingCartLine shoppingCartLine) {
		for (int i = 0; i < shoppingCartLines.size(); i++) {
			if (shoppingCartLine.equals(shoppingCartLines.get(i))) {
				return shoppingCartLines.get(i);
			}
		}
		
		return null;
	}
	
}