package com.ideais.spring.domain.checkout;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;

import org.junit.Test;

import com.ideais.spring.domain.stock.json.ItemJSON;

public class ItemTest {

	ItemJSON itemJSON = mock(ItemJSON.class);
	Item item;

	@Test
	public void check_if_item_is_created_correctly() {	
		when(itemJSON.getActive()).thenReturn(true);
		when(itemJSON.getId()).thenReturn(1L);
		when(itemJSON.getOptionName()).thenReturn("Cor");
		when(itemJSON.getOptionValue()).thenReturn("Branco");
		when(itemJSON.getPriceFor()).thenReturn(new BigDecimal(100));
		when(itemJSON.getPriceFrom()).thenReturn(new BigDecimal(150));
		when(itemJSON.getSku()).thenReturn(2L);
		when(itemJSON.getStock()).thenReturn(10);
		
		item = new Item(itemJSON);
		
		assertEquals(item.getActive(),itemJSON.getActive());
		assertEquals(item.getId(),itemJSON.getId());
		assertEquals(item.getOptionName(),itemJSON.getOptionName());
		assertEquals(item.getOptionValue(),itemJSON.getOptionValue());
		assertEquals(item.getPriceFor(),itemJSON.getPriceFor());
		assertEquals(item.getPriceFrom(),itemJSON.getPriceFrom());
		assertEquals(item.getSku(),itemJSON.getSku());
		assertEquals(item.getStock(),itemJSON.getStock());
	}
}