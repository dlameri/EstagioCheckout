package com.ideais.spring.dao.interfaces;

import com.ideais.spring.dao.domain.checkout.Address;

public interface AddressDaoInterface {
	
	public Address findById(Long id);
	
	public void saveOrUpdate(Object address);

    public void remove(Object address);
}
