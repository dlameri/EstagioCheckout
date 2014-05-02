package com.ideais.spring.domain.stock.json;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SubCategoryJSONTest {

	SubcategoryJSON subcategoryJSON;
	
	@Test
	public void check_if_URI_returned_is_the_right_one() {
		Link link = mock(Link.class);
		when(link.getName()).thenReturn("product");
		when(link.getHref()).thenReturn("subcategory/id/product");
		
		List<Link> links = new ArrayList<Link>();
		links.add(link);
		
		subcategoryJSON = new SubcategoryJSON();
		subcategoryJSON.setLinks(links);
		
		assertEquals(link.getHref(), subcategoryJSON.getURI("product"));
	}
}