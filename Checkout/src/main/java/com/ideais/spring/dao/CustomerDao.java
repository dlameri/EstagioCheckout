package com.ideais.spring.dao;

import java.util.List;

import com.ideais.spring.dao.interfaces.CustomerDaoBehavior;
import com.ideais.spring.domain.checkout.Address;
import com.ideais.spring.domain.checkout.Customer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("customerDao")
public class CustomerDao implements CustomerDaoBehavior {

	@Autowired
    private SessionFactory sessionFactory;

    @Override
	@Transactional(readOnly = true)
    public Customer findById(Long id) {
        return (Customer) session().get(Customer.class, id);
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

	@Override
	@Transactional
	public Customer findByEmail(String email, String password) {
		Customer customer = (Customer) session().createCriteria(Customer.class)
				.add(Restrictions.eq("email", email))
				.add(Restrictions.eq("password", password)).uniqueResult();
		
		return customer;
	}
	
	@Override
	@Transactional
	public String findPasswordByLoginAndEmail(String username, String email) {
		Customer customer = (Customer) session().createCriteria(Customer.class)
				.add(Restrictions.eq("email", email))
				.add(Restrictions.eq("username", username)).uniqueResult();
		
		if (customer != null) {
			return customer.getPassword();
		}
		
		return null;
	}

	@Override
	@Transactional
	public void removeAddress(Customer customer, Long id) {
		for (int i = 0; i < customer.getDeliveryAddresses().size(); i++) {
			if (customer.getDeliveryAddresses().get(i).getId() == id) {
				Address addressToBeRemoved = customer.getDeliveryAddresses().get(i);
				
				addressToBeRemoved.setCustomer(null);
				customer.getDeliveryAddresses().remove(i);
				
				session().delete((Address) addressToBeRemoved);
			}
		}
		
		saveOrUpdate(customer);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Customer> findByEmail(String email) {
		List<Customer> customers = (List<Customer>) session().createCriteria(Customer.class)
		.add(Restrictions.ilike("email", email)).list();
				
		return customers;
	}

}