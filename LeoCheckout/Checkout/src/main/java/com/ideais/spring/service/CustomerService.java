package com.ideais.spring.service;

import com.ideais.spring.dao.GenericDao;
import com.ideais.spring.dao.domain.checkout.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("customerService")
public class CustomerService implements GenericService<Customer> {

	@Autowired
    private GenericDao<Customer> customerDao;

    @Override
    public List<Customer> listObjects() {
        return customerDao.findAll();
    }

    @Override
    public Customer find(Long id) {
        return customerDao.findById(id);
    }

    @Override
    public void save(Customer object) {
    	customerDao.saveOrUpdate(object);
    }

    public void setCustomerDao(GenericDao<Customer> customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public void remove(Customer object) {
    	customerDao.remove(object);
    }

}