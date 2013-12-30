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

	public void setQuantity(Integer quantity) throws Exception {
		if (quantity >= 0) {
			this.quantity = quantity;
		} else {
			throw new Exception("Number not supported!");
		}
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public void increaseByOne() {
		quantity++;
	}
	
	public void decreaseByOne() {
		if (quantity > 0) {
			quantity--;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingCartLine other = (ShoppingCartLine) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		return true;
	}
	
}