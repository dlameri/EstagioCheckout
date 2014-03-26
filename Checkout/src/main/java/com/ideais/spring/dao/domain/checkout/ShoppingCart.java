package com.ideais.spring.dao.domain.checkout;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import com.ideais.spring.util.ValueFormatter;

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
	
	@Column(name="NM_PRECO_SUB_TOTAL")
	private BigDecimal subTotalAmount = BigDecimal.ZERO;
	
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
	
	public ShoppingCart(List<Item> items) {
		if (items != null) {
			shoppingCartLines = new ArrayList<ShoppingCartLine>();
			
			for (int i = 0; i < items.size(); i++) {
				shoppingCartLines.add(new ShoppingCartLine(items.get(i)));
			}
			
			recalculateShoppingCartProperties();
		}
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
			totalPrice = totalPrice.add(shoppingCartLines.get(i).getPrice());
		}
		
		subTotalAmount = totalPrice;
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
		freight = BigDecimal.ZERO;
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
		if (item != null) {
			for (int i = 0; i < shoppingCartLines.size(); i++) {
				if (shoppingCartLines.get(i).getItem().getId() == item.getId()) {
					shoppingCartLines.remove(shoppingCartLines.get(i));
					break;
				}
			}		
		}
		
		recalculateShoppingCartProperties();
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
			processAddItem(shoppingCartLine, item);	
		} 
		
		recalculateShoppingCartProperties();
	}
	
	private void processAddItem(ShoppingCartLine shoppingCartLine, Item item) throws Exception {
		if (item.getStock() > 0) {
			shoppingCartLine = new ShoppingCartLine(item);
			shoppingCartLine.setShoppingCart(this);
			add(shoppingCartLine);	
		} else {
			throw new Exception("Produto se encontra em falta no estoque!");
		}
		
	}

	private ShoppingCartLine hasItem(Item item) {
		if (item != null) {
			for (int i = 0; i < shoppingCartLines.size(); i++) {
				if (shoppingCartLines.get(i).getItem().getId() == item.getId()) {
					return shoppingCartLines.get(i);
				}
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
		
		recalculateShoppingCartProperties();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getFreight() {
		return freight.setScale(2, RoundingMode.CEILING);
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
		recalculateShoppingCartProperties();		
	}

	public BigDecimal getTotalAmount() {
		calculateTotalPrice();
		return totalAmount.setScale(2, RoundingMode.CEILING);
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

	public void editQuantity(Long itemId, Integer quantity) throws Exception {
		Item item = getItem(itemId);
		ShoppingCartLine shoppingCartLine = hasItem(item);
		
		if (shoppingCartLine != null && item != null) {	
			shoppingCartLine.setQuantity(quantity);		
		} 
		
		recalculateShoppingCartProperties();
	}
	
	public void recalculateShoppingCartProperties() {
		calculateTotalPrice();
		calculateQuantityOfItems();	
	}

	public BigDecimal getSubTotalAmount() {
		return subTotalAmount.setScale(2, RoundingMode.CEILING);
	}

	public String getFormattedFreight() {
		return ValueFormatter.format(freight);
	}

	public String getFormattedTotalAmount() {
		return ValueFormatter.format(totalAmount);
	}

	public String getFormattedSubTotalAmount() {
		return ValueFormatter.format(subTotalAmount);
	}
		
}