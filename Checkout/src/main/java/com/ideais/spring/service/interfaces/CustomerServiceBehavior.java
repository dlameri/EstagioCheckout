package com.ideais.spring.service.interfaces;

import com.ideais.spring.domain.Customer;

public interface CustomerServiceBehavior {
	
	public Customer find(Long id);

    public void save(Customer object);

    public void remove(Customer object);
	
} 