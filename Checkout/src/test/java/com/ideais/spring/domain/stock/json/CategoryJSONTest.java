package com.ideais.spring.domain.stock.json;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CategoryJSONTest {

	CategoryJSON categoryJSON;
	
	@Test
	public void check_if_URI_returned_is_the_right_one() {
		Link link = mock(Link.class);
		when(link.getName()).thenReturn("subcategory");
		when(link.getHref()).thenReturn("category/id/subcategory");
		
		List<Link> links = new ArrayList<Link>();
		links.add(link);
		
		categoryJSON = new CategoryJSON();
		categoryJSON.setLinks(links);
		
		assertEquals(link.getHref(), categoryJSON.getURI("subcategory"));
	}
}
