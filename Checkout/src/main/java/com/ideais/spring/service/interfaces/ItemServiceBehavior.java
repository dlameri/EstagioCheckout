package com.ideais.spring.service.interfaces;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpException;
import org.json.JSONException;

import com.ideais.spring.domain.checkout.Item;
import com.ideais.spring.domain.checkout.PurchaseOrder;

public interface ItemServiceBehavior {
	
	public Item getItem(Long id) throws IOException, JSONException;
	
	public Boolean refreshItemQuantity(String cartJson);

	public List<Item> checkStock(PurchaseOrder order) throws HttpException, IOException;
	
} 