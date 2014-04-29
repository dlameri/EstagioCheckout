package com.ideais.spring.domain.checkout;

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
	private StatusOfOrder statusOfOrder = StatusOfOrder.WAITING_FOR_PAYMENT;
	
	@Column(name="DT_DATA_DA_COMPRA")
	private Date purchaseDate;	
	
	@Column(name="DT_PREVISAO_CHEGADA")
	private String scheduledDelivery;
	
	@Column(name="NR_FRETE")
	private BigDecimal freight; 
	
	@Column(name="NR_TOTAL")
	private BigDecimal totalAmount; 

	@Column(name="NM_DESTINATARIO")
	private String addressee; 
	
	@OneToOne
	@JoinColumn(name="CD_CARRINHO_COMPRAS", referencedColumnName="CD_CARRINHO_COMPRAS", nullable=false)
	private ShoppingCart shoppingCart;
	
	@OneToOne
	@JoinColumn(name="CD_ENDERECO_ENTREGA", referencedColumnName="CD_ENDERECO", nullable=false)
	private Address shippingAddress;
	
	@OneToOne
	@JoinColumn(name="CD_ENDERECO_COBRANCA", referencedColumnName="CD_ENDERECO", nullable=false)
	private Address billingAddress;
	
	@OneToOne
	@JoinColumn(name="CD_PAGAMENTO", referencedColumnName="CD_PAGAMENTO", nullable=false)
	private Payment payment;
	
	@ManyToOne
	@JoinColumn(name="CD_CLIENTE", referencedColumnName="CD_CLIENTE", nullable=false)
	@Cascade(CascadeType.MERGE)
	private Customer customer;
	
	public PurchaseOrder(Customer customer, ShoppingCart shoppingCart) {
		this.customer = customer;
		this.addressee = customer.getName() + " " + customer.getSurname();
		this.billingAddress = customer.getMainAddress();
		this.shippingAddress = customer.getMainAddress();
		this.shoppingCart = shoppingCart;
		this.freight = shoppingCart.getFreight();
		this.totalAmount = shoppingCart.getTotalAmount();
	}
	
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
		return shoppingCart.getTotalAmount();
	}

	public String getScheduledDelivery() {
		return scheduledDelivery;
	}

	public void setScheduledDelivery(String scheduledDelivery) {
		this.scheduledDelivery = scheduledDelivery;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getAddressee() {
		return addressee;
	}

	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}
	
}