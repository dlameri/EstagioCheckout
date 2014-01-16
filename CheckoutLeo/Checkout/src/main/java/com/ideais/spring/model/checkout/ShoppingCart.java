package com.ideais.spring.model.checkout;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	@ManyToOne(targetEntity=PurchaseHistory.class)
	@JoinColumn(name="CD_HISTORICO_COMPRAS", referencedColumnName="CD_HISTORICO_COMPRAS", nullable=false)
	@Cascade(CascadeType.MERGE)
	private PurchaseHistory purchaseHistory;
	@OneToMany(mappedBy="shoppingCart")
	@Cascade(CascadeType.ALL)
	private List<ShoppingCartLine> shoppingCartLines = new ArrayList<ShoppingCartLine>();
	
	public ShoppingCart() {
		
	}
	
	public Double calculateTotalPrice() {
		Double totalPrice = 0.0;
		
		for (int i = 0; i < shoppingCartLines.size(); i++) {
			totalPrice += shoppingCartLines.get(i).calculatePrice();
		}
		
		return totalPrice;
	}
	
	public void add(ShoppingCartLine shoppingCartLine) { //aficionar sessão do zero, criar sessão (?)
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
	
	public void emptyShoppingCart() { //expirar sessão (?)
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

	public PurchaseHistory getPurchaseHistory() {
		return purchaseHistory;
	}

	public void setPurchaseHistory(PurchaseHistory purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
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