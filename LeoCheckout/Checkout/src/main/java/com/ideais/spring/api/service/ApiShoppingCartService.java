package com.ideais.spring.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.ideais.spring.dao.ShoppingCartJsonDao;
import org.springframework.stereotype.Component;

@Component("apiShoppingCartService")
public class ApiShoppingCartService {
	
	@Autowired
    private ShoppingCartJsonDao shoppingCartJsonDao;
	
	public String getShoppingCartJson() {
		return shoppingCartJsonDao.getShoppingCartToJson();
	}
	
	public String getStockItemsJson() {
		return shoppingCartJsonDao.getStockItemsToJson();
	}
	
}