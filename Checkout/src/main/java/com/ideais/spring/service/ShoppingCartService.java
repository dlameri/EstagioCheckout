package com.ideais.spring.service;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideais.spring.dao.interfaces.ShoppingCartDaoBehavior;
import com.ideais.spring.domain.checkout.Item;
import com.ideais.spring.domain.checkout.ShoppingCart;
import com.ideais.spring.domain.checkout.ShoppingCartLine;
import com.ideais.spring.domain.checkout.json.Cart;
import com.ideais.spring.domain.checkout.json.CartItem;
import com.ideais.spring.exceptions.MissingQuantityStockException;
import com.ideais.spring.service.interfaces.ItemServiceBehavior;
import com.ideais.spring.service.interfaces.ShoppingCartServiceBehavior;
import com.ideais.spring.util.JsonUtil;

@Service("shoppingCartService")
public class ShoppingCartService implements ShoppingCartServiceBehavior {
		
    @Autowired
    private ItemServiceBehavior itemService;
    @Autowired
    private ShoppingCartDaoBehavior shoppingCartDao;
    @Autowired
    private String cookiePath;
    @Autowired
    private String cookieDomain;
    private static final String CART_KEY = "cart";
    private static final String CART_COOKIE_KEY = "CartItems";
    private static final String CART_TOP_COOKIE_KEY = "CartTop";
    private static final int EMPTY_CART = 0;
	private static final String EMPTY_STRING = "";
	
    @Override
	public Cart getCartFromJson(String jsonCart) throws IOException {		
		return shoppingCartDao.getCartFromJson(jsonCart);
	}
	
    @Override
	public String getJsonCart(ShoppingCart shoppingCart) throws JsonGenerationException, JsonMappingException, IOException {
		return shoppingCartDao.getShoppingCartToCartJson(shoppingCart);
	}
	
    @Override
	public ShoppingCart getShoppingCart(String cartCookie, HttpServletRequest request) throws IOException, NumberFormatException, MissingQuantityStockException, JSONException {
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute(CART_KEY);
		
    	if (shoppingCart == null) {
    		return getShoppingCartFromCookie(cartCookie);
    	}

    	return shoppingCart;
    }

	private ShoppingCart getShoppingCartFromCookie(String cartCookie) throws IOException, NumberFormatException, MissingQuantityStockException, JSONException {
		if (cartCookie == null || EMPTY_STRING.equals(cartCookie)) {
			return new ShoppingCart();
		} else {
			return createShoppingCartFromCart(getCartFromJson(cartCookie));
		}
	}
    
	@Override
    public Cookie createCartCookie(ShoppingCart shoppingCart) throws JsonGenerationException, JsonMappingException, IOException {
    	String jsonCart = getJsonCart(shoppingCart);

    	Cookie cartCookie = new Cookie(CART_COOKIE_KEY, jsonCart);
    	cartCookie.setMaxAge(60 * 60 * 1000);
    	cartCookie.setDomain(cookieDomain);
    	cartCookie.setPath(cookiePath);
    	
    	return cartCookie;
    }
    
	@Override
    public Cookie createCartTopCookie(ShoppingCart shoppingCart) throws JsonGenerationException, JsonMappingException, IOException {
    	String jsonCartTop = JsonUtil.writeObjectToJson(shoppingCart.getQuantityOfItems());
    	
    	Cookie cartTopCookie = new Cookie(CART_TOP_COOKIE_KEY, jsonCartTop);
    	cartTopCookie.setMaxAge(60 * 60 * 1000);
    	cartTopCookie.setDomain(cookieDomain);
    	cartTopCookie.setPath(cookiePath);
    	
    	return cartTopCookie;
    }
    
      
    private ShoppingCart createShoppingCartFromCart(Cart cart) throws NumberFormatException, MissingQuantityStockException, IOException, JSONException {
    	ShoppingCart shoppingCart = new ShoppingCart();
    	List<ShoppingCartLine> shoppingCartLines = new ArrayList<ShoppingCartLine>();
    	
    	for (int i = 0; i < cart.getCartItems().size(); i++) {
    		ShoppingCartLine shoppingCartLine = createShoppingCartLine(cart.getCartItems().get(i));
    		
    		if (shoppingCartLine != null) {
    			shoppingCartLines.add(shoppingCartLine);
    		}
    	}
    	
    	shoppingCart.setShoppingCartLines(shoppingCartLines);
    	
    	return shoppingCart;
    }
    
    private ShoppingCartLine createShoppingCartLine(CartItem cartItem) throws NumberFormatException, MissingQuantityStockException, IOException, JSONException {
    	Item item = itemService.getItem(cartItem.getCartItemId());
    	
    	if (item != null && item.getActive()) {
			ShoppingCartLine shoppingCartLine = new ShoppingCartLine(item);
			shoppingCartLine.setQuantity(cartItem.getQuantity());
			
			return shoppingCartLine;
    	}
    	
    	return null;
    }
    
    @Override
    public void addItemToShoppingCart(Long id, ShoppingCart shoppingCart) throws IOException, JSONException, MissingQuantityStockException {
    	if (!shoppingCart.hasItemWithId(id)) {    		
    		Item item = itemService.getItem(id);	
    		if (item != null && item.getActive()) {
    			shoppingCart.addItem(item);
    		}
    	}
    }
    
    @Override
    public void removeItemToShoppingCart(Long id, ShoppingCart shoppingCart) {
    	if (shoppingCart.hasItemWithId(id)) {
			shoppingCart.removeItemFromId(id);				
    	}
    }
    
    @Override
    public void setShoppingCartInSession(ShoppingCart shoppingCart, HttpServletRequest request) {
        request.getSession().setAttribute(CART_KEY, shoppingCart);
	}
    
	@Override
	public Integer cartQtd(HttpServletRequest request) throws HttpException, IOException {
		Cookie[] cookies = request.getCookies();
		return getCookieByName(cookies, CART_TOP_COOKIE_KEY);	
	}

	private Integer getCookieByName(Cookie[] cookies, String name) {
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(name)) {
					return ((Integer) JsonUtil.readJsonToObject(cookies[i].getValue(), Integer.class));
				}
			}
		}
		
		return EMPTY_CART;
	}

	@Override
	public void removeCartFromSession(HttpSession session, HttpServletResponse response) {
		session.setAttribute(CART_KEY, null);
		
    	Cookie cartTopCookie = new Cookie(CART_TOP_COOKIE_KEY, null);
    	cartTopCookie.setMaxAge(0);
    	cartTopCookie.setDomain(cookieDomain);
    	cartTopCookie.setPath(cookiePath);
    	
    	response.addCookie(cartTopCookie);
    	
    	Cookie cartCookie = new Cookie(CART_COOKIE_KEY, null);
    	cartCookie.setMaxAge(60 * 60 * 1000);
    	cartCookie.setDomain(cookieDomain);
    	cartCookie.setPath(cookiePath);
    	
    	response.addCookie(cartCookie);

	}
 
}