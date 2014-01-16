package com.ideais.spring.model.checkout;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="HISTORICO_COMPRAS")
public class PurchaseHistory {
	
	//TODO: ShippingAddress que é uma lista de endereços de entrega associada ao cliente
	
	@Id
	@SequenceGenerator(name = "purchaseHistory_id", sequenceName = "purchaseHistory_id")
	@GeneratedValue(generator = "purchaseHistory_id", strategy = GenerationType.AUTO)
	@Column(name="CD_HISTORICO_COMPRAS")
	private Long idPurchaseHistory;
	@OneToMany(mappedBy="purchaseHistory")
	@Cascade(CascadeType.ALL)
	private List<ShoppingCart> shoppingCarts = new ArrayList<ShoppingCart>();
	@OneToOne
	@JoinColumn(name="CD_CLIENTE", referencedColumnName="CD_CLIENTE", nullable=false)
	private Customer customer;
	
	public List<ShoppingCart> getShoppingCarts() {
		return shoppingCarts;
	}
	
	public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}