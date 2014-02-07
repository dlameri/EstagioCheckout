package com.ideais.spring.api.service.model.json;

public class Sku {
	private Long id;
	private Long productId;
	private Double priceFor;
	private Double priceFrom;
	private Integer quantity;
	private Integer Stock;
	private String name;
	private String longDescription;
	private String shortDescription;
	private Image image;
	
	public Long getProductId() {
		return productId;
	}
	
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Integer getStock() {
		return Stock;
	}
	
	public void setStock(Integer stock) {
		Stock = stock;
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
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}

	public Double getPriceFor() {
		return priceFor;
	}

	public void setPriceFor(Double priceFor) {
		this.priceFor = priceFor;
	}

	public Double getPriceFrom() {
		return priceFrom;
	}

	public void setPriceFrom(Double priceFrom) {
		this.priceFrom = priceFrom;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

}