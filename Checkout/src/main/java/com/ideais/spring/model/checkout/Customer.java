package com.ideais.spring.model.checkout;

public class Customer {
	private Long customerFbId;
	private String name;
	private String surname;
	private Address address;
	private String phoneNumber;
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