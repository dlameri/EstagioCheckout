package com.ideais.spring.domain.checkout;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;

public class PurchaseOrderTest {

	Customer customer = mock(Customer.class);
	Address address = mock(Address.class);
	ShoppingCart shoppingCart = mock(ShoppingCart.class);
	
	PurchaseOrder purchaseOrder = new PurchaseOrder(customer, shoppingCart);
	
	@Test
	public void test_check_if_purchase_order_is_created_correctly() {
		
		when(customer.getName()).thenReturn("Joao");
		when(customer.getSurname()).thenReturn("Silva");
		when(customer.getMainAddress()).thenReturn(address);
		when(customer.getMainAddress()).thenReturn(address);
		when(shoppingCart.getFreight()).thenReturn(new BigDecimal(10));
		when(shoppingCart.getTotalAmount()).thenReturn(new BigDecimal(100));
		
		purchaseOrder = new PurchaseOrder(customer, shoppingCart);
		
		assertEquals(customer, purchaseOrder.getCustomer());
		assertEquals(customer.getName() + " " +  customer.getSurname(),purchaseOrder.getAddressee());
		assertEquals(customer.getMainAddress(), purchaseOrder.getBillingAddress());
		assertEquals(customer.getMainAddress(), purchaseOrder.getShippingAddress());
		assertEquals(shoppingCart.getFreight(), purchaseOrder.getFreight());
		assertEquals(shoppingCart.getTotalAmount(), purchaseOrder.getTotalAmount());
	}
}
