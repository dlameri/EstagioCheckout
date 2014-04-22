package com.ideais.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ideais.spring.dao.domain.checkout.Address;
import com.ideais.spring.dao.domain.checkout.Customer;
import com.ideais.spring.dao.domain.checkout.RegisterWrapper;
import com.ideais.spring.service.AddressServiceInterface;
import com.ideais.spring.service.CustomerService;
import com.ideais.spring.service.interfaces.CustomerServiceInterface;

@Controller("CustomerController")
@RequestMapping("/customer")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class CustomerController {
	
	//TODO: criar list de addresses
	//TODO: implementar login sem facebook e cadastro com e sem facebook

    @Autowired
    private CustomerServiceInterface customerService;
    
//    @Autowired
//    private AddressServiceInterface addressService;
    
    @RequestMapping(value = "/new",method = RequestMethod.GET)
    public ModelAndView newCustomer(){
    	ModelAndView view = new ModelAndView("customer/new");
    	RegisterWrapper rw = new RegisterWrapper();
        rw.setAddress(new Address());
        rw.setCustomer(new Customer());   	
        	
    	view.addObject("register", rw);
    	
    	//view.addObject("address", new Address());
        return view;
    }

    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public String newCustomer(RegisterWrapper rw){
        Address address = rw.getAddress();
        Customer customer = rw.getCustomer();
    	address.setCustomer(customer);
        customer.setMainAddress(address);
        customerService.save(customer);
      
        //addressService.save(address);                
    	
        return "redirect:";
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Long id){
        Customer customer = customerService.find(id);
        return new ModelAndView("customer/edit", "customer", customer);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public String edit(Customer customer) {
        customerService.save(customer);
        return "redirect:list";
    }

    public void setCustomerService(CustomerService customerService) {
    	this.customerService = customerService;
    }
}