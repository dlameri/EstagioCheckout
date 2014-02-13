package com.ideais.spring.service;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ideais.spring.dao.FreightXmlDao;
import com.ideais.spring.dao.ShoppingCartJsonDao;
import com.ideais.spring.dao.domain.catalog.ShoppingCart;
import com.ideais.spring.dao.domain.catalog.Sku;

@Component("shoppingCartService")
public class ShoppingCartService {
	
	@Autowired
    private ShoppingCartJsonDao shoppingCartJsonDao;
	
	public ShoppingCart getShoppingCart() throws IOException {		
		return shoppingCartJsonDao.getShoppingCartFromJson();
	}
	
    public void ShoppingCartJsonDao(ShoppingCartJsonDao shoppingCartJsonDao) {
        this.shoppingCartJsonDao = shoppingCartJsonDao;
    }
    
}