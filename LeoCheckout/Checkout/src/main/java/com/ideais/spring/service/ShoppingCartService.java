package com.ideais.spring.service;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ideais.spring.api.service.model.json.Cart;
import com.ideais.spring.dao.FreightXmlDao;
import com.ideais.spring.dao.ShoppingCartJsonDao;
import com.ideais.spring.dao.domain.checkout.ShoppingCart;
import com.ideais.spring.dao.domain.checkout.ShoppingCartLine;

@Component("shoppingCartService")
public class ShoppingCartService {
	
	@Autowired
    private ShoppingCartJsonDao shoppingCartJsonDao;
	
	public Cart getCartFromJson(String jsonCart) throws IOException {		
		return shoppingCartJsonDao.getCartFromJson(jsonCart);
	}
	
	public String getJsonCart(ShoppingCart shoppingCart) throws JsonGenerationException, JsonMappingException, IOException {
		return shoppingCartJsonDao.getShoppingCartToCartJson(shoppingCart);
	}
 
}