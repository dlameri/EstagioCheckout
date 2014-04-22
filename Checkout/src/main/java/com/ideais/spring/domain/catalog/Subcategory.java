package com.ideais.spring.domain.catalog;

import java.util.List;
import com.ideais.spring.domain.stock.json.SubcategoryJSON;

public class Subcategory {
	
	private Long id;
	private String name;
	private Category category;	
	private Boolean active;
	private List<Product> products;
	
	public Subcategory(SubcategoryJSON subcategoryJSON) {
		if (subcategoryJSON != null) {
			this.id = subcategoryJSON.getId();
			this.name = subcategoryJSON.getName();
			this.active = subcategoryJSON.getActive();
		}
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public String toJson() {
		return "{" 
					+"\"id\":" + this.id + ","
					+"\"name\":" + this.name
				+ "}";
	}
	
}
