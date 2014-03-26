package com.ideais.spring.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.CookieValue;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import com.ideais.spring.api.service.model.json.Cart;
import com.ideais.spring.api.service.model.json.CartItem;
import com.ideais.spring.dao.ItemJsonDao;
import com.ideais.spring.dao.domain.checkout.FreightDetails;
import com.ideais.spring.dao.domain.checkout.ShoppingCart;
import com.ideais.spring.dao.domain.checkout.ShoppingCartLine;
import com.ideais.spring.dao.domain.checkout.stock.Item;
import com.ideais.spring.service.FreightService;
import com.ideais.spring.service.ItemService;
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
	
    private static final String EMPTY_STRING = "";
	@Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private FreightService freightService;
    @Autowired
    private String cookiePath;
    @Autowired
    private ItemService itemService;
	private final Integer SUCCESS_RESPONSE_CODE = 200;
	private final Integer ERROR_RESPONSE_CODE = 500; //mandar Bad Request
	    
    @RequestMapping(value = "/codItem/{id}", method = RequestMethod.GET)
    public String edit(@CookieValue(value="CartItems", required=false) String cartCookie, 
    		           @PathVariable Long id, 
    		           HttpServletResponse response,
    		           HttpServletRequest request) {
    	
    	ShoppingCart shoppingCart;
    	try {    		
	    	Item item = itemService.getItem(id);
	    	
	    	shoppingCart = getShoppingCart(cartCookie, request);
    		shoppingCart.addItem(item);	   
    		
    		request.getSession().setAttribute("cart", shoppingCart);
	    	response.addCookie(createCartCookie(shoppingCart));
	    	
	    	return "redirect:../list";
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }

	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listCartItems(@CookieValue(value="CartItems", required=false) String cartCookie, HttpServletRequest request) {
    	try {    		    	    		
    		ShoppingCart shoppingCart = getShoppingCart(cartCookie, request);
    		FreightDetails freightDetails = (FreightDetails) request.getSession().getAttribute("freightDetails");
    		
    		if (freightDetails == null) {
    			request.getSession().setAttribute("freightDetails", new FreightDetails());
    		}
    	    		
	    	return new ModelAndView("shoppingcart/list", "cart", shoppingCart);
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String removeItem(@CookieValue(value="CartItems", required=false) String cartCookie, 
    						 @PathVariable Long id, 
    						 HttpServletResponse response,
    						 HttpServletRequest request) {
    	
    	ShoppingCart shoppingCart;
    	try {
	    	Item item = itemService.getItem(id);
	    	
    		shoppingCart = getShoppingCart(cartCookie, request);
    		shoppingCart.removeItem(item);	
    		
    		recalculateFreight(shoppingCart, request);
	    	
    		response.addCookie(createCartCookie(shoppingCart));
	        request.getSession().setAttribute("cart", shoppingCart);
	        
	    	return "redirect:../list";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
    
    private void recalculateFreight(ShoppingCart shoppingCart, HttpServletRequest request) {
		try {
	    	FreightDetails freightDetails = (FreightDetails) request.getSession().getAttribute("freightDetails");
			
			if (freightDetails != null) {
				freightDetails = updateFreightDetails(shoppingCart, freightDetails);
				setFreightInSession(freightDetails, shoppingCart, request);
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private FreightDetails updateFreightDetails(ShoppingCart shoppingCart, FreightDetails freightDetails) throws Exception {
		if (shoppingCart.getShoppingCartLines().size() <= 0) {
			shoppingCart.setFreight(BigDecimal.ZERO);
			freightDetails.setFreightValue(shoppingCart.getFreight());
		} else {
			freightDetails = freightService.getFreightDetails(shoppingCart, freightDetails.getDestinationZipCode());
		}
		
		return freightDetails;
	}

	@RequestMapping(value = "/emptyCart", method = RequestMethod.POST)
    public String emptyCart(ShoppingCart shoppingCart, HttpServletResponse response, HttpServletRequest request) {
    	try {
    		shoppingCart.emptyShoppingCart();
    		recalculateFreight(shoppingCart, request);
    		
    		request.getSession().setAttribute("cart", shoppingCart);
	    	response.addCookie(createCartCookie(shoppingCart));
    		
        	return "redirect:list";
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    	return null;
    }
    
    @RequestMapping(value = "/editQuantity/{id}", method = RequestMethod.POST)
    public String edit(@CookieValue(value="CartItems", required=false) String cartCookie,
    				   @PathVariable Long id, 
    				   @RequestParam(value="quantity", required=false) String quantity, 
    				   HttpServletResponse response,
    				   HttpServletRequest request) {
    	
    	ShoppingCart shoppingCart;
    	try {    		    
    		shoppingCart = getShoppingCart(cartCookie, request);
    		
    		if (DigitsValidator.validate(quantity)) {
    			shoppingCart.editQuantity(id, Integer.parseInt(quantity));	    		
    		}
    		
    		recalculateFreight(shoppingCart, request);
    		
    		request.getSession().setAttribute("cart", shoppingCart);
	    	response.addCookie(createCartCookie(shoppingCart));
    		
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
    		shoppingCart = getShoppingCart(cartCookie, request);
    		
    		if (DigitsValidator.validate(postalCodeID1) && DigitsValidator.validate(postalCodeID2)) {
    			FreightDetails freightDetails = freightService.getFreightDetails(shoppingCart, postalCodeID1 + postalCodeID2);   
    			setFreightInSession(freightDetails, shoppingCart, request);
    		}
    		
	    	response.addCookie(createCartCookie(shoppingCart));
	    	request.getSession().setAttribute("cart", shoppingCart);
	   	    	
	    	return "redirect:list";
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    private void setFreightInSession(FreightDetails freightDetails, ShoppingCart shoppingCart, HttpServletRequest request) {
		shoppingCart.setFreight(freightDetails.getFreightValue());
		request.getSession().setAttribute("freightDetails", freightDetails);
	}

	@RequestMapping(value = "/proccessShoppingCart", method = RequestMethod.GET)
    public String proccessShoppingCart(@CookieValue(value="CartItems", required=false) String cartCookie, 
    		           HttpServletRequest request) {
    	
    	ShoppingCart shoppingCart;
    	try {    		
	    	shoppingCart = getShoppingCart(cartCookie, request);	    			
	        request.getSession().setAttribute("cart", shoppingCart);
	    	
	    	return "redirect:../customer/list";
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    @RequestMapping(value = "/addItemToCart", method = RequestMethod.POST, consumes="application/json")
	public Response addItemToCart(@CookieValue(value="CartItems", required=false) String cartCookie, 
	           Long id, 
	           HttpServletResponse response,
	           HttpServletRequest request) {
    	
    	ShoppingCart shoppingCart;
    	try {    		
	    	Item item = itemService.getItem(id);
	    	
	    	shoppingCart = getShoppingCart(cartCookie, request);
    		shoppingCart.addItem(item);	    		
    		
	    	response.addCookie(createCartCookie(shoppingCart));
	        request.getSession().setAttribute("cart", shoppingCart);
	    	
	    	return Response.status(SUCCESS_RESPONSE_CODE).entity(id.toString()).build();
    	} catch (Exception e) {
    		e.printStackTrace();
    		
    		return Response.status(ERROR_RESPONSE_CODE).entity(id.toString()).build();
    	}
	}

	private ShoppingCart getShoppingCart(String cartCookie, HttpServletRequest request) throws Exception {
    	if (request.getSession().getAttribute("cart") == null) {		
			return getShoppingCartFromCookie(cartCookie);
    	}

    	return (ShoppingCart) request.getSession().getAttribute("cart");
    }

	private ShoppingCart getShoppingCartFromCookie(String cartCookie) throws Exception, IOException {
		if (cartCookie == null || EMPTY_STRING.equals(cartCookie)) {
			return new ShoppingCart();
		} else {
			return createShoppingCartFromCart(shoppingCartService.getCartFromJson(cartCookie));
		}
	}
    
    private Cookie createCartCookie(ShoppingCart shoppingCart) throws JsonGenerationException, JsonMappingException, IOException {
    	String jsonCart = shoppingCartService.getJsonCart(shoppingCart);

    	Cookie cartCookie = new Cookie("CartItems", jsonCart);
    	cartCookie.setMaxAge(60 * 60 * 1000);
    	cartCookie.setPath(cookiePath);
    	
    	return cartCookie;
    }
      
    private ShoppingCart createShoppingCartFromCart(Cart cart) throws Exception {
    	ShoppingCart shoppingCart = new ShoppingCart();
    	List<ShoppingCartLine> shoppingCartLines = new ArrayList<ShoppingCartLine>();
    	
    	for (int i = 0; i < cart.getCartItems().size(); i++) {
    		shoppingCartLines.add(createShoppingCartLine(cart.getCartItems().get(i)));
    	}
    	
    	shoppingCart.setShoppingCartLines(shoppingCartLines);
    	
    	return shoppingCart;
    }
    
    private ShoppingCartLine createShoppingCartLine(CartItem cartItem) throws Exception {
		ShoppingCartLine shoppingCartLine = new ShoppingCartLine(itemService.getItem(cartItem.getCartItemId()));
		shoppingCartLine.setQuantity(cartItem.getQuantity());
		
		return shoppingCartLine;
    }

}