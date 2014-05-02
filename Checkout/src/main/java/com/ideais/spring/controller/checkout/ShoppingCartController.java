package com.ideais.spring.controller.checkout;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import org.springframework.web.bind.annotation.CookieValue;

import javax.servlet.http.HttpServletResponse;

import com.ideais.spring.controller.catalog.BaseController;
import com.ideais.spring.controller.checkout.message.ErrorMessageFactory;
import com.ideais.spring.domain.checkout.FreightDetails;
import com.ideais.spring.domain.checkout.ShoppingCart;
import com.ideais.spring.exceptions.FreightException;
import com.ideais.spring.exceptions.FreightZipCodeException;
import com.ideais.spring.exceptions.ItemPackageDimensionException;
import com.ideais.spring.exceptions.ItemPackageVolumeException;
import com.ideais.spring.exceptions.ItemPackageWeightException;
import com.ideais.spring.exceptions.MissingQuantityStockException;
import com.ideais.spring.service.interfaces.FreightServiceBehavior;
import com.ideais.spring.service.interfaces.ShoppingCartServiceBehavior;
import com.ideais.spring.util.DigitsValidator;

import org.jdom2.JDOMException;
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
@RequestMapping("/shoppingCart")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class ShoppingCartController extends BaseController {
		
	@Autowired
    private ShoppingCartServiceBehavior shoppingCartService;
    @Autowired
    private FreightServiceBehavior freightService;
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
	    	response.addCookie(shoppingCartService.createCartTopCookie(shoppingCart));
	    	
	    	return "redirect:../";
		} catch (NumberFormatException e) {
			return "redirect:../errorNumber";
		} catch (IOException e) {
			return "redirect:../error";
		} catch (MissingQuantityStockException e) {
			return "redirect:../errorQuantity";
		} catch (JSONException e) {
			return "redirect:../error";
		} 	
    }

	@RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView listCartItems(@CookieValue(value=CART_COOKIE_KEY, required=false) String cartCookie, HttpServletRequest request) {
		ModelAndView view = getBaseView("shoppingcart/list", request);
		ShoppingCart shoppingCart;
		
		try {
			shoppingCart = shoppingCartService.getShoppingCart(cartCookie, request);
    		FreightDetails freightDetails = freightService.getFreightDetails(request);
    		
    		if (freightDetails == null) {
    			freightService.setFreightInSession(new FreightDetails(), shoppingCart, request);
    		}
    	    
    		shoppingCartService.setShoppingCartInSession(shoppingCart, request);
    		view.addObject("cart", shoppingCart);
    		
	    	return view;
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
		}			
    }
	
	@RequestMapping(value = "/{status}", method = RequestMethod.GET)
    public ModelAndView listCartItemsError(@CookieValue(value=CART_COOKIE_KEY, required=false) String cartCookie, 
    		@PathVariable String status, HttpServletRequest request) {
		ModelAndView view = getBaseView("shoppingcart/list", request);
		ShoppingCart shoppingCart;
		
		try {
			shoppingCart = shoppingCartService.getShoppingCart(cartCookie, request);
    		FreightDetails freightDetails = freightService.getFreightDetails(request);
    		
    		if (freightDetails == null) {
    			freightService.setFreightInSession(new FreightDetails(), shoppingCart, request);
    		}
    	    
    		shoppingCartService.setShoppingCartInSession(shoppingCart, request);
    		view.addObject("cart", shoppingCart);
    		view.addObject("errorMessage", getErrorMessage(status));
    		
	    	return view;
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
		}			
    }
    
    private Object getErrorMessage(String status) {
    	return ErrorMessageFactory.buildErrorMessage(status);
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
	    	response.addCookie(shoppingCartService.createCartTopCookie(shoppingCart));
	        
	    	return "redirect:../";
		} catch (NumberFormatException e) {
			return "redirect:../errorNumber";
		} catch (IOException e) {
			return "redirect:../error";
		} catch (MissingQuantityStockException e) {
			return "redirect:../errorQuantity";
		} catch (JSONException e) {
			return "redirect:../error";
		} 
	}

	@RequestMapping(value = "/emptyCart", method = RequestMethod.POST)
    public String emptyCart(ShoppingCart shoppingCart, HttpServletResponse response, HttpServletRequest request) {
    	try {
    		shoppingCart.emptyShoppingCart();
    		freightService.recalculateFreight(shoppingCart, request);
    		
	        shoppingCartService.setShoppingCartInSession(shoppingCart, request);
	    	response.addCookie(shoppingCartService.createCartCookie(shoppingCart));
	    	response.addCookie(shoppingCartService.createCartTopCookie(shoppingCart));
    		
        	return "redirect:";
    	} catch(IOException e) {
    		return "redirect:../error";
    	}
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
    		
    		if (DigitsValidator.validateQuantityInput(quantity)) {
    			shoppingCart.editQuantity(id, Integer.parseInt(quantity));	    		
    		
	    		freightService.recalculateFreight(shoppingCart, request);
	    		
		        shoppingCartService.setShoppingCartInSession(shoppingCart, request);
		    	response.addCookie(shoppingCartService.createCartCookie(shoppingCart));
		    	response.addCookie(shoppingCartService.createCartTopCookie(shoppingCart));
    		}
    		
	    	return "redirect:../";
		} catch (NumberFormatException e) {
			return "redirect:../errorNumber";
		} catch (IOException e) {
			return "redirect:../error";
		} catch (MissingQuantityStockException e) {
			return "redirect:../errorQuantity";
		} catch (JSONException e) {
			return "redirect:../error";
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
    		
    		if (DigitsValidator.validateZipCodeInput(postalCodeID1) && DigitsValidator.validateZipCodeInput(postalCodeID2)) {
    			FreightDetails freightDetails = null;
				freightDetails = freightService.calculateFreightDetails(shoppingCart, postalCodeID1 + postalCodeID2);

    			freightService.setFreightInSession(freightDetails, shoppingCart, request);

    	        shoppingCartService.setShoppingCartInSession(shoppingCart, request);
    	    	response.addCookie(shoppingCartService.createCartCookie(shoppingCart));
    	    	response.addCookie(shoppingCartService.createCartTopCookie(shoppingCart));
    		}
   	    	
	    	return "redirect:";
		} catch (NumberFormatException e) {
			return "redirect:/errorNumber";
		} catch (IOException e) {
			return "redirect:/error";
		} catch (MissingQuantityStockException e) {
			return "redirect:/errorQuantity";
		} catch (JSONException e) {
			return "redirect:/error";
		} catch (FreightException e) {
			return "redirect:/errorFreight";
		} catch (FreightZipCodeException e) {
			return "redirect:/errorCep";
		} catch (JDOMException e) {
			return "redirect:/error";
		} catch (ItemPackageWeightException e) {
			return "redirect:/errorWeight";
		} catch (ItemPackageVolumeException e) {
			return "redirect:/errorVolume";
		} catch (ItemPackageDimensionException e) {
			return "redirect:/errorDimension";
		} 
    }

	@RequestMapping(value = "/proccessShoppingCart", method = RequestMethod.GET)
    public String proccessShoppingCart(@CookieValue(value=CART_COOKIE_KEY, required=false) String cartCookie, 
    		           HttpServletRequest request) {
    	
    	ShoppingCart shoppingCart;

    	try {
			shoppingCart = shoppingCartService.getShoppingCart(cartCookie, request);    			
		    shoppingCartService.setShoppingCartInSession(shoppingCart, request);
			
			return "redirect:../purchaseOrder/paymentDetails";
		} catch (NumberFormatException e) {
			return "redirect:/errorNumber";
		} catch (IOException e) {
			return "redirect:/error";
		} catch (MissingQuantityStockException e) {
			return "redirect:/errorQuantity";
		} catch (JSONException e) {
			return "redirect:/error";
		}
    }

}