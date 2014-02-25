package com.ideais.spring.dao.domain.checkout.stock;

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
@Table(name = "PRODUTO")
public class Product {
	
	@Id
	@SequenceGenerator(name = "product_id", sequenceName = "product_id")
	@GeneratedValue(generator = "product_id", strategy = GenerationType.AUTO)
	@Column(name = "CD_PRODUTO")
	private Long id;

	@Column(name = "NM_NOME", nullable = false)
	private String name;

	@Column(name = "NM_DESCRICAO_LONGA", nullable = false)
	private String longDescription;

	@Column(name = "NM_DESCRICAO_CURTA", nullable = false)
	private String shortDescription;

	@Column(name = "NR_PESO", nullable = false)
	private Integer weight;

	@Column(name = "NR_GARANTIA", nullable = false)
	private Integer warranty;

	@ManyToOne(targetEntity=Brand.class, fetch=FetchType.EAGER)
	@JoinColumn(name="CD_MARCA", referencedColumnName="CD_MARCA", nullable=false)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Brand brand;

	@Column(name = "NM_MODELO", nullable = false)
	private String model;

	@JsonBackReference
	@OneToMany(mappedBy="product", fetch=FetchType.EAGER, orphanRemoval=true)
	@Fetch(value = FetchMode.SUBSELECT)
	@Cascade(CascadeType.ALL)
	private List<Item> items;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "CD_SUBCATEGORIA", referencedColumnName = "CD_SUBCATEGORIA", nullable = false)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Subcategory subcategory;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "CD_CATEGORIA", referencedColumnName = "CD_CATEGORIA", nullable = false)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Category category;
	
	@OneToOne(mappedBy="product")
	private Dimensions dimensions;

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

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
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

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}