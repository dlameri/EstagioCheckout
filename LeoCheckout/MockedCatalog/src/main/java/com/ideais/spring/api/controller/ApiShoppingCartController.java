package com.ideais.spring.api.controller;

import com.ideais.spring.api.service.ApiShoppingCartService;
import com.ideais.spring.api.service.model.json.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("apiShoppingCartController")
@RequestMapping("/shoppingcart/api")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ApiShoppingCartController {

    @Autowired
    private ApiShoppingCartService apiShoppingCartService;
    
    @RequestMapping("/sku")
    @ResponseBody
    public ShoppingCart list(){
        return apiShoppingCartService.getShoppingCart();
    }

    public void setShoppingCartServiceApi(ApiShoppingCartService shoppingCartServiceApi) {
        this.apiShoppingCartService = shoppingCartServiceApi;
    }

}