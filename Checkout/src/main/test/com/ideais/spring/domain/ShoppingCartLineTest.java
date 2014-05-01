package com.ideais.spring.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.ideais.spring.domain.checkout.Item;
import com.ideais.spring.domain.checkout.ShoppingCartLine;

public class ShoppingCartLineTest {
	
	Item item = mock(Item.class);
	ShoppingCartLine shoppingCartLine = new ShoppingCartLine(item);	
	
	@Before
	public void setUp() {
		when(item.getPriceFor()).thenReturn(new BigDecimal(100));
		when(item.getStock()).thenReturn(10);
	}
	
	@Test
	public void check_if_quantity_is_set_right() throws Exception {
		shoppingCartLine.setQuantity(5);
		assertEquals(new Integer(5), shoppingCartLine.getQuantity());
	}
	
	@Test
	public void check_if_unsupported_quantity_throws_exception() {
		 try {
			shoppingCartLine.setQuantity(0);
			fail("Test failed!");
		 } catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void check_if_quantity_greater_than_stock_throws_exception() {
		try {
			shoppingCartLine.setQuantity(100);
			fail("Test failed!");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void check_shopping_cart_line_price() throws Exception {
		shoppingCartLine.setQuantity(10);	
		shoppingCartLine.calculatePrice();
		
		assertEquals(new BigDecimal(1000), shoppingCartLine.getPrice());
	}
	
	@Test
	public void check_if_quantity_is_increased_properly() throws Exception {
		shoppingCartLine.setQuantity(9);
		shoppingCartLine.calculatePrice();
		shoppingCartLine.increaseByOne();
		
		assertEquals(new Integer(10), shoppingCartLine.getQuantity());
	}

}