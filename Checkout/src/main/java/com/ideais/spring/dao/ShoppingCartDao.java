package com.ideais.spring.dao;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ideais.spring.dao.interfaces.ShoppingCartDaoBehavior;
import com.ideais.spring.domain.ShoppingCart;
import com.ideais.spring.domain.json.Cart;
import com.ideais.spring.domain.json.CartItem;
import com.ideais.spring.util.JsonUtil;

@Service
public class ShoppingCartDao extends BasicRestClientDao implements ShoppingCartDaoBehavior {

	@Override
	public Cart getCartFromJson(String jsonCart) {
		return (Cart) JsonUtil.readJsonToObject(jsonCart, Cart.class);
	}

	@Override
	public String getCartToJson(Cart cart) throws JsonGenerationException,
			JsonMappingException, IOException {
		return JsonUtil.writeObjectToJson(cart);
	}

	@Override
	public String getShoppingCartToCartJson(ShoppingCart shoppingCart) throws JsonGenerationException, JsonMappingException, IOException {	
		Cart cart = new Cart();
    	
    	if (shoppingCart != null) {
	    	for (int i = 0; i < shoppingCart.getShoppingCartLines().size(); i++) {
	    		cart.addStockItem(new CartItem(shoppingCart.getShoppingCartLines().get(i)));
	    	}
    	}
		
		return JsonUtil.writeObjectToJson(cart);
	}

}