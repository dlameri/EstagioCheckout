package com.ideais.spring.service.interfaces;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ideais.spring.domain.checkout.Customer;

public interface CustomerServiceBehavior {
	
	public Customer find(Long id);

    public void saveOrUpdate(Customer object);
    
    public Customer customerLogin(String username, String password);
    
    public String findPassword(String username, String email);
    
    public void setCustomerInSession(Customer object, HttpServletRequest request);
    
    public Cookie createCustomerCookie(Customer customer);

    public void remove(Customer customer);
    
    public void removeAddress(Customer customer, Long id);

	public void setCustomerInSessionAfterUpdate(HttpServletRequest request, Long id);
	
	public void removeCustomerCookie(HttpServletResponse response);

	public List<Customer> findByEmail(String email);
	
} 