package com.ideais.spring.dao.domain.checkout;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "CLIENTE")
public class Customer {

	@Id
	@SequenceGenerator(name = "customer_id", sequenceName = "customer_id")
	@GeneratedValue(generator = "customer_id", strategy = GenerationType.AUTO)
	@Column(name = "CD_CLIENTE", nullable = false)
	private Long customerFbId;
	
	@Column(name = "NM_NOME")
	private String name;
	
	@Column(name = "NM_SOBRENOME")
	private String surname;
	
	@Column(name = "NM_TELEFONE")
	private String phoneNumber;

	@JsonBackReference
	@OneToMany(mappedBy = "customer")
	@Cascade(CascadeType.SAVE_UPDATE)
	private List<ShoppingCart> shoppingCarts;
	
	@OneToMany(mappedBy = "customer")
	@Cascade(CascadeType.ALL)
	private List<Address> addresses;

	@JsonBackReference
	@OneToMany(mappedBy = "customer")
	@Cascade(CascadeType.SAVE_UPDATE)
	private List<PurchaseOrder> purchaseOrders;

	public Long getCustomerFbId() {
		return customerFbId;
	}

	public void setCustomerFbId(Long userFbId) {
		this.customerFbId = userFbId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<PurchaseOrder> getPurchaseOrders() {
		return purchaseOrders;
	}

	public void setPurchaseOrders(List<PurchaseOrder> purchaseOrders) {
		this.purchaseOrders = purchaseOrders;
	}

	public List<ShoppingCart> getShoppingCarts() {
		return shoppingCarts;
	}

	public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}
}