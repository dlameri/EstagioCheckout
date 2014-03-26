package com.ideais.spring.dao;

import java.io.IOException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ideais.spring.dao.domain.checkout.stock.Product;
import com.ideais.spring.util.JsonReaderUtil;

@Component("productJsonDao")
public class ProductJsonDao {
	
	@Autowired
	private String stockIntegrationUrl;
	private final String productUrl = "product/";
	
	public Product getProductFromStock(Long id) throws IOException, JSONException {
		String jsonProduct = JsonReaderUtil.readJsonFromUrl(buildRequestProductString(id));
		
		return new Gson().fromJson(jsonProduct, new TypeToken<Product>(){}.getType());
	}
	
	private String buildRequestProductString(Long id) {
		return stockIntegrationUrl + productUrl + id;
	}
	
}