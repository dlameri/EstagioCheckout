package com.ideais.spring.model.stock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="IMAGE")
public class Image {
	@Id
	@SequenceGenerator(name = "image_id", sequenceName = "image_id")
	@GeneratedValue(generator = "image_id", strategy = GenerationType.AUTO)
	@Column(name="CD_IMAGE", nullable=false)
	private Long id;
	@Column(name="NM_CAMINHO")
	private String path;
	@Column(name="BO_PRINCIPAL")
	private Boolean main;
	@ManyToOne(targetEntity=Item.class)
	@JoinColumn(name="CD_ITEM", referencedColumnName="CD_ITEM", nullable=false)
	@Cascade(CascadeType.MERGE)
	private Item item;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Boolean getMain() {
		return main;
	}

	public void setMain(Boolean main) {
		this.main = main;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}