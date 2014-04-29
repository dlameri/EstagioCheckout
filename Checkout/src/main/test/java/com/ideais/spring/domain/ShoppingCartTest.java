package java.com.ideais.spring.domain;

import static org.junit.Assert.*;
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

	ShoppingCart shoppingCart;
			
	@Before
	public void setUp() {
		shoppingCart = new ShoppingCart();
		
		when(item.getStock()).thenReturn(10);
		when(item.getPriceFor()).thenReturn(new BigDecimal(100));
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
	public void check_adding_not_yet_existing_shopping_cart_line() {
		try {
			shoppingCart.addItem(item);
			shoppingCart.addItem(item);
		} catch (NullPointerException e) {
			fail("NullPointer inesperado!");
		}
		catch (Exception e) {
			fail(e.getMessage());
		}

		assertEquals(new Integer(2), shoppingCart.getShoppingCartLines().get(0).getQuantity());
	}	
	
	@Test
	public void check_adding_existing_item_to_cart() {
		when(item.getId()).thenReturn(1L);
		
		try {
			shoppingCart.addItem(item);
			shoppingCart.addItem(item);
		} catch (NullPointerException e) {
			fail("NullPointer inesperado!");
		}
		catch (Exception e) {
			fail(e.getMessage());
		}

		assertEquals(new Integer(2), shoppingCart.getShoppingCartLines().get(0).getQuantity());
	}

	@Test
	public void check_if_shopping_cart_line_is_removed_from_shopping_cart() {
		
		when(shoppingCartLine.getItem()).thenReturn(item);
		shoppingCart.remove(shoppingCartLine);

		assertEquals(null, shoppingCart.getShoppingCartLines());
	}

}