package com.ideais.spring.dao.domain.checkout.stock;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import com.ideais.spring.dao.domain.checkout.ShoppingCartLine;

@Entity
@Table(name="SKU")
public class Sku {
	
	@Id
	@SequenceGenerator(name = "sku_id", sequenceName = "sku_id")
	@GeneratedValue(generator = "sku_id", strategy = GenerationType.AUTO)
	@Column(name="CD_SKU", nullable=false)
	private Long id;
	@Column(name="NM_NOME")
	private String name;
	@Column(name="NM_DESCRICAO")
	private String longDescription;
	@Column(name="NM_DESCRICAO_CURTA")
	private String shortDescription;
	@Column(name="NR_LARGURA")
	private Integer width;
	@Column(name="NR_ALTURA")
	private Integer height;
	@Column(name="NR_PROFUNDIDADE")
	private Integer depth;
	@Column(name="NR_PESO")
	private Integer weight;
	@Column(name="NR_GARANTIA")
	private Integer warranty;
	@Column(name="NM_MODELO")
	private String model;
	@Column(name="NR_NUMERO_ESTOQUE")
	private Integer stock;
	
	//lazy eager (lazy)
	
	//Many to One
	@ManyToOne(targetEntity=Brand.class)
	@JoinColumn(name="CD_MARCA", referencedColumnName="CD_MARCA", nullable=false)
	@Cascade(CascadeType.MERGE)
	private Brand brand;
	@ManyToOne(targetEntity=Product.class)
	@JoinColumn(name="CD_PRODUTO", referencedColumnName="CD_PRODUTO", nullable=false)
	@Cascade(CascadeType.MERGE)
	private Product product;
	
	//One to One
	@OneToOne
	@JoinColumn(name="CD_CARRINHO_COMPRAS_LINHA", referencedColumnName="CD_CARRINHO_COMPRAS_LINHA", nullable=false)
	private ShoppingCartLine shoppingCartLine;
	
	//One to Many
	@OneToMany(mappedBy="sku")
	@Cascade(CascadeType.ALL)
	private List<Image> images;
	
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public List<Image> getImages() {
		return images;
	}
	
	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public ShoppingCartLine getShoppingCartLine() {
		return shoppingCartLine;
	}

	public void setShoppingCartLine(ShoppingCartLine shoppingCartLine) {
		this.shoppingCartLine = shoppingCartLine;
	}

	public void setImages(List<Image> images) {
		this.images = images;
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
		Sku other = (Sku) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}