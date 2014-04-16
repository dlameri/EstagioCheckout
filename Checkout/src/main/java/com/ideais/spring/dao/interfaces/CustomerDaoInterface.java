package com.ideais.spring.dao.interfaces;

import com.ideais.spring.dao.domain.checkout.Customer;

public interface CustomerDaoInterface {
	
    public Customer findById(Long id);
    
    public Customer findByName(String name);
   
    public void saveOrUpdate(Customer object);

    public void remove(Object object);

}