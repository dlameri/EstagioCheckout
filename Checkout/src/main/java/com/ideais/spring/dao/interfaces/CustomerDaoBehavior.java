package com.ideais.spring.dao.interfaces;

import java.util.List;

import com.ideais.spring.domain.checkout.Customer;

public interface CustomerDaoBehavior {
	
    public Customer findById(Long id);
        
    public Customer findByEmail(String email, String password);
   
    public void saveOrUpdate(Customer object);

    public void remove(Object object);

	public void removeAddress(Customer customer, Long id);

	public String findPasswordByLoginAndEmail(String username, String email);

	public List<Customer> findByEmail(String email);
	
}