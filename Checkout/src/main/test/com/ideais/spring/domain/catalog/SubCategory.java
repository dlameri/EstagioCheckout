package com.ideais.spring.domain.catalog;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.ideais.spring.domain.stock.json.SubcategoryJSON;

public class SubCategory {

	SubcategoryJSON subcategoryJSON = mock(SubcategoryJSON.class);
	Subcategory subcategory;

	@Test
	public void check_if_subcategory_is_created_correctly() {
		
		when(subcategoryJSON.getId()).thenReturn(1L);
		when(subcategoryJSON.getName()).thenReturn("Eletronicos");
		when(subcategoryJSON.getActive()).thenReturn(true);
		
		subcategory = new Subcategory(subcategoryJSON);

		assertEquals(subcategory.getId(),subcategoryJSON.getId());
		assertEquals(subcategory.getName(),subcategoryJSON.getName());
		assertEquals(subcategory.getActive(),subcategoryJSON.getActive());
	}

}

