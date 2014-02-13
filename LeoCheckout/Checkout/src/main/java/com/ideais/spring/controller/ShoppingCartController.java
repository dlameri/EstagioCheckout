package com.ideais.spring.controller;

import java.io.IOException;
import java.rmi.RemoteException;
import com.ideais.spring.dao.domain.catalog.ShoppingCart;
import com.ideais.spring.service.FreightService;
import com.ideais.spring.service.ShoppingCartService;
import org.apache.axis.AxisFault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("ShoppingCartController")
@RequestMapping("/shoppingcart")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ShoppingCartController {
	
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private FreightService freightService;
    
    @RequestMapping("/items")
    public ModelAndView listShoppingCartSkus() throws Exception {
    	try {
    	ShoppingCart shoppingCart = shoppingCartService.getShoppingCart();
    	
    	freightService.calculeFreightPriceAndDate(shoppingCart);
    	
		return new ModelAndView("shoppingcart/list", "cart",  shoppingCart);
		
    	} catch (Exception e) {
    		return null; //tratar exceção
    	}
    }
    
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

}