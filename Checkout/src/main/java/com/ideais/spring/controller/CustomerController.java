package com.ideais.spring.controller;

import javax.servlet.http.HttpServletRequest;
import com.ideais.spring.dao.domain.checkout.Customer;
import com.ideais.spring.dao.domain.checkout.ShoppingCart;
import com.ideais.spring.service.CustomerService;
import com.ideais.spring.service.GenericService;
import com.ideais.spring.service.interfaces.CustomerServiceInterface;
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
public class CustomerController {
	
	//TODO: criar list de addresses
	//TODO: implementar login sem facebook e cadastro com e sem facebook

    @Autowired
    private CustomerServiceInterface customerService;

    @RequestMapping(value = "/new",method = RequestMethod.GET)
    public ModelAndView newCustomer(){
        return new ModelAndView("customer/new", "customer", new Customer());
    }

    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public String newCustomer(Customer customer){
        customerService.save(customer);
        return "redirect:list";
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