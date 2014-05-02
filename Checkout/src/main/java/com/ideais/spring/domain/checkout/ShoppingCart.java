package com.ideais.spring.domain.checkout;

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
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.ideais.spring.exceptions.MissingQuantityStockException;
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
	
	@OneToMany
	@Cascade(CascadeType.ALL)
	private List<ShoppingCartLine> shoppingCartLines;
	
	@ManyToOne(targetEntity=Customer.class)
	@JoinColumn(name="CD_CLIENTE", referencedColumnName="CD_CLIENTE", nullable=false)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Customer customer;
	
	@Transient
	private static final String NULL_FREIGHT = "R$ 0,00";
	
	@Transient
	private static final String EMPTY_STRING = "";
	
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
			if (shoppingCartLine.getItem().getItemId().equals(shoppingCartLines.get(i).getItem().getItemId())) {
				return shoppingCartLines.get(i);
			}
		}
		
		return null;
	}
	
	public void removeItem(Item item) {
		if (item != null) {
			for (int i = 0; i < shoppingCartLines.size(); i++) {
				if (shoppingCartLines.get(i).getItem().getItemId().equals(item.getItemId())) {
					shoppingCartLines.remove(shoppingCartLines.get(i));
					break;
				}
			}		
		}
		
		recalculateShoppingCartProperties();
	}
	
	public void removeItemFromId(Long id) {
		if (id != null) {
			for (int i = 0; i < shoppingCartLines.size(); i++) {
				if (shoppingCartLines.get(i).getItem().getItemId().equals(id)) {
					shoppingCartLines.remove(shoppingCartLines.get(i));
					break;
				}
			}		
		}
		
		recalculateShoppingCartProperties();
	}
	
	public Item getItem(Long id) {
		for (int i = 0; i < shoppingCartLines.size(); i++) {
			if (shoppingCartLines.get(i).getItem().getItemId().equals(id)) {
				return shoppingCartLines.get(i).getItem();
			}
		}
		
		return null;
	}
	
	public void addItem(Item item) throws MissingQuantityStockException {
		ShoppingCartLine shoppingCartLine = hasItem(item);
		
		if (shoppingCartLine == null && item != null) {
			processAddItem(shoppingCartLine, item);	
		} 
		
		recalculateShoppingCartProperties();
	}
	
	private void processAddItem(ShoppingCartLine shoppingCartLine, Item item) throws MissingQuantityStockException {
		if (item.getStock() > 0) {
			shoppingCartLine = new ShoppingCartLine(item);
			add(shoppingCartLine);	
		} else {
			throw new MissingQuantityStockException("Produto se encontra em falta no estoque!");
		}
		
	}

	private ShoppingCartLine hasItem(Item item) {
		if (item != null) {
			for (int i = 0; i < shoppingCartLines.size(); i++) {
				if (shoppingCartLines.get(i).getItem().getItemId().equals(item.getItemId())) {
					return shoppingCartLines.get(i);
				}
			}
		}
		
		return null;
	}
	
	public boolean hasItemWithId(Long itemId) {
		if (shoppingCartLines != null) {
			for (int i = 0; i < shoppingCartLines.size(); i++) {
				if (shoppingCartLines.get(i).getItem() != null) {
					if (shoppingCartLines.get(i).getItem().getItemId().equals(itemId)) {
						return true;
					}
				}
			}
		}
		
		return false;
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

	public void editQuantity(Long itemId, Integer quantity) throws NumberFormatException, MissingQuantityStockException  {
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
		if (!NULL_FREIGHT.equals(ValueFormatter.format(freight))) {
			return ValueFormatter.format(freight);
		}
		
		return EMPTY_STRING;
	}

	public String getFormattedTotalAmount() {
		return ValueFormatter.format(totalAmount);
	}

	public String getFormattedSubTotalAmount() {
		return ValueFormatter.format(subTotalAmount);
	}
	
	public String getFormattedQuantityOfItems() {
		if (quantityOfItems > 1) {
			return quantityOfItems + " items";
		}
		
		return quantityOfItems + " item";
	}
		
}