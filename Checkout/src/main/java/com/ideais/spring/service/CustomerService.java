package com.ideais.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideais.spring.dao.interfaces.CustomerDaoBehavior;
import com.ideais.spring.domain.Customer;
import com.ideais.spring.service.interfaces.CustomerServiceBehavior;

@Service("customerService")
public class CustomerService implements CustomerServiceBehavior {

	@Autowired
    private CustomerDaoBehavior customerDao;

    public Customer find(Long id) {
        return customerDao.findById(id);
    }

    public void save(Customer object) {
    	customerDao.saveOrUpdate(object);
    }

    public void remove(Customer object) {
    	customerDao.remove(object);
    }

	@Override
	public Customer findByLogin(String userName, String password) {
		return customerDao.findByLogin(userName, password);
	}

}