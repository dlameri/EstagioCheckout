package com.ideais.spring.domain.checkout;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

public class PurchaseOrderTest {

	Customer customer = mock(Customer.class);
	ShoppingCart shoppingCart = mock(ShoppingCart.class);
	
	PurchaseOrder purchaseOrder = new PurchaseOrder(customer, shoppingCart);
	
	public void check_if_purchase_order_is_created_correctly() {
		
		when(customer.getName()).thenReturn("Joao");
		when(customer.getSurname()).thenReturn("Silva");
		when(customer.getMainAddress()).thenReturn("Rua blablabla");
		when(customer.getMainAddress()).thenReturn("Rua blablabla");
		when(shoppingCart.getFreight()).thenReturn(new BigDecimal(10));
		when(shoppingCart.getTotalAmount()).thenReturn(new BigDecimal(100));
	}
}
