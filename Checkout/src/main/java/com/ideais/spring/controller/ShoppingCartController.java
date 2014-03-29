package com.ideais.spring.controller;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.rmi.RemoteException;
import org.springframework.web.bind.annotation.CookieValue;
import javax.servlet.http.HttpServletResponse;
import com.ideais.spring.dao.domain.checkout.FreightDetails;
import com.ideais.spring.dao.domain.checkout.ShoppingCart;
import com.ideais.spring.service.FreightService;
import com.ideais.spring.service.ShoppingCartService;
import com.ideais.spring.util.DigitsValidator;
import org.apache.axis.AxisFault;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("ShoppingCartController")
@RequestMapping("/shoppingcart")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class ShoppingCartController {
	
	@Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private FreightService freightService;
    private static final String CART_COOKIE_KEY = "CartItems";
	    
    @RequestMapping(value = "/codItem/{id}", method = RequestMethod.GET)
    public String addItemToCart(@CookieValue(value=CART_COOKIE_KEY, required=false) String cartCookie, 
    		           @PathVariable Long id, 
    		           HttpServletResponse response,
    		           HttpServletRequest request) {
    	
    	ShoppingCart shoppingCart;
    	try {    		
	    	
	    	shoppingCart = shoppingCartService.getShoppingCart(cartCookie, request);
	    	shoppingCartService.addItemToShoppingCart(id, shoppingCart);
	    	
    		freightService.recalculateFreight(shoppingCart, request);
    		
	        shoppingCartService.setShoppingCartInSession(shoppingCart, request);
	    	response.addCookie(shoppingCartService.createCartCookie(shoppingCart));
	    	
	    	return "redirect:../list";
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }

	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listCartItems(@CookieValue(value=CART_COOKIE_KEY, required=false) String cartCookie, HttpServletRequest request) {
    	try {    		    	    		
    		ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(cartCookie, request);
    		FreightDetails freightDetails = freightService.getFreightDetails(request);
    		
    		if (freightDetails == null) {
    			freightService.setFreightInSession(new FreightDetails(), shoppingCart, request);
    		}
    	    		
	    	return new ModelAndView("shoppingcart/list", "cart", shoppingCart);
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String removeItemFromCart(@CookieValue(value=CART_COOKIE_KEY, required=false) String cartCookie, 
    						 @PathVariable Long id, 
    						 HttpServletResponse response,
    						 HttpServletRequest request) {
    	
    	ShoppingCart shoppingCart;
    	try {	    	
    		shoppingCart = shoppingCartService.getShoppingCart(cartCookie, request);   		
    		shoppingCartService.removeItemToShoppingCart(id, shoppingCart);
    		
    		freightService.recalculateFreight(shoppingCart, request);
	    	
	        shoppingCartService.setShoppingCartInSession(shoppingCart, request);
    		response.addCookie(shoppingCartService.createCartCookie(shoppingCart));
	        
	    	return "redirect:../list";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@RequestMapping(value = "/emptyCart", method = RequestMethod.POST)
    public String emptyCart(ShoppingCart shoppingCart, HttpServletResponse response, HttpServletRequest request) {
    	try {
    		shoppingCart.emptyShoppingCart();
    		freightService.recalculateFreight(shoppingCart, request);
    		
	        shoppingCartService.setShoppingCartInSession(shoppingCart, request);
	    	response.addCookie(shoppingCartService.createCartCookie(shoppingCart));
    		
        	return "redirect:list";
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    	return null;
    }
    
    @RequestMapping(value = "/editQuantity/{id}", method = RequestMethod.POST)
    public String editItemQuantity(@CookieValue(value=CART_COOKIE_KEY, required=false) String cartCookie,
    				   @PathVariable Long id, 
    				   @RequestParam(value="quantity", required=false) String quantity, 
    				   HttpServletResponse response,
    				   HttpServletRequest request) {
    	
    	ShoppingCart shoppingCart;
    	try {    		    
    		shoppingCart = shoppingCartService.getShoppingCart(cartCookie, request);
    		
    		if (DigitsValidator.validate(quantity)) {
    			shoppingCart.editQuantity(id, Integer.parseInt(quantity));	    		
    		
	    		freightService.recalculateFreight(shoppingCart, request);
	    		
		        shoppingCartService.setShoppingCartInSession(shoppingCart, request);
		    	response.addCookie(shoppingCartService.createCartCookie(shoppingCart));
    		}
    		
	    	return "redirect:../list";
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }

    @RequestMapping(value = "/calculateFreight", method = RequestMethod.POST)
    public String calculateFreight(@CookieValue(value="CartItems", required=false) String cartCookie,
    				   @RequestParam(value="postalCodeID1", required=false) String postalCodeID1, 
    				   @RequestParam(value="postalCodeID2", required=false) String postalCodeID2,
    				   HttpServletResponse response,
    				   HttpServletRequest request) {
    	
    	ShoppingCart shoppingCart;
    	try {    		    
    		shoppingCart = shoppingCartService.getShoppingCart(cartCookie, request);
    		
    		if (DigitsValidator.validate(postalCodeID1) && DigitsValidator.validate(postalCodeID2)) {
    			FreightDetails freightDetails = freightService.calculateFreightDetails(shoppingCart, postalCodeID1 + postalCodeID2);   
    			freightService.setFreightInSession(freightDetails, shoppingCart, request);

    	        shoppingCartService.setShoppingCartInSession(shoppingCart, request);
    	    	response.addCookie(shoppingCartService.createCartCookie(shoppingCart));
    		}
   	    	
	    	return "redirect:list";
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }

	@RequestMapping(value = "/proccessShoppingCart", method = RequestMethod.GET)
    public String proccessShoppingCart(@CookieValue(value=CART_COOKIE_KEY, required=false) String cartCookie, 
    		           HttpServletRequest request) {
    	
    	ShoppingCart shoppingCart;
    	try {    		
	    	shoppingCart = shoppingCartService.getShoppingCart(cartCookie, request);	    			
	        shoppingCartService.setShoppingCartInSession(shoppingCart, request);
	    	
	    	return "redirect:../customer/list";
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }

}