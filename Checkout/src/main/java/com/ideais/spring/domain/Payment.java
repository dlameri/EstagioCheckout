package com.ideais.spring.domain;

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
	private TypeOfPayment typeOfPayment;
	
	@Column(name="NR_PARCELAS")
    private Integer parcels = 1;
	
	@Column(name="NR_VALOR_TOTAL")
    private BigDecimal amount;
	
	@JsonBackReference
	@OneToOne(mappedBy = "payment")
	@Cascade(CascadeType.MERGE)
	private PurchaseOrder purchaseOrder;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TypeOfPayment getTypeOfPayment() {
		return typeOfPayment;
	}

	public void setTypeOfPayment(TypeOfPayment typeOfPayment) {
		this.typeOfPayment = typeOfPayment;
	}

	public Integer getParcels() {
		return parcels;
	}

	public void setParcels(Integer parcels) {
		this.parcels = parcels;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}
    
}