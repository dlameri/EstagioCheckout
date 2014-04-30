package com.ideais.spring.controller.checkout;

import javax.servlet.http.HttpServletRequest;
import com.ideais.spring.controller.catalog.BaseController;
import com.ideais.spring.domain.checkout.Address;
import com.ideais.spring.domain.checkout.Customer;
import com.ideais.spring.domain.checkout.RegisterWrapper;
import com.ideais.spring.service.interfaces.CustomerServiceBehavior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("CustomerController")
@RequestMapping("/customer")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class CustomerController extends BaseController{
	
	//TODO: criar list de addresses
	//TODO: implementar login sem facebook e cadastro com e sem facebook

    @Autowired
    private CustomerServiceBehavior customerService;
    private static final String CUSTOMER_KEY = "customer";
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newCustomer(HttpServletRequest request){
    	ModelAndView view = getBaseView("customer/new", request);
    	RegisterWrapper rw = new RegisterWrapper();
        rw.setAddress(new Address());
        rw.setCustomer(new Customer());   	
        	
    	view.addObject("register", rw);
    	
        return view;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newCustomer(RegisterWrapper rw){
        Address address = rw.getAddress();
        Customer customer = rw.getCustomer();
    	address.setCustomer(customer);
        customer.setMainAddress(address);
        customerService.saveOrUpdate(customer);
          	
        return "redirect:";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public ModelAndView edit(HttpServletRequest request){
    	ModelAndView view = getBaseView("customer/edit", request);
    	Customer customer = (Customer) request.getSession().getAttribute(CUSTOMER_KEY);   
    	
    	if (customer != null) {
    		view.addObject("customer", customer);
            return view;
    	}
    	
    	view = getBaseView("customer/nossesion", request);
		view.addObject("Sua sessão expirou, faça o login para prosseguir.");
    	
        return view;
    }
    
    @RequestMapping(value = "/customerDetails",method = RequestMethod.GET)
    public ModelAndView accountDetails(HttpServletRequest request) {
    	ModelAndView view = getBaseView("customer/customerdetails", request);
    	Customer customer = (Customer) request.getSession().getAttribute(CUSTOMER_KEY);   
    	
    	if (customer != null) {
    		view.addObject("customer", customer);
            return view;
    	}
    	
    	view = getBaseView("customer/nossesion", request);
		view.addObject("Sua sessão expirou, faça o login para prosseguir.");
    	
        return view;

    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public String edit(Customer customer) {
        customerService.saveOrUpdate(customer);
        return "redirect:list";
    }
    
}