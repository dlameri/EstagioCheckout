package com.ideais.spring.dao.interfaces;

import java.util.List;
import com.ideais.spring.dao.domain.checkout.PurchaseOrder;

public interface PurchaseOrderDaoInterface {

    public List<PurchaseOrder> findAll(Long customerId);

    public PurchaseOrder findById(Long id, Long customerId);

    public void saveOrUpdate(PurchaseOrder object);

    public void remove(Object object);
	
}