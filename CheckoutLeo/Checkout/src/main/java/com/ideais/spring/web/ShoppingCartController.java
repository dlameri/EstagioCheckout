package com.ideais.spring.web;

import com.ideais.spring.service.ShoppingCartService;
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
    
    @RequestMapping("/items")
    public ModelAndView listSkus() {
		return new ModelAndView("shoppingcart/list", "list",  shoppingCartService.getShoppingCart().getSkus());
    }
    
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

}