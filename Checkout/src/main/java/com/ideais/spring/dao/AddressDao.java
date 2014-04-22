package com.ideais.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ideais.spring.dao.domain.checkout.Address;
import com.ideais.spring.dao.interfaces.AddressDaoInterface;


@Component("addressDao")
public class AddressDao implements AddressDaoInterface {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional(readOnly = true)
	public Address findById(Long id) {
		return (Address) session().get(Address.class, id);
	}

	@Override
	@Transactional
	public void saveOrUpdate(Object address) {
		Address a = (Address) session().merge((Address) address);
		session().save(a);
	}

	@Override
	@Transactional
	public void remove(Object address) {
		session().delete(address);		
	}
	
	 public void setSessionFactory(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }
	
	 public Session session() {
			return sessionFactory.getCurrentSession();
		}

}
