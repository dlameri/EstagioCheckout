package com.ideais.spring.service.interfaces;

import com.ideais.spring.dao.domain.checkout.Customer;

public interface CustomerServiceInterface {
	
	public Customer find(Long id);

    public void save(Customer object);

    public void remove(Customer object);
	
} 