package com.ideais.spring.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ideais.spring.api.service.model.json.Cart;
import com.ideais.spring.dao.ItemJsonDao;
import com.ideais.spring.dao.ProductJsonDao;
import com.ideais.spring.dao.domain.checkout.stock.Item;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

@Component("itemService")
public class ItemService {
	
	@Autowired
    private ItemJsonDao itemJsonDao;
	@Autowired
    private ProductJsonDao productJsonDao;
	
	public Item getItem(Long id) throws IOException, JSONException {		
		Item item = itemJsonDao.getItemFromStock(id); 
		
		if (item != null) {
			item.setProduct(productJsonDao.getProductFromStock(item.getProductId()));
		}
		
		return item;
	}
	
	public Integer refreshItemQuantity(String cartJson) {
		return itemJsonDao.updateStock(cartJson);
	}
	
}