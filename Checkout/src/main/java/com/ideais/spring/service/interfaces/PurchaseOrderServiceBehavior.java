package com.ideais.spring.service.interfaces;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;

import com.ideais.spring.domain.checkout.PurchaseOrder;
import com.ideais.spring.exceptions.MissingQuantityStockException;

public interface PurchaseOrderServiceBehavior {

    public PurchaseOrder findById(Long id, Long customerId);

    public List<PurchaseOrder> findAllByCustomerId(Long customerId);
    
    public void save(PurchaseOrder object);

    public void remove(PurchaseOrder object);
    
    public void setPurchaseOrderInSession(HttpServletRequest request, PurchaseOrder purchaseOrder);

	void requestItemsFromStock(PurchaseOrder order) throws IOException, JSONException, MissingQuantityStockException;

	public void removeOrderFromSession(HttpSession session);
	
}