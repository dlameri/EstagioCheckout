package com.ideais.spring.domain.checkout;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="ENDERECO")
public class Address {
	
	@Id
	@SequenceGenerator(name = "address_id", sequenceName = "address_id")
	@GeneratedValue(generator = "address_id", strategy = GenerationType.AUTO)
	@Column(name="CD_ENDERECO", nullable=false)
	private Long id;
	
	@Column(name="NM_RUA")
	private String street;
	
	@Column(name="NM_NUMERO")
	private String number;
	
	@Column(name="NM_COMPLEMENTO")
	private String complement;
	
	@Column(name="NM_CIDADE")
	private String city;
	
	@Column(name="NM_ESTADO")
	private String state;
	
	@Column(name="NM_PAIS")
	private String country;
	
	@Column(name="NM_BAIRRO")
	private String neighborhood;
	
	@Column(name="NM_CEP")
	private String zipCode;
	
	@Column(name="BO_PRINCIPAL")
	private Boolean main;
	
	@Column(name="NM_REFERENCIA")
	private String reference;
	
	@Column(name="NM_NAME")
	private String name;
	
	@Column(name="NM_DESTINATARIO")
	private String addressee; 
	
	@ManyToOne(targetEntity=Customer.class)
	@JoinColumn(name="CD_CLIENTE", referencedColumnName="CD_CLIENTE", nullable=false)
	@Cascade(CascadeType.MERGE)
	private Customer customer;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getComplement() {
		return complement;
	}
	
	public void setComplement(String complement) {
		this.complement = complement;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getZipCode() {
		return zipCode;
	}
	
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public Boolean getMain() {
		return main;
	}

	public void setMain(Boolean main) {
		this.main = main;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFormattedZipCode() {
		if (zipCode.contains("-")) {
			String[] formattedZipCode = zipCode.split("-"); 
			return formattedZipCode[0] + formattedZipCode[1];
		}
		
		return zipCode;
	}

	public String getAddressee() {
		return addressee;
	}

	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}
	
	public String getFormattedAdress(){
		return "Endereço: "+street +", nº:"+ number+"\nCidade: "+city+", cep:"+getFormattedZipCode()+
				"\nDestinatário: "+addressee;
	}
	
}