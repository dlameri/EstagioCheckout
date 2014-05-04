package com.ideais.spring.domain.checkout;

public enum TypeOfPayment {
	CREDIT_CARD("Cartão de crédito"), BILLET("Boleto");
	
	private String paymentType;
	
	TypeOfPayment(String paymentType) {
		this.paymentType = paymentType;
	}
	
	public String getPaymentType() {
		return paymentType;
	}
	
}