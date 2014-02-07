package com.ideais.spring.dao;

import com.ideais.spring.dao.domain.checkout.PurchaseOrder;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component("purchaseOrderDao")
public class PurchaseOrderDao implements GenericDao<PurchaseOrder> {

	@Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
	@Override
    @Transactional(readOnly = true)
    public List<PurchaseOrder> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from PurchaseOrder").list();
    }

    @Override
    @Transactional(readOnly = true)
    public PurchaseOrder findById(Long id) {
        return (PurchaseOrder) sessionFactory.getCurrentSession().get(PurchaseOrder.class, id);
    }

    @Override
    @Transactional
    public void saveOrUpdate(PurchaseOrder object) {
    	PurchaseOrder purchaseHistory = (PurchaseOrder) sessionFactory.getCurrentSession().merge((PurchaseOrder) object);
        sessionFactory.getCurrentSession().save(purchaseHistory);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void remove(Object object) {
        sessionFactory.getCurrentSession().delete((PurchaseOrder) object);
    }

}