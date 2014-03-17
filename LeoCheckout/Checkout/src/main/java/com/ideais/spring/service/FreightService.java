package com.ideais.spring.service;

import java.io.IOException;
import java.math.BigDecimal;
import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ideais.spring.dao.FreightXmlDao;
import com.ideais.spring.dao.domain.checkout.FreightDetails;
import com.ideais.spring.dao.domain.checkout.ItemsPackage;
import com.ideais.spring.dao.domain.checkout.ShoppingCart;

@Component("freightService")
public class FreightService {
	
	@Autowired
    private FreightXmlDao freightXmlDao;
	
	public FreightDetails getFreightDetails(ShoppingCart shoppingCart, String zipCode) throws Exception {
		return freightXmlDao.getFreight(new ItemsPackage(shoppingCart.getShoppingCartLines()), zipCode);
	}
	
}