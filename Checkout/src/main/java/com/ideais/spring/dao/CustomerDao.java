package com.ideais.spring.dao;

import com.ideais.spring.dao.domain.checkout.Customer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component("customerDao")
public class CustomerDao implements GenericDao<Customer> {

	@Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
	@Override
    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Customer").list();
    }

    @Override
    @Transactional(readOnly = true)
    public Customer findById(Long id) {
        return (Customer) sessionFactory.getCurrentSession().get(Customer.class, id);
    }
    
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

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void remove(Object object) {
        sessionFactory.getCurrentSession().delete((Customer) object);
    }

}