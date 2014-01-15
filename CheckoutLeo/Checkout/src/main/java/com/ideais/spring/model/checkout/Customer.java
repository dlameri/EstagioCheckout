package com.ideais.spring.model.checkout;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="CLIENTE")
public class Customer {
	
	@Id
	@SequenceGenerator(name = "customer_id", sequenceName = "customer_id")
	@GeneratedValue(generator = "customer_id", strategy = GenerationType.AUTO)
	@Column(name="CD_CLIENTE", nullable=false)
	private Long customerFbId;
	@Column(name="NM_NOME")
	private String name;
	@Column(name="NM_SOBRENOME")
	private String surname;
	@Column(name="NM_TELEFONE")
	private String phoneNumber;
	@OneToOne(mappedBy="customer")
	@Cascade(CascadeType.ALL)
	private Address address;
	@OneToOne(mappedBy="customer")
	private PurchaseHistory purchaseHistory;
	
	public Customer() {
		
	}

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public PurchaseHistory getPurchaseHistory() {
		return purchaseHistory;
	}

	public void setPurchaseHistory(PurchaseHistory purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
	}
	
}