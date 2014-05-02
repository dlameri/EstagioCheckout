package com.ideais.spring.domain.stock.json;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class ItemJSONTest {
	
	ItemJSON itemJSON;
	
	@Test
	public void check_if_URI_returned_is_the_right_one() {
		Link link = mock(Link.class);
		when(link.getName()).thenReturn("image");
		when(link.getHref()).thenReturn("item/id/image");
		
		List<Link> links = new ArrayList<Link>();
		links.add(link);
		
		itemJSON = new ItemJSON();
		itemJSON.setLinks(links);
		
		assertEquals(link.getHref(), itemJSON.getURI("image"));
	}

}
