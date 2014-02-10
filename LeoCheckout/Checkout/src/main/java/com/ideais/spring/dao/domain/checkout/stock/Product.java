package com.ideais.spring.dao.domain.checkout.stock;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="PRODUTO")
public class Product {
	@Id
	@SequenceGenerator(name = "product_id", sequenceName = "product_id")
	@GeneratedValue(generator = "product_id", strategy = GenerationType.AUTO)
	@Column(name="CD_PRODUTO", nullable=false)
	private Long id;
	@Column(name="NR_NOME")
	private String name;
	@Column(name="NR_PRECO_DE")
	private BigDecimal priceFrom;
	@Column(name="NR_PRECO_PARA")
	private BigDecimal priceFor;
	
	//One to Many
	@OneToMany(mappedBy="product")
	@Cascade(CascadeType.ALL)
	private List<Sku> skus;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
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

	public List<Sku> getSkus() {
		return skus;
	}

	public void setSkus(List<Sku> skus) {
		this.skus = skus;
	}

}