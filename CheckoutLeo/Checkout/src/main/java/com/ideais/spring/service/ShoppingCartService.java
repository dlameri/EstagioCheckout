package com.ideais.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ideais.spring.dao.ShoppingCartJsonDao;
import com.ideais.spring.dao.domain.catalog.ShoppingCart;

@Component("shoppingCartService")
public class ShoppingCartService {
	
	@Autowired
	private String shoppingCartItemsUrl;
	
	@Autowired
    private ShoppingCartJsonDao shoppingCartJsonDao;
	
	public ShoppingCart getShoppingCart() {
		return shoppingCartJsonDao.getShoppingCartJson();
	}
	
    public void ShoppingCartJsonDao(ShoppingCartJsonDao shoppingCartJsonDao) {
        this.shoppingCartJsonDao = shoppingCartJsonDao;
    }

}