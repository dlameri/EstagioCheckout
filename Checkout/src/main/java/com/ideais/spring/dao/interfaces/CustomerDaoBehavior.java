package com.ideais.spring.dao.interfaces;

import com.ideais.spring.domain.checkout.Customer;

public interface CustomerDaoBehavior {
	
    public Customer findById(Long id);
    
    public Customer findByName(String name);
    
    public Customer findByLoginOrEmail(String username, String password);
   
    public void saveOrUpdate(Customer object);

    public void remove(Object object);

	public void removeAddress(Customer customer, Long id);

}