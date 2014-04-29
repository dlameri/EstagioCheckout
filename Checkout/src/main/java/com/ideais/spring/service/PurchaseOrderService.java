package com.ideais.spring.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ideais.spring.dao.interfaces.PurchaseOrderDaoBehavior;
import com.ideais.spring.domain.checkout.PurchaseOrder;
import com.ideais.spring.service.interfaces.PurchaseOrderServiceBehavior;

import org.springframework.stereotype.Service;

@Service("purchaseOrderService")
public class PurchaseOrderService implements PurchaseOrderServiceBehavior {

    private PurchaseOrderDaoBehavior purchaseOrderDao;
    private static final String ORDER_KEY = "order";

    @Override
    public PurchaseOrder findById(Long id, Long customerId) {
        return purchaseOrderDao.findById(id, customerId);
    }
    
    @Override
    public List<PurchaseOrder> findAllByCustomerId(Long customerId) {
        return purchaseOrderDao.findAll(customerId);
    }

    @Override
    public void save(PurchaseOrder object) {
    	purchaseOrderDao.saveOrUpdate(object);
    }

    @Override
    public void remove(PurchaseOrder object) {
    	purchaseOrderDao.remove(object);
    }
    
    @Override
    public void setPurchaseOrderInSession(HttpServletRequest request, PurchaseOrder purchaseOrder) {
		request.getSession().setAttribute(ORDER_KEY, purchaseOrder);
    }

}