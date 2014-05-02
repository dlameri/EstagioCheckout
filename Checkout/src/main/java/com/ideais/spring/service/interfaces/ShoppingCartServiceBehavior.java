package com.ideais.spring.service.interfaces;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;

import com.ideais.spring.domain.checkout.ShoppingCart;
import com.ideais.spring.domain.checkout.json.Cart;
import com.ideais.spring.exceptions.MissingQuantityStockException;

public interface ShoppingCartServiceBehavior {
	
	public Cart getCartFromJson(String jsonCart) throws IOException;
	
	public String getJsonCart(ShoppingCart shoppingCart) throws JsonGenerationException, JsonMappingException, IOException;
	
	public ShoppingCart getShoppingCart(String cartCookie, HttpServletRequest request) throws IOException, NumberFormatException, MissingQuantityStockException, JSONException;

	public Cookie createCartCookie(ShoppingCart shoppingCart) throws JsonGenerationException, JsonMappingException, IOException;
	
	public void addItemToShoppingCart(Long id, ShoppingCart shoppingCart) throws IOException, JSONException, MissingQuantityStockException;
    
    public void removeItemToShoppingCart(Long id, ShoppingCart shoppingCart);
    
    public void setShoppingCartInSession(ShoppingCart shoppingCart, HttpServletRequest request);
    
    public Cookie createCartTopCookie(ShoppingCart shoppingCart) throws JsonGenerationException, JsonMappingException, IOException;

    public Integer cartQtd(HttpServletRequest request) throws HttpException, IOException;
    
} 