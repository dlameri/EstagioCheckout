package com.ideais.spring.service;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ideais.spring.api.domain.json.Cart;
import com.ideais.spring.api.domain.json.CartItem;
import com.ideais.spring.dao.ShoppingCartJsonDao;
import com.ideais.spring.dao.domain.checkout.FreightDetails;
import com.ideais.spring.dao.domain.checkout.ShoppingCart;
import com.ideais.spring.dao.domain.checkout.ShoppingCartLine;
import com.ideais.spring.dao.domain.checkout.stock.Item;

@Component("shoppingCartService")
public class ShoppingCartService {
	
    private static final String EMPTY_STRING = "";
    @Autowired
    private ItemService itemService;
    @Autowired
    private ShoppingCartJsonDao shoppingCartJsonDao;
    @Autowired
    private String cookiePath;
    private static final String CART_KEY = "cart";
    private static final String CART_COOKIE_KEY = "CartItems";
	
	public Cart getCartFromJson(String jsonCart) throws IOException {		
		return shoppingCartJsonDao.getCartFromJson(jsonCart);
	}
	
	public String getJsonCart(ShoppingCart shoppingCart) throws JsonGenerationException, JsonMappingException, IOException {
		return shoppingCartJsonDao.getShoppingCartToCartJson(shoppingCart);
	}
	
	public ShoppingCart getShoppingCart(String cartCookie, HttpServletRequest request) throws Exception {
    	if (request.getSession().getAttribute(CART_KEY) == null) {		
			return getShoppingCartFromCookie(cartCookie);
    	}

    	return (ShoppingCart) request.getSession().getAttribute(CART_KEY);
    }

	private ShoppingCart getShoppingCartFromCookie(String cartCookie) throws Exception, IOException {
		if (cartCookie == null || EMPTY_STRING.equals(cartCookie)) {
			return new ShoppingCart();
		} else {
			return createShoppingCartFromCart(getCartFromJson(cartCookie));
		}
	}
    
    public Cookie createCartCookie(ShoppingCart shoppingCart) throws JsonGenerationException, JsonMappingException, IOException {
    	String jsonCart = getJsonCart(shoppingCart);

    	Cookie cartCookie = new Cookie(CART_COOKIE_KEY, jsonCart);
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
    
    public void addItemToShoppingCart(Long id, ShoppingCart shoppingCart) throws Exception {
    	Item item = itemService.getItem(id);	
		shoppingCart.addItem(item);	   
    }
    
    public void removeItemToShoppingCart(Long id, ShoppingCart shoppingCart) throws Exception {
    	Item item = itemService.getItem(id);	
		shoppingCart.removeItem(item);	
    }
    
    public void setShoppingCartInSession(ShoppingCart shoppingCart, HttpServletRequest request) {
        request.getSession().setAttribute(CART_KEY, shoppingCart);
	}
 
}