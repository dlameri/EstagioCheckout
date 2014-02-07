package com.ideais.spring.web;

import com.ideais.spring.dao.domain.checkout.Customer;
import com.ideais.spring.service.GenericService;
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
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CustomerController {

    @Autowired
    private GenericService<Customer> customerService;
    
    @RequestMapping("/list")
    public ModelAndView list(){
        return new ModelAndView("customer/list", "list", customerService.listObjects());
    }

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

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Long id){
        Customer customer = customerService.find(id);
        return new ModelAndView("customer/delete", "custmer", customer);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public String delete(Customer customer){
        customerService.remove(customer);
        return "redirect:list";
    }

    public void setCustomerService(GenericService<Customer> customerService) {
        this.customerService = customerService;
    }

}