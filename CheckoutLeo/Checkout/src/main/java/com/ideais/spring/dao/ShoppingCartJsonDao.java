package com.ideais.spring.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ideais.spring.dao.domain.catalog.ShoppingCart;
import com.ideais.spring.util.JsonReaderDaoUtil;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component("shoppingCartJsonDao")
public class ShoppingCartJsonDao {

	@Autowired
	private String shoppingCartUrl;

    public ShoppingCart getShoppingCartJson() {
    	String jsonShoppingCart;
    	
		try {
			jsonShoppingCart = JsonReaderDaoUtil.readJsonFromUrl(shoppingCartUrl);
			
			return new Gson().fromJson(jsonShoppingCart, new TypeToken<ShoppingCart>(){}.getType());
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}	
		
		return null;
    }

}