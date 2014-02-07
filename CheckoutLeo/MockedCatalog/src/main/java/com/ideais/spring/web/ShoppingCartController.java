package com.ideais.spring.web;

import com.ideais.spring.api.service.ShoppingCartServiceApi;
import com.ideais.spring.api.service.model.json.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("ShoppingCartController")
@RequestMapping("/shoppingcart")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ShoppingCartController {

    @Autowired
    private ShoppingCartServiceApi shoppingCartServiceApi;
    
    @RequestMapping("/api/sku")
    @ResponseBody
    public ShoppingCart list(){
        return shoppingCartServiceApi.getShoppingCart();
    }

    public void setShoppingCartServiceApi(ShoppingCartServiceApi shoppingCartServiceApi) {
        this.shoppingCartServiceApi = shoppingCartServiceApi;
    }

}