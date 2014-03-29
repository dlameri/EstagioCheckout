package com.ideais.spring.api.controller;

import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ideais.spring.dao.domain.checkout.ShoppingCart;
import com.ideais.spring.service.ShoppingCartService;

@Controller
@RequestMapping("/api/shoppingcart")
@Scope("request")
public class ApiShoppingCartController {
    
	@Autowired
    private ShoppingCartService shoppingCartService;
	private final Integer SUCCESS_RESPONSE_CODE = 200;
	private final Integer ERROR_RESPONSE_CODE = 500; //mandar Bad Request
    
    @RequestMapping(value = "/cartItems", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody String listCart(@CookieValue(value="CartItems", required=false) String cartCookie) {
		return cartCookie;
    }
    
    @RequestMapping(value = "/addItemToCart", method = RequestMethod.POST, consumes="application/json")
	public Response addItemToCart(@CookieValue(value="CartItems", required=false) String cartCookie, 
	           Long id, 
	           HttpServletResponse response,
	           HttpServletRequest request) {
    	
    	ShoppingCart shoppingCart;
    	try {    			    	
	    	shoppingCart = shoppingCartService.getShoppingCart(cartCookie, request);
    		shoppingCartService.addItemToShoppingCart(id, shoppingCart);    		
    		
	    	response.addCookie(shoppingCartService.createCartCookie(shoppingCart));
	        request.getSession().setAttribute("cart", shoppingCart);
	    	
	    	return Response.status(SUCCESS_RESPONSE_CODE).entity(id.toString()).build();
    	} catch (Exception e) {
    		e.printStackTrace();
    		return Response.status(ERROR_RESPONSE_CODE).entity(id.toString()).build();
    	}
	}
	
}