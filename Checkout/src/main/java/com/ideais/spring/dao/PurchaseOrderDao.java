package com.ideais.spring.dao;

import com.ideais.spring.dao.interfaces.PurchaseOrderDaoBehavior;
import com.ideais.spring.domain.checkout.PurchaseOrder;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component("purchaseOrderDao")
public class PurchaseOrderDao implements PurchaseOrderDaoBehavior {

	@Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<PurchaseOrder> findAll(Long customerId) {
        return sessionFactory.getCurrentSession().createCriteria(PurchaseOrder.class).
        		add(Restrictions.eq("customer.id", customerId)).list();
    }

    @Transactional(readOnly = true)
    public PurchaseOrder findById(Long id, Long customerId) {
        return (PurchaseOrder) sessionFactory.getCurrentSession().createCriteria(PurchaseOrder.class).
        		add(Restrictions.eq("customer.id", customerId)).add(Restrictions.eq("id", id)).uniqueResult();
    }
    
    @Override
    @Transactional(readOnly = true)
    public PurchaseOrder findByPurchaseDate(Date date, Long customerId) {
        return (PurchaseOrder) sessionFactory.getCurrentSession().createCriteria(PurchaseOrder.class).
        		add(Restrictions.eq("customer.id", customerId)).add(Restrictions.eq("purchaseDate", date)).uniqueResult();
    }

    @Override
    @Transactional
    public void saveOrUpdate(PurchaseOrder object) {
    	PurchaseOrder purchaseOrder = (PurchaseOrder) sessionFactory.getCurrentSession().merge((PurchaseOrder) object);
        sessionFactory.getCurrentSession().save(purchaseOrder);
    }

    @Override
    @Transactional
    public void remove(Object object) {
        sessionFactory.getCurrentSession().delete((PurchaseOrder) object);
    }

}