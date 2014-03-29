package com.ideais.spring.service;

import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
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
	
    public void recalculateFreight(ShoppingCart shoppingCart, HttpServletRequest request) {
		try {
	    	FreightDetails freightDetails = (FreightDetails) request.getSession().getAttribute("freightDetails");
			
			if (freightDetails != null && freightDetails.wasCalculated()) {
				freightDetails = updateFreightDetails(shoppingCart, freightDetails);
				setFreightInSession(freightDetails, shoppingCart, request);
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public FreightDetails updateFreightDetails(ShoppingCart shoppingCart, FreightDetails freightDetails) throws Exception {
		if (shoppingCart.getShoppingCartLines().size() <= 0) {
			shoppingCart.setFreight(BigDecimal.ZERO);
			freightDetails = new FreightDetails();
		} else {
			freightDetails = getFreightDetails(shoppingCart, freightDetails.getDestinationZipCode());
		}
		
		return freightDetails;
	}
    
    public void setFreightInSession(FreightDetails freightDetails, ShoppingCart shoppingCart, HttpServletRequest request) {
		shoppingCart.setFreight(freightDetails.getFreightValue());
		request.getSession().setAttribute("freightDetails", freightDetails);
	}
	
}