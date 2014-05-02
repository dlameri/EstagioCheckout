package com.ideais.spring.controller.checkout;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ideais.spring.controller.catalog.BaseController;
import com.ideais.spring.domain.checkout.Address;
import com.ideais.spring.domain.checkout.Customer;
import com.ideais.spring.domain.checkout.FreightDetails;
import com.ideais.spring.domain.checkout.Item;
import com.ideais.spring.domain.checkout.Payment;
import com.ideais.spring.domain.checkout.PurchaseOrder;
import com.ideais.spring.domain.checkout.ShoppingCart;
import com.ideais.spring.domain.checkout.StatusOfOrder;
import com.ideais.spring.exceptions.FreightException;
import com.ideais.spring.exceptions.FreightZipCodeException;
import com.ideais.spring.exceptions.ItemPackageDimensionException;
import com.ideais.spring.exceptions.ItemPackageVolumeException;
import com.ideais.spring.exceptions.ItemPackageWeightException;
import com.ideais.spring.exceptions.MissingQuantityStockException;
import com.ideais.spring.service.ItemService;
import com.ideais.spring.service.MailService;
import com.ideais.spring.service.interfaces.CustomerServiceBehavior;
import com.ideais.spring.service.interfaces.FreightServiceBehavior;
import com.ideais.spring.service.interfaces.ItemServiceBehavior;
import com.ideais.spring.service.interfaces.PurchaseOrderServiceBehavior;
import com.ideais.spring.service.interfaces.ShoppingCartServiceBehavior;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.jdom2.JDOMException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailException;
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
    private ItemServiceBehavior itemService;
    @Autowired
    private MailService mailService;
    private static final String CART_COOKIE_KEY = "CartItems";
    private static final String CUSTOMER_KEY = "customer";
    private static final String ORDER_KEY = "order";
	private static final Logger logger = Logger.getLogger("purchaseOrderController");
    
    @RequestMapping("/processOrder")
    public synchronized ModelAndView showPaymentDetails(HttpServletRequest request) {
    	try {
	    	ModelAndView view = getBaseView("purchaseorder/successorder", request);
	    	
			PurchaseOrder order = (PurchaseOrder) request.getSession().getAttribute(ORDER_KEY);
	    	order.getShoppingCart().setCustomer(order.getCustomer());
	    	order.setPayment(new Payment());
			
	    	List<Item> items = itemService.checkStock(order);
	    	
	    	if (items.size() == 0) {
		    	mailService.sendMail();
		    	itemService.refreshItemQuantity(shoppingCartService.getJsonCart(order.getShoppingCart()));
		    	order.setStatusOfOrder(StatusOfOrder.FINISHED.getStatus());
		    	purchaseOrderService.save(order);
		    	
		    	view.addObject("order", order);
		    	return view;
	    	}
	    	
	    	view = getBaseView("purchaseorder/failorder", request);
	    	
	    	view.addObject("order", order);
	    	view.addObject("missingItems", items);
	    	
	        return view;
    	} catch (MailException e) {
    		return null;
    	} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return null;
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
	    		
	    		if (order == null) {	    			
		    		order = new PurchaseOrder(customer, shoppingCart);
		    		logger.debug("Customer de id: " + customer.getId() + "carrinho da sessão setados em uma ordem de compra.");
	    		} 
	    		
				FreightDetails freightDetails = null;
				freightDetails = freightService.calculateFreightDetails(shoppingCart, order.getShippingAddress().getFormattedZipCode());
	
				freightService.setFreightInSession(freightDetails, shoppingCart, request);
	    		freightService.setFreightDeliverInPurchaseOrder(request, order);
	    		
	    		purchaseOrderService.setPurchaseOrderInSession(request, order);
	    		
	    		view.addObject("order", order);
	    	}
	    	
	    	logger.warn("Customer ou shoppingCart não estão na sessão");
	    	
	        return getMessageForOrder(view, customer, shoppingCart);
		} catch (NumberFormatException e) {
			view.addObject("errorMessage", "Dado de input inválido.");
			return view;
		} catch (IOException e) {
			view.addObject("errorMessage", "Erro, tente novamente!");
			return view;		
		} catch (MissingQuantityStockException e) {
			view.addObject("errorMessage", "Quantidade indisponível no estoque!");
			return view;
		} catch (JSONException e) {
			view.addObject("errorMessage", "Erro, tente novamente!");
			return view;
		} catch (FreightException e) {
			view.addObject("errorMessage", "Erro ao calcular o frete, tente novamente.");
			return view;
		} catch (FreightZipCodeException e) {
			view.addObject("errorMessage", "Cep inválido!");
			return view;
		} catch (JDOMException e) {
			view.addObject("errorMessage", "Erro, tente novamente!");
			return view;
		} catch (ItemPackageWeightException e) {
			view.addObject("errorMessage", "Peso do pedido excedido!");
			return view;
		} catch (ItemPackageVolumeException e) {
			view.addObject("Volume do pedido excedido!");
			return view;
		} catch (ItemPackageDimensionException e) {
			view.addObject("errorMessage", "Dimensão do pedido excedido!");
			return view;
		}
    	
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