package com.ideais.spring.service.interfaces;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.ideais.spring.domain.checkout.Address;
import com.ideais.spring.domain.checkout.Customer;

public interface CustomerServiceBehavior {
	
	public Customer find(Long id);

    public void saveOrUpdate(Customer object);
    
    public Customer customerLogin(String username, String password);
    
    public void setCustomerInSession(Customer object, HttpServletRequest request);
    
    public Cookie createCustomerCookie(Customer customer);

    public void remove(Customer customer);
    
    public void removeAddress(Customer customer, Long id);

	public void setCustomerInSessionAfterUpdate(HttpServletRequest request, Long id);
	
} 