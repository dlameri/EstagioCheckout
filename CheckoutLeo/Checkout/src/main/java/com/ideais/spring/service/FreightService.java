package com.ideais.spring.service;

import java.io.IOException;
import java.math.BigDecimal;
import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ideais.spring.dao.FreightXmlDao;
import com.ideais.spring.dao.domain.catalog.ShoppingCart;

//frete tem que ser calculado depois no order não aqui, fiz pra testes apenas

@Component("freightService")
public class FreightService {
	@Autowired
    private FreightXmlDao freightXmlDao;
	
	public void calculeFreightPriceAndDate(ShoppingCart shoppingCart) throws Exception {
		BigDecimal freight = BigDecimal.ZERO;
		
		for (int i = 0; i < shoppingCart.getSkus().size(); i++) {
			freight = freight.add(freightXmlDao.getFreight(shoppingCart.getSkus().get(i), "24110310"/*shoppingCart.getAddress().getZipCode()*/));
		}
		
		shoppingCart.setFreight(freight);
	}

	public void setFreightXmlDao(FreightXmlDao freightXmlDao) {
		this.freightXmlDao = freightXmlDao;
	}
	
}