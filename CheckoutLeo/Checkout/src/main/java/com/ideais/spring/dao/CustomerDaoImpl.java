package com.ideais.spring.dao;

import com.ideais.spring.model.checkout.Customer;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("customerDaoImpl")
public class CustomerDaoImpl implements GenericDao {

	@Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
	@Override
    @Transactional(readOnly = true)
    public List<Object> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Pizza").list();
    }

    @Override
    @Transactional(readOnly = true)
    public Object findById(Long id) {
        return (Customer) sessionFactory.getCurrentSession().get(Customer.class, id);
    }

    @Override
    @Transactional
    public void saveOrUpdate(Object object) {
    	Customer c = (Customer) sessionFactory.getCurrentSession().merge((Customer) object);
        sessionFactory.getCurrentSession().save(c);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void remove(Object object) {
        sessionFactory.getCurrentSession().delete((Customer) object);
    }

}