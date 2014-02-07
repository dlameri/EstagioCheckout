package com.ideais.spring.dao.domain.checkout;

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

@Entity
@Table(name="CARRINHO_COMPRAS")
public class ShoppingCart {
	
	@Id
	@SequenceGenerator(name = "shoppingCart_id", sequenceName = "shoppingCart_id")
	@GeneratedValue(generator = "shoppingCart_id", strategy = GenerationType.AUTO)
	@Column(name="CD_CARRINHO_COMPRAS")
	private Long id;
	
	//One to One
	@OneToOne(mappedBy = "shoppingCart")
	@Cascade(CascadeType.ALL)
	private PurchaseOrder purchaseOrder;
	
	//One to Many
	@OneToMany(mappedBy="shoppingCart")
	@Cascade(CascadeType.ALL)
	private List<ShoppingCartLine> shoppingCartLines;
	
	//Many to One
	@ManyToOne(targetEntity=Customer.class)
	@JoinColumn(name="CD_CLIENTE", referencedColumnName="CD_CLIENTE", nullable=false)
	@Cascade(CascadeType.MERGE)
	private Customer customer;
	
	public Double calculateTotalPrice() {
		Double totalPrice = 0.0;
		
		for (int i = 0; i < shoppingCartLines.size(); i++) {
			totalPrice += shoppingCartLines.get(i).calculatePrice();
		}
		
		return totalPrice;
	}
	
	public void add(ShoppingCartLine shoppingCartLine) { //adicionar sessão do zero, criar sessão (?)
		ShoppingCartLine existingShoppingCartLine = contains(shoppingCartLine);
		
		if (existingShoppingCartLine != null) {
			existingShoppingCartLine.increaseByOne();
		} else {
			shoppingCartLines.add(shoppingCartLine);
		}
	}
	
	public void remove(ShoppingCartLine shoppingCartLine) {
		shoppingCartLines.remove(shoppingCartLine);
	}
	
	public void emptyShoppingCart() { 
		shoppingCartLines.removeAll(shoppingCartLines);
	}
	
	public ShoppingCartLine contains(ShoppingCartLine shoppingCartLine) {
		for (int i = 0; i < shoppingCartLines.size(); i++) {
			if (shoppingCartLine.equals(shoppingCartLines.get(i))) {
				return shoppingCartLines.get(i);
			}
		}
		
		return null;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public List<ShoppingCartLine> getShoppingCartLines() {
		return shoppingCartLines;
	}

	public void setShoppingCartLines(List<ShoppingCartLine> shoppingCartLines) {
		this.shoppingCartLines = shoppingCartLines;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}