package com.ideais.spring.domain.checkout;

public class Installment {

	private Integer number;
	private String value;
	
	public Installment(Integer number, String value) {
		this.number = number;
		this.value = value;
	}
	
	public Integer getNumber() {
		return number;
	}
	
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public String getValue() {
		return value;
	}
	
	public String getFormattedValue() {
		return number + "x de " + value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
}