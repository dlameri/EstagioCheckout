package com.ideais.spring.model.stock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import com.ideais.spring.model.checkout.ShoppingCartLine;

@Entity
@Table(name="PRODUTO")
public class Product {
	@Id
	@SequenceGenerator(name = "product_id", sequenceName = "product_id")
	@GeneratedValue(generator = "product_id", strategy = GenerationType.AUTO)
	@Column(name="CD_PRODUTO", nullable=false)
	private Long id;
	@Column(name="NM_NOME")
	private String name;
	@Column(name="NM_DESCRICAO")
	private String longDescription;
	@Column(name="NM_DESCRICAO_CURTA")
	private String shortDescription;
	@Column(name="NM_DIMENSAO")
	private String dimensions;
	@Column(name="NR_PESO")
	private Integer weight;
	@Column(name="NR_GARANTIA")
	private Integer warranty;
	@Column(name="NM_MARCA")
	private String brand;
	@Column(name="NM_MODELO")
	private String model;
	@ManyToOne(targetEntity=Item.class)
	@JoinColumn(name="CD_ITEM", referencedColumnName="CD_ITEM", nullable=false)
	@Cascade(CascadeType.MERGE)
	private Item item;
	@OneToOne
	@JoinColumn(name="CD_CARRINHO_COMPRAS", referencedColumnName="CD_CARRINHO_COMPRAS", nullable=false)
	private ShoppingCartLine shoppingCartLine;
	
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

	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}