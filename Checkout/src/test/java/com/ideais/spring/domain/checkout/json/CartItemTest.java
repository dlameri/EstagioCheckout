package com.ideais.spring.domain.checkout.json;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.ideais.spring.domain.checkout.Item;
import com.ideais.spring.domain.checkout.ShoppingCartLine;


public class CartItemTest {

	ShoppingCartLine shoppingCartLine = mock(ShoppingCartLine.class);
	Item item = mock(Item.class);
	
	CartItem cartItem;
	
	public void setUp() {
		
	}
	
	@Test
	public void check_if_cart_item_is_created_correctly_with_a_shopping_cart_line() {
		
		when(shoppingCartLine.getItem()).thenReturn(item);
		when(item.getItemId()).thenReturn(new Long(1));
		when(shoppingCartLine.getQuantity()).thenReturn(10);
		
		cartItem = new CartItem(shoppingCartLine);
		
		assertEquals(new Long(1), cartItem.getCartItemId());
		assertEquals(new Integer(10), cartItem.getQuantity());
	}
	
	@Test
	public void check_if_cart_item_is_created_correctly_with_an_item() {
		
		when(item.getItemId()).thenReturn(new Long(1));
		
		cartItem = new CartItem(item, 10);
	
		assertEquals(new Long(1), cartItem.getCartItemId());
		assertEquals(new Integer(10), cartItem.getQuantity());
	}
}