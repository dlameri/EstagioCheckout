package com.ideais.spring.service;

import com.ideais.spring.dao.PurchaseOrderDao;
import com.ideais.spring.dao.domain.checkout.PurchaseOrder;
import com.ideais.spring.dao.interfaces.PurchaseOrderDaoInterface;

import org.springframework.stereotype.Service;

@Service("purchaseOrderService")
public class PurchaseOrderService {

    private PurchaseOrderDaoInterface purchaseOrderDao;

    public PurchaseOrder findById(Long id, Long customerId) {
        return purchaseOrderDao.findById(id, customerId);
    }

    public void save(PurchaseOrder object) {
    	purchaseOrderDao.saveOrUpdate(object);
    }

    public void remove(PurchaseOrder object) {
    	purchaseOrderDao.remove(object);
    }
    
    public void setPurchaseOrderDaoInterface(PurchaseOrderDao purchaseOrderDao) {
    	this.purchaseOrderDao = purchaseOrderDao;
    }

}