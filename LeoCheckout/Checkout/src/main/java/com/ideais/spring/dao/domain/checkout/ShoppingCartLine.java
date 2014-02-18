package com.ideais.spring.dao.domain.checkout;

import java.math.BigDecimal;

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

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.ideais.spring.dao.domain.checkout.stock.Item;

@Entity
@Table(name="CARRINHO_COMPRAS_LINHA")
public class ShoppingCartLine {
	
	@Id
	@SequenceGenerator(name = "shoppingCartLine_id", sequenceName = "shoppingCartLine_id")
	@GeneratedValue(generator = "shoppingCartLine_id", strategy = GenerationType.AUTO)
	@Column(name="CD_CARRINHO_COMPRAS_LINHA")
	private Long id;
	
	@Column(name="NM_QUANTIDADE")
	private Integer quantity;
	
	@Column(name="NM_PRECO")
	private BigDecimal price;
	
	@OneToOne
	private Item item;
	
	@JsonBackReference
	@ManyToOne(targetEntity=ShoppingCart.class)
	@JoinColumn(name="CD_CARRINHO_COMPRAS", referencedColumnName="CD_CARRINHO_COMPRAS", nullable=false)
	@Cascade(CascadeType.ALL)
	private ShoppingCart shoppingCart;
	
	ShoppingCartLine() {}
	
	ShoppingCartLine(Item item) {
		this.item = item;
		quantity = 1;
		price = item.getPriceFor();
	}
	
	public BigDecimal calculatePrice() {
		return item.getPriceFor().multiply(new BigDecimal(quantity));
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice() {
		this.price = calculatePrice();
	}
	
	public void increaseByOne() {
		quantity++;
	}
	
	public void decreaseByOne() {
		if (quantity > 0) {
			quantity--;
		}
	}

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