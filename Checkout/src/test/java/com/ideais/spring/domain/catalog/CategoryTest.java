package com.ideais.spring.domain.catalog;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.ideais.spring.domain.stock.json.CategoryJSON;

public class CategoryTest {

	@Test
	public void check_if_category_is_created_correctly() {
		CategoryJSON categoryJSON = mock(CategoryJSON.class);
		
		when(categoryJSON.getId()).thenReturn(1L);
		when(categoryJSON.getName()).thenReturn("Eletronicos");
		when(categoryJSON.getActive()).thenReturn(true);
		
		Category category = new Category(categoryJSON);

		assertEquals(category.getId(),categoryJSON.getId());
		assertEquals(category.getName(),categoryJSON.getName());
		assertEquals(category.getActive(),categoryJSON.getActive());
	}
}
