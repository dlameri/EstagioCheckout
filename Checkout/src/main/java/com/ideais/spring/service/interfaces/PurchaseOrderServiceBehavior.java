package com.ideais.spring.service.interfaces;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.ideais.spring.domain.checkout.PurchaseOrder;

public interface PurchaseOrderServiceBehavior {

    public PurchaseOrder findById(Long id, Long customerId);

    public List<PurchaseOrder> findAllByCustomerId(Long customerId);
    
    public void save(PurchaseOrder object);

    public void remove(PurchaseOrder object);
    
    public void setPurchaseOrderInSession(HttpServletRequest request, PurchaseOrder purchaseOrder);
	
}