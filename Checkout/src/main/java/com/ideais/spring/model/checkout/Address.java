package com.ideais.spring.model.checkout;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

public class Address {
	private Long id;
	private String street;
	private String number;
	private String complement;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	private Customer customer;
}