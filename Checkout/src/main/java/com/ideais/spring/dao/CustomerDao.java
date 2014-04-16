package com.ideais.spring.dao;

import com.ideais.spring.dao.domain.checkout.Customer;
import com.ideais.spring.dao.interfaces.CustomerDaoInterface;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("customerDao")
public class CustomerDao implements CustomerDaoInterface {

	@Autowired
    private SessionFactory sessionFactory;

    @Override
	@Transactional(readOnly = true)
    public Customer findById(Long id) {
        return (Customer) sessionFactory.getCurrentSession().get(Customer.class, id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Customer findByName(String name) {
        return (Customer) sessionFactory.getCurrentSession().get(Customer.class, name);
    }

    @Override
    @Transactional
    public void saveOrUpdate(Customer object) {
    	Customer c = (Customer) sessionFactory.getCurrentSession().merge((Customer) object);
        sessionFactory.getCurrentSession().save(c);
    }

    @Override
    @Transactional
    public void remove(Object object) {
        sessionFactory.getCurrentSession().delete((Customer) object);
    }
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}