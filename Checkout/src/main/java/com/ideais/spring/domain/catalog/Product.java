package com.ideais.spring.domain.catalog;

import java.util.List;
import com.ideais.spring.domain.checkout.Dimensions;
import com.ideais.spring.domain.checkout.Item;
import com.ideais.spring.domain.stock.json.ProductJSON;

public class Product {

	private Long id;
	private String name;
	private String longDescription;
	private String shortDescription;
	private Integer weight;
	private Integer warranty;
	private String brand;
	private String model;
	private Boolean active;
	private Subcategory subcategory;
	private Category category;
	private List<Item> items;
	private Dimensions dimensions;
	private Integer rank;

	public Product(ProductJSON productJSON) {
		if (productJSON != null) {
			this.id = productJSON.getId();
			this.name = productJSON.getName();
			this.longDescription = productJSON.getLongDescription();
			this.shortDescription = productJSON.getShortDescription();
			this.weight = productJSON.getWeight();
			this.warranty = productJSON.getWarranty();
			this.brand = productJSON.getBrand();
			this.model = productJSON.getModel();
			this.active = productJSON.getActive();
			this.rank = productJSON.getRank();
		}
	}
	
	public List<Item> getItems() {
	    return items;
	}

	public void setItems(List<Item> items) {
	    this.items = items;
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

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public Dimensions getDimensions() {
		return dimensions;
	}

	public void setDimensions(Dimensions dimensions) {
		this.dimensions = dimensions;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getWarranty() {
		return warranty;
	}

	public void setWarranty(Integer warranty) {
		this.warranty = warranty;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public Integer getRank() {
		return rank;
	}
	
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
}