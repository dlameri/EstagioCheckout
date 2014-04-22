package com.ideais.spring.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.ideais.spring.domain.stock.json.ItemJSON;
import com.ideais.spring.util.ValueFormatter;

@Entity
@Table(name="ITEM")
public class Item {
	
	@Id
	@SequenceGenerator(name="item_id", sequenceName="item_id")
	@GeneratedValue(generator="item_id", strategy=GenerationType.AUTO)
	@Column(name="CD_ITEM")
	private Long id;
	
	@Column(name="NR_SKU")
	private Long sku;
	
	@Column(name = "NR_PRECO_DE", precision = 7, scale = 2, nullable = false)
	private BigDecimal priceFrom;

	@Column(name = "NR_PRECO_POR", precision = 7, scale = 2, nullable = false)
	private BigDecimal priceFor;
	
	@Column(name="NM_NOME_OPCAO")
	private String optionName;
	
	@Column(name="NM_VALOR_OPCAO")
	private String optionValue;
	
	@Column(name="NR_ESTOQUE", nullable=false)
	private Integer stock;
	
	@Column(name="NR_PESO") 
	private Integer weight;
	
	@Column(name="BO_ATIVO", nullable=false)
	private Boolean active;
	
	@Column(name="NM_NOME_PRODUTO")
	private String productName;
	
	@Column(name="CD_PRODUTO")
	private Long productId;

	@Column(name="NM_URL_IMAGEM")
	private String imageUrl;
	
	@OneToOne
	@JoinColumn(name = "CD_DIMENSOES", referencedColumnName = "CD_DIMENSOES", nullable = false)
	@Cascade(CascadeType.ALL)
	private Dimensions dimensions;
	
	public Item(ItemJSON itemJSON) {
		if (itemJSON != null) {
			this.active = itemJSON.getActive();
			this.id = itemJSON.getId();
			this.optionName = itemJSON.getOptionName();
			this.optionValue = itemJSON.getOptionValue();
			this.priceFor = itemJSON.getPriceFor();
			this.priceFrom = itemJSON.getPriceFrom();
			this.sku = itemJSON.getSku();
			this.stock = itemJSON.getStock();
		}
	}
	
	public Long getProductId() {
	    return productId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
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

	public Dimensions getDimensions() {
		return dimensions;
	}

	public void setDimensions(Dimensions dimensions) {
		this.dimensions = dimensions;
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
	
}