package com.ideais.spring.domain.checkout.json;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ideais.spring.domain.checkout.Item;
import com.ideais.spring.domain.checkout.ShoppingCart;
import com.ideais.spring.domain.checkout.ShoppingCartLine;

public class CartTest {
	
	ShoppingCart shoppingCart = mock(ShoppingCart.class);
	ShoppingCartLine shoppingCartLine = mock(ShoppingCartLine.class);
	Item item = mock(Item.class);
	
	Cart cart;

	@Test
	public void check_if_cart_is_created_correctly_with_a_shopping_cart() {
		List<ShoppingCartLine> shoppingCartLines = new ArrayList<ShoppingCartLine>();
		shoppingCartLines.add(shoppingCartLine);
		
		when(shoppingCart.getShoppingCartLines()).thenReturn(shoppingCartLines);
		when(shoppingCartLine.getItem()).thenReturn(item);
		when(item.getItemId()).thenReturn(25L);
		when(shoppingCartLine.getQuantity()).thenReturn(10);
		
		cart = new Cart(shoppingCart);
		
		assertEquals(Long.valueOf(25L), cart.getCartItems().get(0).getCartItemId());
		assertEquals(new Integer(10), cart.getTotalQuantity());
	}
	
	@Test
	public void check_adding_a_cart_item_into_the_cart() {
		CartItem cartItem = mock(CartItem.class);
		
		cart = new Cart();
		cart.addCartItem(cartItem);
		
		assertFalse(cart.getCartItems().isEmpty());
	}
	
	@Test
	public void check_total_quantity_of_cart_with_1_item() {
		List<ShoppingCartLine> shoppingCartLines = new ArrayList<ShoppingCartLine>();
		shoppingCartLines.add(shoppingCartLine);
		
		when(shoppingCart.getShoppingCartLines()).thenReturn(shoppingCartLines);
		when(shoppingCartLine.getItem()).thenReturn(item);
		when(shoppingCartLine.getQuantity()).thenReturn(10);
		
		cart = new Cart(shoppingCart);
		
		assertEquals(new Integer(10), cart.getTotalQuantity());
	}
}