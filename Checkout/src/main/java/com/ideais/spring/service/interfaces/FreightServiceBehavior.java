package com.ideais.spring.service.interfaces;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.httpclient.HttpException;
import org.jdom2.JDOMException;
import com.ideais.spring.domain.checkout.FreightDetails;
import com.ideais.spring.domain.checkout.PurchaseOrder;
import com.ideais.spring.domain.checkout.ShoppingCart;
import com.ideais.spring.exceptions.FreightException;
import com.ideais.spring.exceptions.FreightZipCodeException;
import com.ideais.spring.exceptions.ItemPackageDimensionException;
import com.ideais.spring.exceptions.ItemPackageVolumeException;
import com.ideais.spring.exceptions.ItemPackageWeightException;

public interface FreightServiceBehavior {
	
	public FreightDetails calculateFreightDetails(ShoppingCart shoppingCart, String zipCode) throws HttpException, IOException, FreightException, FreightZipCodeException, JDOMException, ItemPackageWeightException, ItemPackageVolumeException, ItemPackageDimensionException;
	
	public void recalculateFreight(ShoppingCart shoppingCart, HttpServletRequest request);
	
	public FreightDetails updateFreightDetails(ShoppingCart shoppingCart, FreightDetails freightDetails) throws HttpException, IOException, FreightException, FreightZipCodeException, JDOMException, ItemPackageWeightException, ItemPackageVolumeException, ItemPackageDimensionException;
	
	public void setFreightInSession(FreightDetails freightDetails, ShoppingCart shoppingCart, HttpServletRequest request);
	
	public FreightDetails getFreightDetails(HttpServletRequest request);
	
	public void setFreightDeliverInPurchaseOrder(HttpServletRequest request, PurchaseOrder order);
	
} 