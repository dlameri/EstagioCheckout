package com.ideais.spring.dao.domain.checkout.stock;

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
	private double priceFrom;
	@Column(name="NR_PRECO_PARA")
	private double priceFor;
	@Column(name="NM_NOME_OPCAO") //variacao de tipo
	private String optionName;
	@Column(name="NM_VALOR_OPCAO") //valor de variação
	private String optionValue;
	
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

	public double getPriceFrom() {
		return priceFrom;
	}

	public void setPriceFrom(double priceFrom) {
		this.priceFrom = priceFrom;
	}

	public double getPriceFor() {
		return priceFor;
	}

	public void setPriceFor(double priceFor) {
		this.priceFor = priceFor;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public String getOptionValue() {
		return optionValue;
	}

	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}

	public List<Sku> getSkus() {
		return skus;
	}

	public void setSkus(List<Sku> skus) {
		this.skus = skus;
	}

}