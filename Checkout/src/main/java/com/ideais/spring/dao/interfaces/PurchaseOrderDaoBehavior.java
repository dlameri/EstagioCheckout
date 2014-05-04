package com.ideais.spring.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.ideais.spring.domain.checkout.PurchaseOrder;

public interface PurchaseOrderDaoBehavior {

    public List<PurchaseOrder> findAll(Long customerId);

    public PurchaseOrder findById(Long id, Long customerId);

    public void saveOrUpdate(PurchaseOrder object);

    public void remove(Object object);

	public PurchaseOrder findByPurchaseDate(Date date, Long customerId);
	
}