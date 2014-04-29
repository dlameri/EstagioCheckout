package com.ideais.spring.dao.interfaces;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;

import com.ideais.spring.domain.checkout.ShoppingCart;
import com.ideais.spring.domain.checkout.json.Cart;

public interface ShoppingCartDaoBehavior {
			
	public Cart getCartFromJson(String jsonCart);

	public String getCartToJson(Cart cart) throws JsonGenerationException, JsonMappingException, IOException;
	
	public String getShoppingCartToCartJson(ShoppingCart shoppingCart) throws JsonGenerationException, JsonMappingException, IOException;

}