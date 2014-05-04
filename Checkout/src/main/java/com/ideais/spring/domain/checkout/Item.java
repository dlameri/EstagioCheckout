package com.ideais.spring.domain.checkout;

import java.math.BigDecimal;
import com.ideais.spring.domain.stock.json.ItemJSON;
import com.ideais.spring.util.ValueFormatter;

public class Item {
	
	private Long itemId;
	private Long sku;
	private BigDecimal priceFrom;
	private BigDecimal priceFor;
	private String optionName;
	private String optionValue;
	private Integer stock;
	private Integer weight; //gramas
	private String productName;
	private Long productId;
	private String imageUrl;
	private Dimensions dimensions;
	private Boolean active;
	
	public Item() {}
	
	public Item(ItemJSON itemJSON) {
		if (itemJSON != null) {
			this.itemId = itemJSON.getId();
			this.optionName = itemJSON.getOptionName();
			this.optionValue = itemJSON.getOptionValue();
			this.priceFor = itemJSON.getPriceFor();
			this.priceFrom = itemJSON.getPriceFrom();
			this.sku = itemJSON.getSku();
			this.stock = itemJSON.getStock();
			this.active = itemJSON.getActive();
		}
	}
	
	public Long getProductId() {
	    return productId;
	}

	public BigDecimal getPriceFrom() {
		return priceFrom;
	}

	public void setPriceFrom(BigDecimal priceFrom) {
		this.priceFrom = priceFrom;
	}

	public BigDecimal getPriceFor() {
		return priceFor;
	}

	public void setPriceFor(BigDecimal priceFor) {
		this.priceFor = priceFor;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public Long getSku() {
		return sku;
	}

	public void setSku(Long sku) {
		this.sku = sku;
	}

	public String getOptionValue() {
		return optionValue;
	}

	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	public String getFormattedPriceFrom() {
	    return ValueFormatter.format(priceFrom);
	}

	public String getFormattedPriceFor() {
		return ValueFormatter.format(priceFor);
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Dimensions getDimensions() {
		return dimensions;
	}

	public void setDimensions(Dimensions dimensions) {
		this.dimensions = dimensions;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
}