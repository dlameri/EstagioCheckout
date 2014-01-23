package com.ideais.spring.model.stock;

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
@Table(name="ITEM")
public class Item {
	@Id
	@SequenceGenerator(name = "item_id", sequenceName = "item_id")
	@GeneratedValue(generator = "item_id", strategy = GenerationType.AUTO)
	@Column(name="CD_ITEM", nullable=false)
	private Long id;
	@Column(name="NR_PRECO_DE")
	private double priceFrom;
	@Column(name="NR_PRECO_PARA")
	private double priceFor;
	@Column(name="NM_NOME_OPCAO") //variacao de tipo
	private String optionName;
	@Column(name="NM_VALOR_OPCAO") //valor de variação
	private String optionValue;
	@Column(name="NR_NUMERO_ESTOQUE")
	private Integer stock;
	@OneToMany(mappedBy="item")
	@Cascade(CascadeType.ALL)
	private List<Product> products;
	@OneToMany(mappedBy="item")
	@Cascade(CascadeType.ALL)
	private List<Image> images;

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

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImage(List<Image> images) {
		this.images = images;
	}

}