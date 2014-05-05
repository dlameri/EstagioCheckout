package com.ideais.spring.controller.checkout;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ideais.spring.controller.catalog.BaseController;
import com.ideais.spring.domain.checkout.Address;
import com.ideais.spring.domain.checkout.CorreiosCodes;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("PurchaseOrderController")
@RequestMapping("/purchaseOrder")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class PurchaseOrderController extends BaseController{
	
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
    
    @RequestMapping(value = "/processOrder", method = RequestMethod.POST)
    public synchronized ModelAndView showPaymentDetails(@CookieValue(value=CART_COOKIE_KEY, required=false) String cartCookie,
    		HttpServletRequest request, 
    		HttpServletResponse response,
    		@RequestParam(value="paymentType", required=false) String paymentType, 
			@RequestParam(value="installment", required=false) String installmentsNumber) {
    	
    	//data da compra
    	Date date = new Date();
		PurchaseOrder order = (PurchaseOrder) request.getSession().getAttribute(ORDER_KEY);
		
    	try {
	    	ModelAndView view = getBaseView("purchaseorder/successorder", request);
	    	//monta a ordem de compra pra salvar
	    	if (order != null) {
	    		ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(cartCookie, request);
	    		
	    		order.setShoppingCart(shoppingCart);
		    	order.getShoppingCart().setCustomer(order.getCustomer());
		    	Payment payment = new Payment(paymentType, installmentsNumber, order.getTotalAmount());
		    	order.setPayment(payment);
		    	
		    	order.setPurchaseDate(date);
				
		    	//checa o estoque dos items mais uma vez
		    	List<Item> items = itemService.checkStock(order);
		    	
		    	if (items.size() == 0) {
		    		//fecha o pedido
			    	itemService.refreshItemQuantity(shoppingCartService.getJsonCart(order.getShoppingCart()));
			    	order.setStatusOfOrder(StatusOfOrder.FINISHED.getStatus());
			    	purchaseOrderService.save(order);
			    	
			    	mailService.sendMail(order.getCustomer().getEmail(), order);
			    	
			    	customerService.setCustomerInSessionAfterUpdate(request, order.getCustomer().getId());
			    	
			  	    shoppingCartService.removeCartFromSession(request.getSession(), response);
				    purchaseOrderService.removeOrderFromSession(request.getSession());
			    	
			    	view.addObject("order", order);
			    	return view;
		    	}
		    	
		    	request.getSession().setAttribute(CART_COOKIE_KEY, null);
		    	shoppingCartService.removeCartFromSession(request.getSession(), response);
		    	shoppingCart = shoppingCartService.getShoppingCart(cartCookie, request);
		    	shoppingCartService.setShoppingCartInSession(shoppingCart, request, response);
		    	
		    	view = getBaseView("purchaseorder/missingitems", request);
		    	
		    	view.addObject("order", order);
		    	view.addObject("missingItems", items);
	    	} else {
	    		view.addObject("errorMessage", "Logue antes de efetuar uma compra.");
	    	}
	    	
	        return view;
    	} catch (MailException e) {
    		if (order != null) {
    			//cancela a compra se o email falha
    			Long customerId = order.getCustomer().getId();
    			order = purchaseOrderService.findByPurchaseDate(date, customerId );
    			if (order != null) {
	    			order.setStatusOfOrder(StatusOfOrder.DECLINED.getStatus());
	    			purchaseOrderService.save(order);
    			}
    			ModelAndView view = getBaseView("purchaseorder/failorder", request);
    			view.addObject("errorMessage", "Sua compra foi cancelada, ocorreu um problema ao concluir sua compra e enviar o seu email de confirmação, verifique se o seu email está correto (email: " + 
    			order.getCustomer().getEmail() + ") e tente novamente.");
    			
    			return view;
    		}

    		ModelAndView view = getBaseView("purchaseorder/failorder", request);
			view.addObject("errorMessage", "Sua compra foi cancelada, ocorreu um problema ao concluir sua compra, tente novamente.");
			
			return view;
    	} catch (JsonGenerationException e) {

    		ModelAndView view = getBaseView("purchaseorder/failorder", request);
			view.addObject("errorMessage", "Sua compra foi cancelada, ocorreu um problema ao concluir sua compra, tente novamente.");
			
			return view;
		} catch (JsonMappingException e) {

    		ModelAndView view = getBaseView("purchaseorder/failorder", request);
			view.addObject("errorMessage", "Sua compra foi cancelada, ocorreu um problema ao concluir sua compra, tente novamente.");
			
			return view;
		} catch (IOException e) {

    		ModelAndView view = getBaseView("purchaseorder/failorder", request);
			view.addObject("errorMessage", "Sua compra foi cancelada, ocorreu um problema ao concluir sua compra, tente novamente.");
			
			return view;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MissingQuantityStockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
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
	    		if (shoppingCart.getQuantityOfItems() > 0) {
	    			if ( order.getShippingAddress() == null) {
	    				order.setShippingAddress(customer.getMainAddress());
	    			}
	    			
					FreightDetails  freightDetails = freightService.calculateFreightDetails(shoppingCart, order.getShippingAddress().getFormattedZipCode());
		
					freightService.setFreightInSession(freightDetails, shoppingCart, request);
		    		freightService.setFreightDeliverInPurchaseOrder(request, order);
	    		} else {
	    			view.addObject("errorMessage", "O carrinho está vazio, insira um item para continuar com a compra.");
	    		}
	    		purchaseOrderService.setPurchaseOrderInSession(request, order);
	    		
	    		order.checkAddress(customer);
	    		view.addObject("order", order);
	    	}
	    		    	
	        return getMessageForOrder(view, customer,  shoppingCart);
		} catch (NumberFormatException e) {
			view.addObject("errorMessage", "Dado de input inválido.");
			return view;
		} catch (IOException e) {
			return buildPaymentPageIfCorreiosDies(cartCookie, request);		
		} catch (MissingQuantityStockException e) {
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

	private ModelAndView buildPaymentPageIfCorreiosDies(String cartCookie, HttpServletRequest request) {
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
	    		if (shoppingCart.getQuantityOfItems() > 0) {
					FreightDetails freightDetails = new FreightDetails();
					freightDetails.setDeliveryDays(CorreiosCodes.PAC.getDefaultDays());
					freightDetails.setFreightValue(CorreiosCodes.PAC.getDefaultFreight());
					
					freightService.setFreightInSession(freightDetails, shoppingCart, request);
		    		freightService.setFreightDeliverInPurchaseOrder(request, order);
	    		} else {
	    			view.addObject("errorMessage", "O carrinho está vazio, insira um item para continuar com a compra.");
	    		}
	    		purchaseOrderService.setPurchaseOrderInSession(request, order);
	    		
	    		order.checkAddress(customer);
	    		view.addObject("order", order);
	    	}
	    		    	
	        return getMessageForOrder(view, customer,  shoppingCart);
		} catch (NumberFormatException e) {
			view.addObject("errorMessage", "Dado de input inválido.");
			return view;
		} catch (IOException e) {
			return buildPaymentPageIfCorreiosDies(cartCookie, request);		
		} catch (MissingQuantityStockException e) {
			return view;
		} catch (JSONException e) {
			view.addObject("errorMessage", "Erro, tente novamente!");
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