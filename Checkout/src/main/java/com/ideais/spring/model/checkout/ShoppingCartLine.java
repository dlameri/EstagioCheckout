package com.ideais.spring.model.checkout;

import com.ideais.spring.model.stock.Item;

public class ShoppingCartLine {
	private Item item;
	private Integer quantity;
	private Double price;
	
	public ShoppingCartLine() {
		
	}
	
	public Double calculatePrice() {
		return item.getProduct().getPriceFor() * quantity;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
}