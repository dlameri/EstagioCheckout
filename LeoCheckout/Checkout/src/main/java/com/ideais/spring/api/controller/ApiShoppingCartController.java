package com.ideais.spring.api.controller;

import java.io.IOException;
import org.codehaus.jackson.annotate.JsonRawValue;
import org.codehaus.jackson.annotate.JsonValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ideais.spring.api.service.ApiShoppingCartService;
import com.ideais.spring.dao.domain.checkout.ShoppingCart;

@Controller
@RequestMapping("/shoppingcart/api")
@Scope("request")
public class ApiShoppingCartController {
	
    @Autowired
    private ApiShoppingCartService apiShoppingCartService;
    
    @RequestMapping("/cart")
    public @JsonValue @JsonRawValue @ResponseBody String listCart(@CookieValue(value="CartItems", required=false) String cartCookie) {
		return cartCookie;
    }
	
}