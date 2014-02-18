package com.ideais.spring.dao.domain.checkout;

import java.math.BigDecimal;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import com.ideais.spring.dao.domain.checkout.stock.Item;

@Entity
@Table(name="CARRINHO_COMPRAS")
public class ShoppingCart {
	
	@Id
	@SequenceGenerator(name = "shoppingCart_id", sequenceName = "shoppingCart_id")
	@GeneratedValue(generator = "shoppingCart_id", strategy = GenerationType.AUTO)
	@Column(name="CD_CARRINHO_COMPRAS")
	private Long id;
	
	@Column(name="NM_FRETE")
	private BigDecimal freight = BigDecimal.ZERO;

	@Column(name="NM_PRECO_TOTAL")
	private BigDecimal totalAmount = BigDecimal.ZERO;
	
	@Column(name="NM_QUANTIDADE_ITEMS")
	private Integer quantityOfItems = 0;
	
	@OneToOne(mappedBy = "shoppingCart")
	@Cascade(CascadeType.ALL)
	private PurchaseOrder purchaseOrder;
	
	@OneToMany(mappedBy="shoppingCart")
	@Cascade(CascadeType.ALL)
	private List<ShoppingCartLine> shoppingCartLines;
	
	@ManyToOne(targetEntity=Customer.class)
	@JoinColumn(name="CD_CLIENTE", referencedColumnName="CD_CLIENTE", nullable=false)
	@Cascade(CascadeType.MERGE)
	private Customer customer;
	
	public ShoppingCart() {
		shoppingCartLines = new ArrayList<ShoppingCartLine>();
	}
	
	public void calculateQuantityOfItems() {
		Integer totalQuantity = 0;
		
		for (int i = 0; i < shoppingCartLines.size(); i++) {
			totalQuantity += shoppingCartLines.get(i).getQuantity();
		}
		
		quantityOfItems = totalQuantity;
	}
	
	public void calculateTotalPrice() {
		BigDecimal totalPrice = BigDecimal.ZERO;
		
		for (int i = 0; i < shoppingCartLines.size(); i++) {
			totalPrice = totalPrice.add(shoppingCartLines.get(i).calculatePrice());
		}
		
		totalAmount = totalPrice.add(freight);
	}
	
	public void add(ShoppingCartLine shoppingCartLine) {
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
	
	public void removeItem(Item item) {
		for (int i = 0; i < shoppingCartLines.size(); i++) {
			if (shoppingCartLines.get(i).getItem().getId() == item.getId()) {
				shoppingCartLines.remove(shoppingCartLines.get(i));
				break;
			}
		}		
		
		calculateTotalPrice();
		calculateQuantityOfItems();
	}
	
	public Item getItem(Long id) {
		for (int i = 0; i < shoppingCartLines.size(); i++) {
			if (shoppingCartLines.get(i).getItem().getId() == id) {
				return shoppingCartLines.get(i).getItem();
			}
		}
		
		return null;
	}
	
	public void addItem(Item item) throws Exception {
		ShoppingCartLine shoppingCartLine = hasItem(item);
		
		if (shoppingCartLine == null && item != null) {	
			shoppingCartLine = new ShoppingCartLine(item);
			shoppingCartLine.setShoppingCart(this);
			add(shoppingCartLine);		
		} 
		
		calculateTotalPrice();
		calculateQuantityOfItems();
	}
	
	private ShoppingCartLine hasItem(Item item) {
		for (int i = 0; i < shoppingCartLines.size(); i++) {
			if (shoppingCartLines.get(i).getItem().getId() == item.getId()) {
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

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public BigDecimal getTotalAmount() {
		calculateTotalPrice();
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getQuantityOfItems() {
		return quantityOfItems;
	}

	public void setQuantityOfItems(Integer quantityOfItems) {
		this.quantityOfItems = quantityOfItems;
	}
	
}