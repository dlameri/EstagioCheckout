package com.ideais.spring.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "CLIENTE")
public class Customer {

	@Id
	@SequenceGenerator(name = "customer_id", sequenceName = "customer_id")
	@GeneratedValue(generator = "customer_id", strategy = GenerationType.AUTO)
	@Column(name = "CD_CLIENTE", nullable = false)
	private Long id;

	@Column(name = "NM_NOME")
	private String name;

	@Column(name = "NM_SOBRENOME")
	private String surname;

	@Column(name = "NM_TELEFONE")
	private String phoneNumber;

	@Column(name = "NM_CPF")
	private String cpf;

	@Column(name = "NM_EMAIL")
	private String email;

	@Column(name = "NM_LOGIN")
	private String username;

	@Column(name = "NM_SENHA")
	private String password;

	@OneToOne(mappedBy = "customer")
	@Cascade(CascadeType.ALL)
	private Address mainAddress;

	@JsonBackReference
	@OneToMany(mappedBy = "customer")
	@Cascade(CascadeType.SAVE_UPDATE)
	private List<ShoppingCart> shoppingCarts;

	@OneToMany(mappedBy = "customer")
	@Cascade(CascadeType.ALL)
	private List<Address> DeliveryAddresses;

	@JsonBackReference
	@OneToMany(mappedBy = "customer")
	@Cascade(CascadeType.SAVE_UPDATE)
	private List<PurchaseOrder> purchaseOrders;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Address> getDeliveryAddresses() {
		return DeliveryAddresses;
	}

	public void setDeliveryAddresses(List<Address> deliveryAddresses) {
		DeliveryAddresses = deliveryAddresses;
	}

	public Address getMainAddress() {
		return mainAddress;
	}

	public void setMainAddress(Address mainAddress) {
		this.mainAddress = mainAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}