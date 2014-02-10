package com.ideais.spring.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ideais.spring.api.service.model.json.StockItem;
import com.ideais.spring.dao.domain.catalog.ShoppingCart;
import com.ideais.spring.util.JsonReaderUtil;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component("shoppingCartJsonDao")
public class ShoppingCartJsonDao {
	
	@Autowired
	private String shoppingCartIntegrationUrl;
	private ShoppingCart shoppingCart;

    public ShoppingCart getShoppingCartFromJson() {
    	String jsonShoppingCart;
    	
		try {
			jsonShoppingCart = JsonReaderUtil.readJsonFromUrl(shoppingCartIntegrationUrl);
			
			shoppingCart = new Gson().fromJson(jsonShoppingCart, new TypeToken<ShoppingCart>(){}.getType());
			
			return shoppingCart;
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}	
		
		return null;
    }
   
    public String getShoppingCartToJson() {
    	return new Gson().toJson(shoppingCart);
    }
    
    public String getStockItemsToJson() {
    	List<StockItem> stockItems = new ArrayList<StockItem>();
    	
    	for (int i = 0; i < shoppingCart.getSkus().size(); i++) {
    		stockItems.add(new StockItem(shoppingCart.getSkus().get(i)));
    	}
    	
    	return new Gson().toJson(stockItems);
    }
    
    public ShoppingCart getShoppingCart() {
    	return shoppingCart;
    }
    
}