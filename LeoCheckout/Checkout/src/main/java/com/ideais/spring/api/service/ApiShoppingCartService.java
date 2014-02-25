package com.ideais.spring.api.service;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import com.ideais.spring.api.service.model.json.Cart;
import com.ideais.spring.dao.ShoppingCartJsonDao;
import com.ideais.spring.dao.domain.checkout.ShoppingCart;
import com.ideais.spring.dao.domain.checkout.ShoppingCartLine;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;

@Component("apiShoppingCartService")
public class ApiShoppingCartService {
	
	@Autowired
    private ShoppingCartJsonDao shoppingCartJsonDao;
	
	public Cart getShoppingCartLines(String jsonCart) {
		return shoppingCartJsonDao.getCartFromJson(jsonCart);
	}
	
	public String getCartJson(ShoppingCart shoppingCart) throws JsonGenerationException, JsonMappingException, IOException {
		return shoppingCartJsonDao.getShoppingCartToCartJson(shoppingCart);
	}
	
}