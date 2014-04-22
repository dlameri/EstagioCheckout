package com.ideais.spring.service.interfaces;

import java.io.IOException;
import org.json.JSONException;
import com.ideais.spring.domain.Item;

public interface ItemServiceBehavior {
	
	public Item getItem(Long id) throws IOException, JSONException;
	
	public Boolean refreshItemQuantity(String cartJson);
	
} 