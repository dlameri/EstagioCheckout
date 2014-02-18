package com.ideais.spring.controller;

import javax.servlet.http.Cookie;

import java.io.IOException;
import java.rmi.RemoteException;

import org.springframework.web.bind.annotation.CookieValue;

import javax.servlet.http.HttpServletResponse;

import com.ideais.spring.dao.domain.checkout.ShoppingCart;
import com.ideais.spring.dao.domain.checkout.stock.Item;
import com.ideais.spring.service.FreightService;
import com.ideais.spring.service.ItemService;
import com.ideais.spring.service.ShoppingCartService;

import org.apache.axis.AxisFault;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("ShoppingCartController")
@RequestMapping("/shoppingcart")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ShoppingCartController {
	
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private FreightService freightService;
    @Autowired
    private ItemService itemService;
    
    @RequestMapping(value = "/codItem/{id}", method = RequestMethod.GET)
    public String edit(@CookieValue(value="ShoppingCartItems", required=false) String cartCookie, @PathVariable Long id, HttpServletResponse response) {
    	ShoppingCart shoppingCart;
    	try {
	    	Item item = itemService.getItem(id);
    	
	    	shoppingCart = getCart(cartCookie);
    		shoppingCart.addItem(item);	    		
	    	
	    	response.addCookie(createCartCookie(shoppingCart));
	    	
	    	return "redirect:../list";
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }

	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listCartItems(@CookieValue(value="ShoppingCartItems", required=false) String cartCookie) {
    	try {    		    	    		
	    	return new ModelAndView("shoppingcart/list", "cart", getCart(cartCookie));
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public ModelAndView removeItem(@PathVariable Long id ){
		try {
	    	Item item = itemService.getItem(id);
	        
	        return new ModelAndView("shoppingcart/remove", "item", item);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String removeItem(@CookieValue(value="ShoppingCartItems", required=false) String cartCookie, Item item, HttpServletResponse response){
    	ShoppingCart shoppingCart;
    	try {
    		shoppingCart = getCart(cartCookie);
    		shoppingCart.removeItem(item);
        
	    	response.addCookie(createCartCookie(shoppingCart));
    		
        	return "redirect:list";
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    	return null;
    }
    
    private ShoppingCart getCart(String cartCookie) throws IOException {
    	if (cartCookie == null || "".equals(cartCookie)) {
	    	return  new ShoppingCart();
    	}
    	
    	return shoppingCartService.getShoppingCart(cartCookie);
    }
    
    private Cookie createCartCookie(ShoppingCart shoppingCart) throws JsonGenerationException, JsonMappingException, IOException {
    	String jsonShoppingCart = shoppingCartService.getJsonShoppingCart(shoppingCart);
		
    	Cookie shoppingCartCookie = new Cookie("ShoppingCartItems", jsonShoppingCart);
    	shoppingCartCookie.setPath("/Checkout/");
    	
    	return shoppingCartCookie;
    }

}