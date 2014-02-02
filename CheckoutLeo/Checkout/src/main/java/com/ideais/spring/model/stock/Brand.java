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
	
	//One to Many
	@OneToMany(mappedBy="brand")
	@Cascade(CascadeType.ALL)
	List<Sku> skus;
	
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
	
	public List<Sku> getSkus() {
		return skus;
	}
	
	public void setSkus(List<Sku> skus) {
		this.skus = skus;
	}
	
}