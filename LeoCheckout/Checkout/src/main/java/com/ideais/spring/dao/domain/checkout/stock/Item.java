package com.ideais.spring.dao.domain.checkout.stock;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
	
	@Column(name="NR_PRECO_DE", nullable=false)
	private BigDecimal priceFrom;
	
	@Column(name="NR_PRECO_POR", nullable=false)
	private BigDecimal priceFor;
	
	@Column(name="NM_NOME_OPCAO")
	private String optionName;
	
	@Column(name="NM_VALOR_OPCAO")
	private String optionValue;
	
	@Column(name="NR_ESTOQUE", nullable=false)
	private Integer stock;
	
	@ManyToOne(targetEntity=Product.class, fetch=FetchType.EAGER)
	@JoinColumn(name="CD_PRODUTO", referencedColumnName="CD_PRODUTO", nullable=false)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Product product;
	
	@OneToMany(mappedBy="item", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@Cascade(CascadeType.ALL)
	private List<Image> images;

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPriceFrom() {
		return priceFrom.setScale(2, RoundingMode.CEILING);
	}

	public void setPriceFrom(BigDecimal priceFrom) {
		this.priceFrom = priceFrom;
	}

	public BigDecimal getPriceFor() {
		return priceFor.setScale(2, RoundingMode.CEILING);
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}