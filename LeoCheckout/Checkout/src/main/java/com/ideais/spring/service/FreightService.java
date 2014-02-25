package com.ideais.spring.service;

import java.io.IOException;
import java.math.BigDecimal;
import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ideais.spring.dao.FreightXmlDao;
import com.ideais.spring.dao.domain.checkout.ItemsPackage;
import com.ideais.spring.dao.domain.checkout.ShoppingCart;

@Component("freightService")
public class FreightService {
	@Autowired
    private FreightXmlDao freightXmlDao;
	
	public void calculeFreightPriceAndDate(ShoppingCart shoppingCart, String zipCode) throws Exception {
		BigDecimal freight = BigDecimal.ZERO;		
		freight = freight.add(freightXmlDao.getFreight(new ItemsPackage(shoppingCart.getShoppingCartLines()), zipCode));
				
		shoppingCart.setFreight(freight);
	}

	public void setFreightXmlDao(FreightXmlDao freightXmlDao) {
		this.freightXmlDao = freightXmlDao;
	}
	
}