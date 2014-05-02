package com.ideais.spring.controller.checkout;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ideais.spring.controller.catalog.BaseController;
import com.ideais.spring.domain.checkout.Address;
import com.ideais.spring.domain.checkout.Customer;
import com.ideais.spring.domain.checkout.PurchaseOrder;
import com.ideais.spring.service.interfaces.CustomerServiceBehavior;
import com.ideais.spring.service.interfaces.PurchaseOrderServiceBehavior;

@Controller("AddressController")
@RequestMapping("/customer/address")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class AddressController extends BaseController {

    @Autowired
    private PurchaseOrderServiceBehavior purchaseOrderService; 
    @Autowired
    private CustomerServiceBehavior customerService; 
    private static final String CUSTOMER_KEY = "customer";
    private static final String ORDER_KEY = "order";
	
	@RequestMapping(value = "/newAddress", method = RequestMethod.GET)
    public ModelAndView newAddress(HttpServletRequest request) {
    	ModelAndView view = getBaseView("customer/newshippingaddress", request);
    	
    	Customer customer = (Customer) request.getSession().getAttribute(CUSTOMER_KEY);
    	PurchaseOrder order = (PurchaseOrder) request.getSession().getAttribute(ORDER_KEY);
	    	
    	if (customer != null && order != null) {
    		view.addObject("notSelectedShippingAddresses", customer.getNotSelectedShippingAddresses(order.getShippingAddress().getId()));
    		view.addObject("address", new Address());	
    		return view;
    	}

    	view.addObject("customerError", "sessão expirou! Logue antes de criar novo endereço!");

    	return view;	
    }
    
    @RequestMapping(value = "/newShippingAddress", method = RequestMethod.POST)
    public String newShippingAddress(Address address, HttpServletRequest request) {    	
    	Customer customer = (Customer) request.getSession().getAttribute(CUSTOMER_KEY);
    	
    	if (customer != null) {
    		customer.addDeliveryAddress(address);
    		customerService.saveOrUpdate(customer);   
    		customerService.setCustomerInSessionAfterUpdate(request, customer.getId());
    	}
    	
    	return "redirect:newAddress";	
    }
    
	@RequestMapping(value = "/editAddressForm/{id}", method = RequestMethod.GET)
    public ModelAndView editAddress(@PathVariable Long id, HttpServletRequest request) {
    	ModelAndView view = getBaseView("customer/editaddress", request);
    	
    	Customer customer = (Customer) request.getSession().getAttribute(CUSTOMER_KEY);    	    	

    	if (customer != null) {
    		view.addObject("address", customer.getAddressById(id));	
    		return view;
    	}
    	
    	return view;	
    }
	
	@RequestMapping(value = "/editAddressForm/{id}/{status}", method = RequestMethod.GET)
    public ModelAndView editAddress(@PathVariable Long id, @PathVariable String status, HttpServletRequest request) {
    	ModelAndView view = getBaseView("customer/editaddress", request);
    	
    	Customer customer = (Customer) request.getSession().getAttribute(CUSTOMER_KEY);    	    	
    	
		if (status != null && "error".equals(status)) {
			view.addObject("errorMessage", "Erro ao editar dados.");
		} else if (status != null && "success".equals(status)) {
			view.addObject("successMessage", "Dados editados com sucesso.");
		} else if (status != null && "errorSession".equals(status)) {
			view.addObject("successMessage", "Você precisa estar logado antes de editar informações.");
		}
    	
    	if (customer != null) {
    		view.addObject("address", customer.getAddressById(id));	
    		return view;
    	}
    	
    	return view;	
    }
    
    @RequestMapping(value = "/editAddress", method = RequestMethod.POST)
    public String editAddress(Address address, HttpServletRequest request) {
    	try {
    		Customer customer = (Customer) request.getSession().getAttribute(CUSTOMER_KEY);    	    	

        	if (customer != null) {
	    		address.setCustomer(customer);
	    		customer.updateAddress(address);
	    		
		    	customerService.saveOrUpdate(customer);    	
	    		customerService.setCustomerInSessionAfterUpdate(request, customer.getId());
		    	
		    	return "redirect:editAddressForm/"+ address.getId() +"/success";	
    		}
        	
	    	return "redirect:editAddressForm/"+ address.getId() +"/errorSession";	
    	} catch (Exception e) {
	    	return "redirect:editAddressForm/"+ address.getId() +"/error";	
    	}
    }
    
    @RequestMapping(value = "/setMainAddress/{id}", method = RequestMethod.GET)
    public String setMainAddress(@PathVariable Long id, HttpServletRequest request) {
    	try {
    		Customer customer = (Customer) request.getSession().getAttribute(CUSTOMER_KEY);    	    	

        	if (customer != null) {
	    		customer.setMainAddress(id);
	    		
		    	customerService.saveOrUpdate(customer);    	
	    		customerService.setCustomerInSessionAfterUpdate(request, customer.getId());
	    	
		    	return "redirect:../../edit/";	
    		}
        	
	    	return "redirect:../../edit/";	
    	} catch (Exception e) {
	    	return "redirect:../../edit/";	
    	}
    }
    
    @RequestMapping(value = "/removeAddress/{id}", method = RequestMethod.GET)
    public String removeOrderAddress(@PathVariable Long id, HttpServletRequest request) {
    	String uri = "";
    	try {
    		Customer customer = (Customer) request.getSession().getAttribute(CUSTOMER_KEY);    	    	
    		uri = request.getHeader("referer");
    		    		
        	if (customer != null) {
        		customerService.removeAddress(customer, id);
	    		customerService.setCustomerInSessionAfterUpdate(request, customer.getId());
	    	
		    	return "redirect:" + uri;	
    		}
        	
	    	return "redirect:" + uri;
    	} catch (Exception e) {
    		e.printStackTrace();

	    	return "redirect:" + uri;	
    	}
    }
    
    @RequestMapping(value = "/setShippingAddress/{id}", method = RequestMethod.GET)
    public String setShippingAddress(@PathVariable Long id, HttpServletRequest request) {
    	try {
    		PurchaseOrder order = (PurchaseOrder) request.getSession().getAttribute(ORDER_KEY);
    		Customer customer = (Customer) request.getSession().getAttribute(CUSTOMER_KEY);
    		order.setCustomer(customer);

        	if (order != null) {        		
	    		order.setShippingAddress(order.getCustomer().getAddressById(id));
	    		purchaseOrderService.setPurchaseOrderInSession(request, order);
		    		    		
		    	return "redirect:../../../purchaseOrder/paymentDetails";	
    		}
        	
	    	return "redirect:../../../purchaseOrder/paymentDetails";	
    	} catch (Exception e) {
	    	return "redirect:../../../purchaseOrder/paymentDetails";	
    	}
    }
	
}