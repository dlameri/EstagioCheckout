package com.ideais.spring.api.service;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import com.ideais.spring.dao.ShoppingCartJsonDao;
import com.ideais.spring.dao.domain.checkout.ShoppingCart;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;

@Component("apiShoppingCartService")
public class ApiShoppingCartService {
	
	@Autowired
    private ShoppingCartJsonDao shoppingCartJsonDao;
	
	public ShoppingCart getShoppingCart(String jsonShoppingCart) {
		return shoppingCartJsonDao.getShoppingCartFromJson(jsonShoppingCart);
	}
	
	public String getCartJson(ShoppingCart shoppingCart) throws JsonGenerationException, JsonMappingException, IOException {
		return shoppingCartJsonDao.getCartToJson(shoppingCart);
	}
	
}