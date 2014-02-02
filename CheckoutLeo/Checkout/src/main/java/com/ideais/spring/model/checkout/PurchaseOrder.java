package com.ideais.spring.model.checkout;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="ORDEM_DE_COMPRA")
public class PurchaseOrder {
		
	@Id
	@SequenceGenerator(name = "purchaseOrder_id", sequenceName = "purchaseOrder_id")
	@GeneratedValue(generator = "purchaseOrder_id", strategy = GenerationType.AUTO)
	@Column(name="CD_ORDEM_DE_COMPRA")
	private Long idPurchaseHistory;
	@Column(name="NM_STATUS_COMPRA")
	private StatusOfOrder statusOfOrder;
	@Column(name="DT_DATA_DA_COMPRA")
	private Date purchaseDate;

	//One to One
	@OneToOne
	@JoinColumn(name="CD_CARRINHO_COMPRAS", referencedColumnName="CD_CARRINHO_COMPRAS", nullable=false)
	private ShoppingCart shoppingCart;
	@OneToOne
	@JoinColumn(name="CD_ENDERECO", referencedColumnName="CD_ENDERECO", nullable=false)
	private Address shippingAddress;
	
	//Many to One
	@ManyToOne
	@JoinColumn(name="CD_CLIENTE", referencedColumnName="CD_CLIENTE", nullable=false)
	@Cascade(CascadeType.MERGE)
	private Customer customer;
	
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	
	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Long getIdPurchaseHistory() {
		return idPurchaseHistory;
	}

	public void setIdPurchaseHistory(Long idPurchaseHistory) {
		this.idPurchaseHistory = idPurchaseHistory;
	}

	public StatusOfOrder getStatusOfOrder() {
		return statusOfOrder;
	}

	public void setStatusOfOrder(StatusOfOrder statusOfOrder) {
		this.statusOfOrder = statusOfOrder;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
}