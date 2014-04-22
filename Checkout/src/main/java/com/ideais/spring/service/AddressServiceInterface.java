package com.ideais.spring.service;

import com.ideais.spring.dao.domain.checkout.Address;

public interface AddressServiceInterface {
	
	public Address find(Long id);
	
	public void save(Address address);
	
	public void remove(Address address);
}
