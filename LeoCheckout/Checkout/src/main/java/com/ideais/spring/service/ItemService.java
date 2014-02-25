package com.ideais.spring.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ideais.spring.api.service.model.json.Cart;
import com.ideais.spring.dao.ItemJsonDao;
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
	
	public Item getItem(Long id) throws IOException, JSONException {		
		return itemJsonDao.getItemFromStock(id);
	}
	
	public List<Item> getItems(Cart cart) throws Exception {
		List<Long> ids = new ArrayList<Long>();
		
		for (int i = 0; i < cart.getCartItems().size(); i++) {
			ids.add(cart.getCartItems().get(i).getCartItemId());
		}
		
		return itemJsonDao.getItemsFromStock(ids);
	}
	
	public Integer refreshItemQuantity(Cart cart) {
		return itemJsonDao.updateStock(cart.getCartItems());
	}
	
}