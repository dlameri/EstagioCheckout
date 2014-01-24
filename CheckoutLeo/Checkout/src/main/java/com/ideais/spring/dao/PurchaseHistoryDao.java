package com.ideais.spring.dao;

import com.ideais.spring.model.checkout.PurchaseHistory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component("purchaseHistoryDao")
public class PurchaseHistoryDao implements GenericDao<PurchaseHistory> {

	@Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
	@Override
    @Transactional(readOnly = true)
    public List<PurchaseHistory> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from HISTORICO_COMPRAS").list();
    }

    @Override
    @Transactional(readOnly = true)
    public PurchaseHistory findById(Long id) {
        return (PurchaseHistory) sessionFactory.getCurrentSession().get(PurchaseHistory.class, id);
    }

    @Override
    @Transactional
    public void saveOrUpdate(PurchaseHistory object) {
    	PurchaseHistory pH = (PurchaseHistory) sessionFactory.getCurrentSession().merge((PurchaseHistory) object);
        sessionFactory.getCurrentSession().save(pH);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void remove(Object object) {
        sessionFactory.getCurrentSession().delete((PurchaseHistory) object);
    }

}