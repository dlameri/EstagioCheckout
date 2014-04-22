package com.ideais.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideais.spring.dao.AddressDao;
import com.ideais.spring.dao.domain.checkout.Address;
import com.ideais.spring.dao.interfaces.AddressDaoInterface;

@Service("addressService")
public class AddressService implements AddressServiceInterface {
	
	@Autowired
	private AddressDaoInterface addressDao;
	
	@Override
	public Address find(Long id) {
		return addressDao.findById(id);
	}

	@Override
	public void save(Address address) {
		addressDao.saveOrUpdate(address);		
	}

	@Override
	public void remove(Address address) {
		addressDao.remove(address);		
	}
	 public void setAddressDaoInterface(AddressDao addressDao) {
	    	this.addressDao = addressDao;
	    }
}
