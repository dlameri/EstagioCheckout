package com.ideais.spring.dao.interfaces;

import com.ideais.spring.domain.Customer;

public interface CustomerDaoBehavior {
	
    public Customer findById(Long id);
    
    public Customer findByName(String name);
    
    public Customer findByLogin(String username, String password);
   
    public void saveOrUpdate(Customer object);

    public void remove(Object object);

}