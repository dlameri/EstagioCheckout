package com.ideais.spring.service;

import com.ideais.spring.dao.CustomerDao;
import com.ideais.spring.dao.domain.checkout.Customer;
import com.ideais.spring.dao.interfaces.CustomerDaoInterface;
import com.ideais.spring.service.interfaces.CustomerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("customerService")
public class CustomerService implements CustomerServiceInterface {

	@Autowired
    private CustomerDaoInterface customerDao;

    public Customer find(Long id) {
        return customerDao.findById(id);
    }

    public void save(Customer object) {
    	customerDao.saveOrUpdate(object);
    }

    public void remove(Customer object) {
    	customerDao.remove(object);
    }
    
    public void setCustomerDaoInterface(CustomerDao customerDao) {
    	this.customerDao = customerDao;
    }

}