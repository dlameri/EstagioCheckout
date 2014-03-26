package com.ideais.spring.service;

import com.ideais.spring.dao.GenericDao;
import com.ideais.spring.dao.domain.checkout.PurchaseOrder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("purchaseOrderService")
public class PurchaseOrderService implements GenericService<PurchaseOrder> {

	@Autowired
    private GenericDao<PurchaseOrder> purchaseOrderDao;

    @Override
    public List<PurchaseOrder> listObjects() {
        return purchaseOrderDao.findAll();
    }

    @Override
    public PurchaseOrder find(Long id) {
        return purchaseOrderDao.findById( id );
    }

    @Override
    public void save(PurchaseOrder object) {
    	purchaseOrderDao.saveOrUpdate(object);
    }

    public void setPurchaseOrderDao(GenericDao<PurchaseOrder> purchaseOrderDao) {
        this.purchaseOrderDao = purchaseOrderDao;
    }

    @Override
    public void remove(PurchaseOrder object) {
    	purchaseOrderDao.remove(object);
    }

}