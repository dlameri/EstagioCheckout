package com.ideais.spring.dao;

import com.ideais.spring.dao.domain.checkout.Customer;
import com.ideais.spring.dao.interfaces.CustomerDaoInterface;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
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
        return (Customer) session().get(Customer.class, id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Customer findByName(String name) {
        return (Customer) session().get(Customer.class, name);
    }

    @Override
    @Transactional
    public void saveOrUpdate(Customer object) {
    	Customer c = (Customer) session().merge((Customer) object);
        session().save(c);
    }

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

    @Override
    @Transactional
    public void remove(Object object) {
        session().delete((Customer) object);
    }
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Override
	@Transactional
	public Customer findByLogin(String username, String password) {
		Transaction tx =  session().beginTransaction();
		Customer customer = (Customer) session().createCriteria(Customer.class)
				.add(Restrictions.eq("username", username))
				.add(Restrictions.eq("password", password)).uniqueResult();
		tx.commit();
		return customer;
	}

}