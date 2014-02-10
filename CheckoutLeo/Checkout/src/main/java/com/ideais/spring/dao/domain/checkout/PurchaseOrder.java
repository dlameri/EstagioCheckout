package com.ideais.spring.dao.domain.checkout;

import java.math.BigDecimal;
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
	private Long id;
	@Column(name="NM_STATUS_COMPRA")
	private StatusOfOrder statusOfOrder;
	@Column(name="DT_DATA_DA_COMPRA")
	private Date purchaseDate;	
	@Column(name="DT_PREVISAO_CHEGADA")
	private Date scheduledDelivery;
	@Column(name="NR_FRETE")
	private BigDecimal freight; 
	@Column(name="NR_TOTAL")
	private BigDecimal totalAmount; 

	//One to One
	@OneToOne
	@JoinColumn(name="CD_CARRINHO_COMPRAS", referencedColumnName="CD_CARRINHO_COMPRAS", nullable=false)
	private ShoppingCart shoppingCart;
	@OneToOne
	@JoinColumn(name="CD_ENDERECO", referencedColumnName="CD_ENDERECO", nullable=false)
	private Address shippingAddress;
	@OneToOne
	@JoinColumn(name="CD_PAGAMENTO", referencedColumnName="CD_PAGAMENTO", nullable=false)
	private Payment payment;
	
	//Many to One
	@ManyToOne
	@JoinColumn(name="CD_CLIENTE", referencedColumnName="CD_CLIENTE", nullable=false)
	@Cascade(CascadeType.MERGE)
	private Customer customer;
	
	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public BigDecimal getTotalAmount() {
		return shoppingCart.calculateTotalPrice().add(freight);
	}

	public Date getScheduledDelivery() {
		return scheduledDelivery;
	}

	public void setScheduledDelivery(Date scheduledDelivery) {
		this.scheduledDelivery = scheduledDelivery;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}