package com.ideais.spring.dao.domain.checkout.stock;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="MARCA")
public class Brand {
	
	@Id
	@SequenceGenerator(name = "brand_id", sequenceName = "brand_id")
	@GeneratedValue(generator = "brand_id", strategy = GenerationType.AUTO)
	@Column(name="CD_MARCA", nullable=false)
	private Long id;
	
	@Column(name="NM_NOME")
	private String name;
	
	@Column(name="NM_DESCRICAO")
	private String description;
	
	@JsonBackReference
	@OneToMany(mappedBy="brand", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@Cascade(CascadeType.SAVE_UPDATE)
	List<Product> products;
	
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
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}