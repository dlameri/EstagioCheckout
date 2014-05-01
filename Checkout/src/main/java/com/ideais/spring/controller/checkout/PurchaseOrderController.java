package com.ideais.spring.controller.checkout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ideais.spring.controller.catalog.BaseController;
import com.ideais.spring.domain.checkout.Address;
import com.ideais.spring.domain.checkout.Customer;
import com.ideais.spring.domain.checkout.PurchaseOrder;
import com.ideais.spring.domain.checkout.ShoppingCart;
import com.ideais.spring.service.MailService;
import com.ideais.spring.service.interfaces.CustomerServiceBehavior;
import com.ideais.spring.service.interfaces.FreightServiceBehavior;
import com.ideais.spring.service.interfaces.PurchaseOrderServiceBehavior;
import com.ideais.spring.service.interfaces.ShoppingCartServiceBehavior;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("PurchaseOrderController")
@RequestMapping("/purchaseOrder")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class PurchaseOrderController extends BaseController{

    //TODO: quando fechar a compra destruir cookie do carrinho e destruir carrinho e ordem de compra da sessão
	//TODO: mandar email com os dados da compra
	
    @Autowired
    private PurchaseOrderServiceBehavior purchaseOrderService; 
    @Autowired
    private ShoppingCartServiceBehavior shoppingCartService; 
    @Autowired
    private CustomerServiceBehavior customerService; 
    @Autowired
    private FreightServiceBehavior freightService;
    @Autowired
    private MailService mailService;
    private static final String CART_COOKIE_KEY = "CartItems";
    private static final String CUSTOMER_KEY = "customer";
    private static final String ORDER_KEY = "order";
	private static final Logger logger = Logger.getLogger("purchaseOrderController");
    
    @RequestMapping("/processOrder")
    public synchronized ModelAndView showPaymentDetails() {
    	mailService.sendMail();
        return new ModelAndView();
    }
    
    @RequestMapping(value = "/orderDetails/{id}", method = RequestMethod.GET)
    public ModelAndView showOrderDetails(@PathVariable Long id, HttpServletRequest request) {
    	ModelAndView view = getBaseView("purchaseorder/order", request);
    	
    	Customer customer = (Customer) request.getSession().getAttribute(CUSTOMER_KEY);
    	if (customer != null) {
	    	Long customerId =  customer.getId();
	    	PurchaseOrder purchaseOrder = purchaseOrderService.findById(id, customerId); 
	    	
	    	view.addObject("order", purchaseOrder);
	    	view.addObject("customer", customer);
	    	
	    	logger.debug("Ordem de compra id: " + id + ", do cliente de id: " + customerId + "buscada e devolvida pra tela.");    	
	        return view;
    	}
    	
    	view.addObject("errorMessage", "Detalhes da ordem decompra não disponíveis.");
    	
    	logger.warn("Sessão expirada e cliente nulo para retorno de detalhes da ordem de compra de id: "+ id + ".");
    	return view;
    }
    
    @RequestMapping("/orders")
    public ModelAndView list(HttpServletRequest request) {
    	ModelAndView view = getBaseView("purchaseorder/list", request);

    	Customer customer = (Customer) request.getSession().getAttribute(CUSTOMER_KEY);
    	if (customer != null) {
	    	Long customerId =  customer.getId();
	    	
	    	logger.debug("Ordens de compra do cliente de id: " + customerId + " devolvidos pra tela.");   	
	        return new ModelAndView("purchaseorder/list", "list", purchaseOrderService.findAllByCustomerId(customerId));
    	}
    	
    	view.addObject("errorMessage", "Ordens de compra indisponíveis.");
    	
    	logger.warn("Sessão expirada e cliente nulo para retorno de ordens de compra.");
    	return view;	
    }
    
    
    
    @RequestMapping(value = "/paymentDetails",method = RequestMethod.GET)
    public ModelAndView processPurchaseOrder(@CookieValue(value=CART_COOKIE_KEY, required=false) String cartCookie,
    		HttpServletRequest request) {
    	
    	ModelAndView view = getBaseView("purchaseorder/paymentdetails", request);
    	
    	ShoppingCart shoppingCart;
		try {
			shoppingCart = shoppingCartService.getShoppingCart(cartCookie, request);
	    	Customer customer = (Customer) request.getSession().getAttribute(CUSTOMER_KEY);
	    		    	
	    	if (customer != null && shoppingCart != null) {
	    		
	    		PurchaseOrder order = (PurchaseOrder) request.getSession().getAttribute(ORDER_KEY);
	    		//TODO: recalcular frete quando se muda o endereço
	    		
	    		if (order == null) {	    			
		    		order = new PurchaseOrder(customer, shoppingCart);
		    		freightService.setFreightDeliverInPurchaseOrder(request, order);
		    		purchaseOrderService.setPurchaseOrderInSession(request, order);
		    		
		    		logger.debug("Customer de id: " + customer.getId() + "carrinho da sessão setados em uma ordem de compra.");
	    		} 

	    		view.addObject("order", order);
	    	}
	    	
	    	logger.warn("Customer ou shoppingCart não estão na sessão");
	    	
	        return getMessageForOrder(view, customer, shoppingCart);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return view;
    }

	private ModelAndView getMessageForOrder(ModelAndView view, Customer customer, ShoppingCart shoppingCart) {
		if (customer == null && shoppingCart != null) {
			view.addObject("customerError", "sessão expirou! Logue antes de comprar!");
			
			logger.warn("Usuário inválido");
		} else if (customer != null && shoppingCart == null) {
			view.addObject("shoppingCartError", "carrinho de compras vazio!");
			
			logger.warn("Sessão do carrinho espirou.");
		} else {
			view.addObject("customerError", "sessão expirou! Logue antes de comprar!");
			view.addObject("shoppingCartError", "carrinho de compras vazio!");
			
			logger.warn("Usuário inválido");
			logger.warn("Sessão do carrinho espirou.");
		}
		
		return view;
	}

}