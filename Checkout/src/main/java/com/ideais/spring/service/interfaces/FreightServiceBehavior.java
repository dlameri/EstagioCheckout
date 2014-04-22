package com.ideais.spring.service.interfaces;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONException;
import com.ideais.spring.domain.FreightDetails;
import com.ideais.spring.domain.PurchaseOrder;
import com.ideais.spring.domain.ShoppingCart;

public interface FreightServiceBehavior {
	
	public FreightDetails calculateFreightDetails(ShoppingCart shoppingCart, String zipCode) throws Exception;
	
	public void recalculateFreight(ShoppingCart shoppingCart, HttpServletRequest request);
	
	public FreightDetails updateFreightDetails(ShoppingCart shoppingCart, FreightDetails freightDetails) throws Exception;
	
	public void setFreightInSession(FreightDetails freightDetails, ShoppingCart shoppingCart, HttpServletRequest request);
	
	public FreightDetails getFreightDetails(HttpServletRequest request);
	
	public void setFreightDeliverInPurchaseOrder(HttpServletRequest request, PurchaseOrder order);
	
} 