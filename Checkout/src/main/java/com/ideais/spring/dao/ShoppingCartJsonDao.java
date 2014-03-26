package com.ideais.spring.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ideais.spring.api.service.model.json.Cart;
import com.ideais.spring.api.service.model.json.CartItem;
import com.ideais.spring.dao.domain.checkout.stock.Item;
import com.ideais.spring.dao.domain.checkout.ShoppingCart;
import com.ideais.spring.dao.domain.checkout.ShoppingCartLine;
import com.ideais.spring.util.JsonReaderUtil;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component("shoppingCartJsonDao")
public class ShoppingCartJsonDao {

    public Cart getCartFromJson(String jsonCart) {    			
		return new Gson().fromJson(jsonCart, new TypeToken<Cart>(){}.getType());
	}
   
    public String getCartToJson(Cart cart) throws JsonGenerationException, JsonMappingException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
    	    	
		return mapper.writeValueAsString(cart);
    }
    
    public String getShoppingCartToCartJson(ShoppingCart shoppingCart) throws JsonGenerationException, JsonMappingException, IOException {
    	ObjectMapper mapper = new ObjectMapper();  	
    	Cart cart = new Cart();
    	
    	if (shoppingCart != null) {
	    	for (int i = 0; i < shoppingCart.getShoppingCartLines().size(); i++) {
	    		cart.addStockItem(createCartItem(shoppingCart.getShoppingCartLines().get(i)));
	    	}
    	}
    	
    	return mapper.writeValueAsString(cart);
    }
    
    private CartItem createCartItem(ShoppingCartLine shoppingCartLine) {
    	Item item = shoppingCartLine.getItem();
		Integer quantity = shoppingCartLine.getQuantity();
		
		return new CartItem(item, quantity);
    }
    
}