package com.ideais.spring.service;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ideais.spring.dao.FreightXmlDao;
import com.ideais.spring.dao.ShoppingCartJsonDao;
import com.ideais.spring.dao.domain.checkout.ShoppingCart;

@Component("shoppingCartService")
public class ShoppingCartService {
	
	@Autowired
    private ShoppingCartJsonDao shoppingCartJsonDao;
	
	public ShoppingCart getShoppingCart(String jsonShoppingCart) throws IOException {		
		return shoppingCartJsonDao.getShoppingCartFromJson(jsonShoppingCart);
	}
	
	public String getJsonShoppingCart(ShoppingCart shoppingCart) throws JsonGenerationException, JsonMappingException, IOException {
		return shoppingCartJsonDao.getShoppingCartToJson(shoppingCart);
	}
    
}