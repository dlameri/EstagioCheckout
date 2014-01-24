package com.ideais.spring.service;

import com.ideais.spring.dao.GenericDao;
import com.ideais.spring.model.checkout.PurchaseHistory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("purchaseHistoryService")
public class PurchaseHistoryService implements GenericService<PurchaseHistory> {

	@Autowired
    private GenericDao<PurchaseHistory> purchaseHistoryDao;

    @Override
    public List<PurchaseHistory> listObjects() {
        return purchaseHistoryDao.findAll();
    }

    @Override
    public PurchaseHistory find(Long id) {
        return purchaseHistoryDao.findById( id );
    }

    @Override
    public void save(PurchaseHistory object) {
    	purchaseHistoryDao.saveOrUpdate(object);
    }

    public void setCustomerDao(GenericDao<PurchaseHistory> purchaseHistoryDao) {
        this.purchaseHistoryDao = purchaseHistoryDao;
    }

    @Override
    public void remove(PurchaseHistory object) {
    	purchaseHistoryDao.remove(object);
    }

}