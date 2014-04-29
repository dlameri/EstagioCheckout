package com.ideais.spring.domain.checkout;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Before;
import org.junit.Test;

import com.ideais.spring.domain.checkout.Item;
import com.ideais.spring.domain.checkout.ShoppingCart;
import com.ideais.spring.domain.checkout.ShoppingCartLine;

public class ShoppingCartTest {
	
	ShoppingCartLine shoppingCartLine = mock(ShoppingCartLine.class);
	Item item = mock(Item.class);
	Item item2 = mock(Item.class);

	
	ShoppingCart shoppingCart;
			
	@Before
	public void setUp() {
		shoppingCart = new ShoppingCart();
		
		when(item.getStock()).thenReturn(10);
		when(item.getPriceFor()).thenReturn(new BigDecimal(100));
		when(item.getId()).thenReturn(1L);

		when(item2.getStock()).thenReturn(10);
		when(item2.getPriceFor()).thenReturn(new BigDecimal(100));
	}
	
	@Test
	public void check_quantity_of_items_when_shopping_cart_is_empty() {
		shoppingCart.calculateQuantityOfItems();
		
		assertEquals(new Integer(0), shoppingCart.getQuantityOfItems());
	}
	
	@Test
	public void check_if_quantity_is_correct_with_one_item() {
		try {
			shoppingCart.addItem(item);
		} catch (NullPointerException e) {
			fail("NullPointer inesperado!");
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
		assertEquals(new Integer(1), shoppingCart.getQuantityOfItems());
	}

	@Test
	public void check_total_price_when_shopping_cart_is_empty() {
		shoppingCart.calculateTotalPrice();
		
		assertEquals(new BigDecimal(0).setScale(2, RoundingMode.CEILING), shoppingCart.getTotalAmount());
	}

	@Test
	public void check_if_total_price_is_correct_when_freight_equals_0() {
		try {
			shoppingCart.addItem(item);
		} catch (NullPointerException e) {
			fail("NullPointer inesperado!");
		}
		catch (Exception e) {
			fail(e.getMessage());
		}

		shoppingCart.calculateTotalPrice();

		assertEquals(new BigDecimal(100).setScale(2, RoundingMode.CEILING), shoppingCart.getTotalAmount());
	}
	
	@Test
	public void check_if_quantity_of_total_price_is_correct_with_freight() {
		try {
			shoppingCart.addItem(item);
		} catch (NullPointerException e) {
			fail("NullPointer inesperado!");
		}
		catch (Exception e) {
			fail(e.getMessage());
		}

		shoppingCart.setFreight(new BigDecimal(10));
		
		assertEquals(new BigDecimal(110).setScale(2, RoundingMode.CEILING), shoppingCart.getTotalAmount());
	}

	@Test
	public void check_adding_not_yet_existing_item_to_cart() {
		try {
			shoppingCart.addItem(item);
		} catch (NullPointerException e) {
			fail("NullPointer inesperado!");
		}
		catch (Exception e) {
			fail(e.getMessage());
		}

		assertEquals(new Integer(1), shoppingCart.getShoppingCartLines().get(0).getQuantity());
	}	
	
	@Test
	public void check_adding_existing_item_to_cart() {
		try {
			shoppingCart.addItem(item);
		} catch (NullPointerException e) {
			fail("NullPointer inesperado!");
		}
		catch (Exception e) {
			fail(e.getMessage());
		}

		assertEquals(new Integer(1), shoppingCart.getShoppingCartLines().get(0).getQuantity());
	}
	
	@Test
	public void check_if_shopping_cart_line_is_removed_from_shopping_cart() {
		try {
			shoppingCart.addItem(item);
		} catch (NullPointerException e) {
			fail("NullPointer inesperado!");
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		shoppingCart.removeItem(item);

		assertTrue(shoppingCart.getShoppingCartLines().isEmpty());
	}

	@Test
	public void check_if_cart_is_empty_after_removing_all_shopping_cart_lines() throws Exception {
		shoppingCart.addItem(item);
		shoppingCart.addItem(item2);
		shoppingCart.emptyShoppingCart();
		
		assertTrue(shoppingCart.getShoppingCartLines().isEmpty());
	}

	@Test
	public void check_if_item_is_removed_by_id() throws Exception {
		shoppingCart.addItem(item);
		shoppingCart.removeItemFromId(item.getId());

		assertTrue(shoppingCart.getShoppingCartLines().isEmpty());
	}
	
	@Test
	public void check_if_cart_has_item_with_id() throws Exception {
		shoppingCart.addItem(item);
		
		assertTrue(shoppingCart.hasItemWithId(item.getId()));
	}
	
	@Test
	public void check_if_get_item_return_is_correct() throws Exception {
		shoppingCart.addItem(item);
		
		assertEquals(item, shoppingCart.getItem(item.getId()));
	}
	
	@Test
	public void check_if_quantity_of_shopping_cart_line_is_edited_write() throws Exception {
		shoppingCart.addItem(item);
		shoppingCart.editQuantity(item.getId(), 5);

		assertEquals(new Integer(5), shoppingCart.getShoppingCartLines().get(0).getQuantity());
	}

	@Test
	public void check_if_quantity_of_item_is_formatted_with_quantity_equals_1() throws Exception {
		shoppingCart.addItem(item);

		assertEquals("1 item", shoppingCart.getFormattedQuantityOfItems());
	}
	
	@Test
	public void check_if_quantity_of_item_is_formatted_with_quantity_equals_2() throws Exception {
		shoppingCart.addItem(item);
		shoppingCart.editQuantity(item.getId(), 2);
		
		assertEquals("2 items", shoppingCart.getFormattedQuantityOfItems());
	}	
}