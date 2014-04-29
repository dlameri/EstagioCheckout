package com.ideais.spring.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.ideais.spring.dao.interfaces.CustomerDaoBehavior;
import com.ideais.spring.domain.checkout.Customer;
import com.ideais.spring.service.interfaces.CustomerServiceBehavior;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("customerService")
public class CustomerService implements CustomerServiceBehavior {

	@Autowired
    private CustomerDaoBehavior customerDao;
    @Autowired
    private String cookiePath;
    @Autowired
    private String cookieDomain;
	private static final String CUSTOMER_KEY = "customer";

	@Override
    public Customer find(Long id) {
        return customerDao.findById(id);
    }

    @Override
    public void saveOrUpdate(Customer object) {
    	customerDao.saveOrUpdate(object);
    }

    @Override
    public void remove(Customer object) {
    	customerDao.remove(object);
    }

	@Override
	public void setCustomerInSession(Customer customer, HttpServletRequest request) {
		request.getSession().setAttribute(CUSTOMER_KEY, customer);
	}

	@Override
	public Customer customerLogin(String username, String password) {
		return customerDao.findByLoginOrEmail(username, password);
	}

	@Override
	public Cookie createCustomerCookie(Customer customer) {
    	Cookie customerCookie = new Cookie(CUSTOMER_KEY, customer.getUsername());
    	customerCookie.setMaxAge(60 * 60 * 2); 
    	customerCookie.setDomain(cookieDomain);
    	customerCookie.setPath(cookiePath);
    	
    	return customerCookie;
	}

	@Override
	public void setCustomerInSessionAfterUpdate(HttpServletRequest request, Long id) {
		request.getSession().setAttribute(CUSTOMER_KEY, customerDao.findById(id));
	}

	@Override
	public void removeAddress(Customer customer, Long id) {
		customerDao.removeAddress(customer, id);
	}

}