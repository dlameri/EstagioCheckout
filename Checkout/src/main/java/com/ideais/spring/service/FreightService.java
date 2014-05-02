package com.ideais.spring.service;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpException;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideais.spring.dao.interfaces.FreightDaoBehavior;
import com.ideais.spring.domain.checkout.FreightDetails;
import com.ideais.spring.domain.checkout.ItemsPackage;
import com.ideais.spring.domain.checkout.PurchaseOrder;
import com.ideais.spring.domain.checkout.ShoppingCart;
import com.ideais.spring.exceptions.FreightException;
import com.ideais.spring.exceptions.FreightZipCodeException;
import com.ideais.spring.exceptions.ItemPackageDimensionException;
import com.ideais.spring.exceptions.ItemPackageVolumeException;
import com.ideais.spring.exceptions.ItemPackageWeightException;
import com.ideais.spring.service.interfaces.FreightServiceBehavior;

@Service("freightService")
public class FreightService implements FreightServiceBehavior{
	
	@Autowired
    private FreightDaoBehavior freightDao;
    private static final String FREIGHT_KEY = "freightDetails";
	
    @Override
	public FreightDetails calculateFreightDetails(ShoppingCart shoppingCart, String zipCode) throws HttpException, IOException, FreightException, FreightZipCodeException, JDOMException, ItemPackageWeightException, ItemPackageVolumeException, ItemPackageDimensionException  {
		return freightDao.getFreight(new ItemsPackage(shoppingCart.getShoppingCartLines()), zipCode);
	}
	
    @Override
    public void recalculateFreight(ShoppingCart shoppingCart, HttpServletRequest request) {
		try {
	    	FreightDetails freightDetails = (FreightDetails) request.getSession().getAttribute(FREIGHT_KEY);
			
			if (freightDetails != null && freightDetails.wasCalculated()) {
				freightDetails = updateFreightDetails(shoppingCart, freightDetails);
				setFreightInSession(freightDetails, shoppingCart, request);
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    @Override
    public FreightDetails updateFreightDetails(ShoppingCart shoppingCart, FreightDetails freightDetails) throws HttpException, IOException, FreightException, FreightZipCodeException, JDOMException, ItemPackageWeightException, ItemPackageVolumeException, ItemPackageDimensionException {
		if (shoppingCart.getShoppingCartLines().size() <= 0) {
			shoppingCart.setFreight(BigDecimal.ZERO);
			freightDetails = new FreightDetails();
		} else {
			freightDetails = calculateFreightDetails(shoppingCart, freightDetails.getDestinationZipCode());
		}
		
		return freightDetails;
	}
    
    @Override
    public void setFreightInSession(FreightDetails freightDetails, ShoppingCart shoppingCart, HttpServletRequest request) {
		shoppingCart.setFreight(freightDetails.getFreightValue());
		request.getSession().setAttribute(FREIGHT_KEY, freightDetails);
	}

    @Override
	public FreightDetails getFreightDetails(HttpServletRequest request) {
		return (FreightDetails) request.getSession().getAttribute(FREIGHT_KEY);
	}
	
    @Override
	public void setFreightDeliverInPurchaseOrder(HttpServletRequest request, PurchaseOrder order) {
		FreightDetails freightDetails = getFreightDetails(request);
		
		if (freightDetails != null) {
			order.setScheduledDelivery(freightDetails.getDeliveryDays());
			order.setFreight(freightDetails.getFreightValue());
		}
	}
	
}