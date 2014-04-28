package com.ideais.spring.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ShoppingCartTest {
	
	Item item = mock(Item.class);
	List<Item> items = new ArrayList<Item>();

	ShoppingCartLine shoppingCartLine = mock(ShoppingCartLine.class);
	ShoppingCart shoppingCart;
			
	@Before
	public void setUp() {
		items.add(item);
		when(item.getPriceFor()).thenReturn(new BigDecimal(300));		
	}
	
	@Test
	public void check_quantity_of_items_when_shopping_cart_is_empty() {
		shoppingCart = new ShoppingCart();
		shoppingCart.calculateQuantityOfItems();
		
		assertEquals(new Integer(0), shoppingCart.getQuantityOfItems());
	}
	
	@Test
	public void check_if_quantity_of_items_is_correct() {
		shoppingCart = new ShoppingCart(items);
		
		assertEquals(new Integer(1), shoppingCart.getQuantityOfItems());
	}

	@Test
	public void check_total_price_when_shopping_cart_is_empty() {
		shoppingCart = new ShoppingCart();
		shoppingCart.calculateQuantityOfItems();
		
		assertEquals(new BigDecimal(0).setScale(2, RoundingMode.CEILING), shoppingCart.getTotalAmount());
	}

	@Test
	public void check_if_quantity_of_total_price_is_correct_with_freight_equals_0() {
		shoppingCart = new ShoppingCart(items);

		assertEquals(new BigDecimal(300).setScale(2, RoundingMode.CEILING), shoppingCart.getTotalAmount());
	}
	
	@Test
	public void check_if_quantity_of_total_price_is_correct_with_freight() {
		shoppingCart = new ShoppingCart(items);
		shoppingCart.setFreight(new BigDecimal(10));
		
		assertEquals(new BigDecimal(310).setScale(2, RoundingMode.CEILING), shoppingCart.getTotalAmount());
	}

//	@Test
//	public void check_adding_existing_shopping_cart_line() {
//		shoppingCart = new ShoppingCart(items);
//
//		when(shoppingCartLine.getItem()).thenReturn(item);		
//		shoppingCart.add(shoppingCartLine);
//		
//		assertEquals(new Integer(2), shoppingCartLine.getQuantity());
//	}
//	
	@Test
	public void check_adding_non_existing_shopping_cart_line() {
		
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
