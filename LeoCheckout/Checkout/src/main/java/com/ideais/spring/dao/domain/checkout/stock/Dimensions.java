package com.ideais.spring.dao.domain.checkout.stock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
@Table(name = "DIMENSOES")
public class Dimensions {
	
	@Id
	@SequenceGenerator(name = "item_id", sequenceName = "item_id")
	@GeneratedValue(generator = "item_id", strategy = GenerationType.AUTO)
	@Column(name = "CD_DIMENSOES")
	private Long id;
	
	@Column(name = "NM_ALTURA", nullable = false)
	private Integer height;
	
	@Column(name = "NM_LARGURA", nullable = false)
	private Integer width;
	
	@Column(name = "NM_COMPRIMENTO", nullable = false)
	private Integer depth;
	
	@JsonBackReference 
	@OneToOne
	@JoinColumn(name="CD_PRODUTO", referencedColumnName="CD_PRODUTO", nullable=false)
	private Product product;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

}