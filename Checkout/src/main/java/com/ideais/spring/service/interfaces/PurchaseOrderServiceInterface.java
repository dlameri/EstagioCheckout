package com.ideais.spring.service.interfaces;

import com.ideais.spring.dao.domain.checkout.PurchaseOrder;

public interface PurchaseOrderServiceInterface {

    public PurchaseOrder findById(Long id, Long customerId);

    public void save(PurchaseOrder object);

    public void remove(PurchaseOrder object);
	
}