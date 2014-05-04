package com.ideais.spring.controller.checkout;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ideais.spring.controller.catalog.BaseController;
import com.ideais.spring.domain.checkout.Address;
import com.ideais.spring.domain.checkout.Customer;
import com.ideais.spring.domain.checkout.PurchaseOrder;
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
    
    @RequestMapping(value = "/new/{status}", method = RequestMethod.GET)
    public ModelAndView newCustomerError(@PathVariable String status, HttpServletRequest request){
    	ModelAndView view = getBaseView("customer/new", request);
    	RegisterWrapper rw = new RegisterWrapper();
        rw.setAddress(new Address());
        rw.setCustomer(new Customer());   	
        
        if (status != null && "error".equals(status)) {
			view.addObject("errorMessage", "Email já cadastrado.");
		} 
        	
    	view.addObject("register", rw);
    	
        return view;
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newCustomer(RegisterWrapper rw){
        Address address = rw.getAddress();
        Customer customer = rw.getCustomer();
    	address.setCustomer(customer);
        customer.setMainAddress(address);
        
		List<Customer> existingCustomers = (List<Customer>) customerService.findByEmail(customer.getEmail());
        
        if (existingCustomers == null || existingCustomers.size() == 0) {
        	customerService.saveOrUpdate(customer);
            return "redirect:http://ideaiselectronics.com:9082/Checkout/customer/authenticate/loginForm/successRegister";
        }
        
        return "redirect:http://ideaiselectronics.com:9082/Checkout/customer/new/error";
    }
    
    @RequestMapping(value = "/updateCustomer",method = RequestMethod.POST)
    public String updateCustomer(HttpServletRequest request, Customer updatedCustomer){
    	try {
    		Customer customer = (Customer) request.getSession().getAttribute(CUSTOMER_KEY);   
	    	
	    	if (customer != null) {
	    		customer.updateCustomer(updatedCustomer);
	    		customerService.saveOrUpdate(customer);   
	    		customerService.setCustomerInSessionAfterUpdate(request, customer.getId());
	    		
		        return "redirect:edit/success";
	    	}
	    	
	        return "redirect:edit/errorSession";
    	} catch (Exception e) {
    		e.printStackTrace();
	        return "redirect:edit/error";
    	}
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public ModelAndView edit(HttpServletRequest request){
    	ModelAndView view = getBaseView("customer/edit", request);
    	Customer customer = (Customer) request.getSession().getAttribute(CUSTOMER_KEY);   
    	
    	if (customer != null) {
    		view.addObject("customer", customer);
            return view;
    	}
    	
        return view;
    }
    
    @RequestMapping(value = "/edit/{status}",method = RequestMethod.GET)
    public ModelAndView editMessage(@PathVariable String status, HttpServletRequest request){
    	ModelAndView view = getBaseView("customer/edit", request);
    	Customer customer = (Customer) request.getSession().getAttribute(CUSTOMER_KEY);   
		
    	if (status != null && "error".equals(status)) {
			view.addObject("errorMessage", "Erro ao editar dados.");
		} else if (status != null && "success".equals(status)) {
			view.addObject("successMessage", "Dados editados com sucesso.");
		} else if (status != null && "errorSession".equals(status)) {
			view.addObject("successMessage", "Você precisa estar logado antes de editar informações.");
		}
    	
    	if (customer != null) {
    		view.addObject("customer", customer);
            return view;
    	}
    	
        return view;
    }
    
    @RequestMapping(value = "/customerDetails",method = RequestMethod.GET)
    public ModelAndView accountDetails(HttpServletRequest request) {
    	ModelAndView view = getBaseView("customer/customerdetails", request);
    	Customer customer = (Customer) request.getSession().getAttribute(CUSTOMER_KEY);   
    	List<PurchaseOrder> orders = customer.getPurchaseOrders();
    	if (customer != null) {
    		view.addObject("customer", customer);
    		view.addObject("orders", orders);
            return view;
    	}
    	
        return view;
    }
    
}