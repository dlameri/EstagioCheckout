package com.ideais.spring.controller;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.CookieValue;
import javax.servlet.http.HttpServletResponse;
import com.ideais.spring.api.service.model.json.Cart;
import com.ideais.spring.api.service.model.json.CartItem;
import com.ideais.spring.dao.domain.checkout.ShoppingCart;
import com.ideais.spring.dao.domain.checkout.ShoppingCartLine;
import com.ideais.spring.dao.domain.checkout.stock.Item;
import com.ideais.spring.service.FreightService;
import com.ideais.spring.service.ItemService;
import com.ideais.spring.service.ShoppingCartService;
import com.ideais.spring.util.DigitsValidator;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    @Autowired
    private ItemService itemService;
    private final String EMPTY_STRING = "";    
    
    @RequestMapping(value = "/codItem/{id}", method = RequestMethod.GET)
    public String edit(@CookieValue(value="CartItems", required=false) String cartCookie, 
    		           @PathVariable Long id, 
    		           HttpServletResponse response) {
    	
    	ShoppingCart shoppingCart;
    	try {    		
	    	Item item = itemService.getItem(id);
	    	
	    	shoppingCart = getShoppingCart(cartCookie);
    		shoppingCart.addItem(item);	    		
    		
	    	response.addCookie(createCartCookie(shoppingCart));
	    	
	    	return "redirect:../list";
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }

	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listCartItems(@CookieValue(value="CartItems", required=false) String cartCookie,
    								  @CookieValue(value="CalculateFreight", required=false) String zipCodeFreightCookie) {
    	try {    		    	    		
    		ShoppingCart shoppingCart = getShoppingCart(cartCookie);
    		
    		if (checksIfCanCalculateFreight(zipCodeFreightCookie, shoppingCart.getShoppingCartLines())) {
    			freightService.calculeFreightPriceAndDate(shoppingCart, zipCodeFreightCookie);
    		}
    		
	    	return new ModelAndView("shoppingcart/list", "cart", shoppingCart);
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
	
	private boolean checksIfCanCalculateFreight(String zipCodeFreightCookie, List<ShoppingCartLine> shoppingCartLines) {
		return zipCodeFreightCookie != null && !EMPTY_STRING.equals(zipCodeFreightCookie) && shoppingCartLines.size() > 0;
	}
    
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String removeItem(@CookieValue(value="CartItems", required=false) String cartCookie, 
    						 @PathVariable Long id, 
    						 HttpServletResponse response) {
    	
    	ShoppingCart shoppingCart;
    	try {
	    	Item item = itemService.getItem(id);
	    	
    		shoppingCart = getShoppingCart(cartCookie);
    		shoppingCart.removeItem(item);
        
	    	response.addCookie(createCartCookie(shoppingCart));
	        
	    	return "redirect:../list";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
    
    @RequestMapping(value = "/emptycart", method = RequestMethod.POST)
    public String emptyCart(ShoppingCart shoppingCart, HttpServletResponse response) {
    	try {
    		shoppingCart.emptyShoppingCart();
	    	response.addCookie(createCartCookie(shoppingCart));
    		
        	return "redirect:list";
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    	return null;
    }
    
    @RequestMapping(value = "/editquantity/{id}",method = RequestMethod.POST)
    public String edit(@CookieValue(value="CartItems", required=false) String cartCookie,
    				   @PathVariable Long id, 
    				   @RequestParam(value="quantity", required=false) String quantity, 
    				   HttpServletResponse response) {
    	
    	ShoppingCart shoppingCart;
    	try {    		    
    		shoppingCart = getShoppingCart(cartCookie);
    		
    		if (DigitsValidator.validate(quantity)) {
    			shoppingCart.editQuantity(id, Integer.parseInt(quantity));	    		
    		}
    		
	    	response.addCookie(createCartCookie(shoppingCart));
    		
	    	return "redirect:../list";
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }

    @RequestMapping(value = "/calculatefreight",method = RequestMethod.POST)
    public String calculateFreight(@CookieValue(value="CartItems", required=false) String cartCookie,
    				   @RequestParam(value="postalCodeID1", required=false) String postalCodeID1, 
    				   @RequestParam(value="postalCodeID2", required=false) String postalCodeID2,
    				   HttpServletResponse response) {
    	
    	ShoppingCart shoppingCart;
    	try {    		    
    		shoppingCart = getShoppingCart(cartCookie);
    		
    		if (DigitsValidator.validate(postalCodeID1) && DigitsValidator.validate(postalCodeID2)) {
    			freightService.calculeFreightPriceAndDate(shoppingCart, postalCodeID1 + postalCodeID2);   		
    		}
    		    		
	    	response.addCookie(createCartCookie(shoppingCart));
    		response.addCookie(createFreightCookie(postalCodeID1 + postalCodeID2));
	    	
	    	return "redirect:list";
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }

	private ShoppingCart getShoppingCart(String cartCookie) throws Exception {
    	if (cartCookie == null || "".equals(cartCookie)) {
	    	return new ShoppingCart();
    	}

    	return createShoppingCartFromCart(shoppingCartService.getCartFromJson(cartCookie));
    }
	
    private Cookie createFreightCookie(String zipCode) {
    	Cookie freightCookie = new Cookie("CalculateFreight", zipCode);
    	freightCookie.setPath("/Checkout/");
    	
    	return freightCookie;
    }
    
    private Cookie createCartCookie(ShoppingCart shoppingCart) throws JsonGenerationException, JsonMappingException, IOException {
    	String jsonCart = shoppingCartService.getJsonCart(shoppingCart);

    	Cookie cartCookie = new Cookie("CartItems", jsonCart);
    	cartCookie.setPath("/Checkout/");
    	
    	return cartCookie;
    }
      
    //TODO: os dois métodos abaixo não deveriam estar aqui, implementar construtor para ambos
    //pegar todos os items de uma vez pelo ItemService pelo construtor de ShoppingCart que recebe List<Item>
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