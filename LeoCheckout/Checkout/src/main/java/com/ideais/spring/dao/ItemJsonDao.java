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
import com.ideais.spring.api.service.model.json.Cart;
import com.ideais.spring.dao.domain.checkout.stock.Item;
import com.ideais.spring.util.JsonReaderUtil;

@Component("itemJsonDao")
public class ItemJsonDao {

	@Autowired
	private String stockIntegrationUrl;
	private final String itemUrl = "item/";
	private final String itemsUrl = "itemsbyids";
	private final String updateStockUrl = "item/updatestock";
	private final Integer SUCCESS_RESPONSE_CODE = 200;
	
	public Item getItemFromStock(Long id) throws IOException, JSONException {		
		String jsonItem = JsonReaderUtil.readJsonFromUrl(buildRequestItemString(id));		
		
		return new Gson().fromJson(jsonItem, new TypeToken<Item>(){}.getType());	
	}
	
	public List<Item> getItemsFromStock(List<Long> ids) throws Exception {
    	ObjectMapper mapper = new ObjectMapper();
		ResteasyClient client = new ResteasyClientBuilder().build();
				
        ResteasyWebTarget target = client.target(buildRequestItemsString());
        target.request().accept("application/json");
        Response response = target.request().post(Entity.entity(mapper.writeValueAsString(ids), "application/json"));
        
        System.out.println(mapper.writeValueAsString(ids));
        System.out.println(response.getClass());
        
        return makeItemsRequestFromStock(response);
	}
	
	private List<Item> makeItemsRequestFromStock(Response response) throws Exception {
        if (response.getStatus() != SUCCESS_RESPONSE_CODE) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }	
        
        return response.readEntity(new GenericType<List<Item>>(){});
	}
	
	public Integer updateStock(Cart cart) {
		ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(buildUpdateStockString());
        Response response = target.request().post(Entity.entity(cart, "application/json"));
        
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
	
	private String buildRequestItemsString() {
		return stockIntegrationUrl + itemUrl + itemsUrl; //montar url de verdade, esperar pessoal do stock implementar o serviço
	}
	
	private String buildUpdateStockString() {
		return stockIntegrationUrl + updateStockUrl;
	}
	
}