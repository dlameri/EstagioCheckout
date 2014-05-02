package com.ideais.spring.domain.checkout;

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

import com.ideais.spring.exceptions.MissingQuantityStockException;
import com.ideais.spring.util.ValueFormatter;

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
	@Cascade(CascadeType.SAVE_UPDATE)
	private Item item;
		
	public ShoppingCartLine() {}
	
	public ShoppingCartLine(Item item) {
		this.item = item;
		quantity = 1;
		price = item.getPriceFor();
	}
	
	public void calculatePrice() {
		price = item.getPriceFor().multiply(new BigDecimal(quantity));
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

	public void setQuantity(Integer quantity) throws NumberFormatException, MissingQuantityStockException {
		if (quantity.compareTo(new Integer(1)) >= 0) {
			processEditQuantity(quantity);
		} else {
			throw new NumberFormatException("Number not supported!");
		}
	}

	private void processEditQuantity(Integer quantity) throws MissingQuantityStockException {
		if (item.getStock().compareTo(quantity) >= 0) {
			this.quantity = quantity;
			calculatePrice();
		} else {
			throw new MissingQuantityStockException("Quantidade indisponível em estoque.");
		}
	}

	public BigDecimal getPrice() {
		return price;
	}
	
	public String getFormattedPrice() {
		return ValueFormatter.format(price);
	}

	public void setPrice() {
		calculatePrice();
	}
	
	public void increaseByOne() {
		quantity++;
		calculatePrice();
	}
	
	public void decreaseByOne() {
		if (quantity > 0) {
			quantity--;
			calculatePrice();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}