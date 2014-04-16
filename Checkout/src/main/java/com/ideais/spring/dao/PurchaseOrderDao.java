package com.ideais.spring.dao;

import com.ideais.spring.dao.domain.checkout.PurchaseOrder;
import com.ideais.spring.dao.interfaces.PurchaseOrderDaoInterface;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component("purchaseOrderDao")
public class PurchaseOrderDao implements PurchaseOrderDaoInterface {

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
    @Transactional
    public void saveOrUpdate(PurchaseOrder object) {
    	PurchaseOrder purchaseHistory = (PurchaseOrder) sessionFactory.getCurrentSession().merge((PurchaseOrder) object);
        sessionFactory.getCurrentSession().save(purchaseHistory);
    }

    @Override
    @Transactional
    public void remove(Object object) {
        sessionFactory.getCurrentSession().delete((PurchaseOrder) object);
    }

}