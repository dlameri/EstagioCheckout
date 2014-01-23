package com.ideais.spring.model.checkout;

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
import com.ideais.spring.model.stock.Product;

@Entity
@Table(name="CARRINHO_COMPRAS_LINHA")
public class ShoppingCartLine {
	@Id
	@SequenceGenerator(name = "shoppingCartLine_id", sequenceName = "shoppingCartLine_id")
	@GeneratedValue(generator = "shoppingCartLine_id", strategy = GenerationType.AUTO)
	@Column(name="CD_CARRINHO_COMPRAS_LINHA")
	private Long id;
	@OneToOne(mappedBy="shoppingCartLine")
	private Product product;
	@Column(name="NM_QUANTIDADE")
	private Integer quantity;
	@Column(name="NM_PRECO")
	private Double price;
	@ManyToOne(targetEntity=ShoppingCart.class)
	@JoinColumn(name="CD_CARRINHO_COMPRAS", referencedColumnName="CD_CARRINHO_COMPRAS", nullable=false)
	@Cascade(CascadeType.MERGE)
	private ShoppingCart shoppingCart;
	
	public ShoppingCartLine() {
		
	}
	
	public Double calculatePrice() {
		return product.getItem().getPriceFor() * quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) throws Exception {
		if (quantity >= 0) {
			this.quantity = quantity;
		} else {
			throw new Exception("Number not supported!");
		}
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public void increaseByOne() {
		quantity++;
	}
	
	public void decreaseByOne() {
		if (quantity > 0) {
			quantity--;
		}
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((item == null) ? 0 : item.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		ShoppingCartLine other = (ShoppingCartLine) obj;
//		if (item == null) {
//			if (other.item != null)
//				return false;
//		} else if (!item.equals(other.item))
//			return false;
//		return true;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	
}