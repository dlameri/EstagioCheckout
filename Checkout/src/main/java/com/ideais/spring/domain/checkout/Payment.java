package com.ideais.spring.domain.checkout;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="PAGAMENTO")
public class Payment {
	
	@Id
	@SequenceGenerator(name = "payment_id", sequenceName = "payment_id")
	@GeneratedValue(generator = "payment_id", strategy = GenerationType.AUTO)
	@Column(name="CD_PAGAMENTO")
	private Long id;
	
	@Column(name="NM_TIPO_PAGAMENTO")
	private String typeOfPayment;
	
	@Column(name="NM_PARCELAS")
    private String installments;
	
	@Column(name="NR_VALOR_TOTAL")
    private BigDecimal amount;
	
	public Payment() {}

	public Payment(String paymentType, String installmentsNumber, BigDecimal totalAmount) {
		this.typeOfPayment = TypeOfPayment.valueOf(paymentType).getPaymentType();
		this.installments = installmentsNumber;
		this.amount = totalAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeOfPayment() {
		return typeOfPayment;
	}

	public void setTypeOfPayment(String typeOfPayment) {
		this.typeOfPayment = typeOfPayment;
	}

	public String getInstallments() {
		return installments;
	}

	public void setInstallments(String installments) {
		this.installments = installments;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
    
}