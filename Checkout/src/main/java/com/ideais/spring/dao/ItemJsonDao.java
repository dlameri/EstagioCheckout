package com.ideais.spring.dao;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ideais.spring.api.domain.json.Cart;
import com.ideais.spring.dao.domain.checkout.stock.Item;
import com.ideais.spring.util.JsonReaderUtil;

@Component("itemJsonDao")
public class ItemJsonDao {

	@Autowired
	private String stockIntegrationUrl;
	private final String itemUrl = "item/";
	private final String updateStockUrl = "product/updatestock";
	private final Integer SUCCESS_RESPONSE_CODE = 200;
	
	public Item getItemFromStock(Long id) throws IOException, JSONException {		
		String jsonItem = JsonReaderUtil.readJsonFromUrl(buildRequestItemString(id));
		Item item = new Gson().fromJson(jsonItem, new TypeToken<Item>(){}.getType());
		
		return item;	
	}

	public Integer updateStock(String cartJson) {
		ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(buildUpdateStockString());        
        Response response = target.request().put(Entity.entity(cartJson, "application/json"));
        
        return makeUpdateStockRequest(response);
	}
	
	private Integer makeUpdateStockRequest(Response response) {
        if (response.getStatus() != SUCCESS_RESPONSE_CODE) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }	
        
        return response.getStatus();
	}

	private String buildRequestItemString(Long id) {
		return stockIntegrationUrl + itemUrl + id;
	}
	
	private String buildUpdateStockString() {
		return stockIntegrationUrl + updateStockUrl;
	}
	
}