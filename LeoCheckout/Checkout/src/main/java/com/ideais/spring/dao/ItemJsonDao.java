package com.ideais.spring.dao;

import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ideais.spring.dao.domain.checkout.stock.Item;
import com.ideais.spring.util.JsonReaderUtil;

@Component("itemJsonDao")
public class ItemJsonDao {

	@Autowired
	private String stockIntegrationUrl;
	
	public Item getItemFromStock(Long id) throws IOException, JSONException {		
		String jsonItem = JsonReaderUtil.readJsonFromUrl(buildRequestString(id));		
		
		return new Gson().fromJson(jsonItem, new TypeToken<Item>(){}.getType());	
	}
	
	private String buildRequestString(Long id) {
		return stockIntegrationUrl + id;
	}
	
}