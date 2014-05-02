package com.ideais.spring.domain.catalog;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.ideais.spring.domain.stock.json.SubcategoryJSON;

public class SubCategory {

	@Test
	public void check_if_subcategory_is_created_correctly() {

		SubcategoryJSON subcategoryJSON = mock(SubcategoryJSON.class);
		
		when(subcategoryJSON.getId()).thenReturn(1L);
		when(subcategoryJSON.getName()).thenReturn("Eletronicos");
		when(subcategoryJSON.getActive()).thenReturn(true);
		
		Subcategory subcategory = new Subcategory(subcategoryJSON);

		assertEquals(subcategory.getId(),subcategoryJSON.getId());
		assertEquals(subcategory.getName(),subcategoryJSON.getName());
		assertEquals(subcategory.getActive(),subcategoryJSON.getActive());
	}

}

