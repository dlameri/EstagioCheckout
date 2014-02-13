package com.ideais.spring.api.controller;

import org.codehaus.jackson.annotate.JsonRawValue;
import org.codehaus.jackson.annotate.JsonValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ideais.spring.api.service.ApiShoppingCartService;

@Controller
@RequestMapping("/shoppingcart/api")
@Scope("request")
public class ApiShoppingCartController {
	
    @Autowired
    private ApiShoppingCartService apiShoppingCartService;
   
    @RequestMapping("/sku")
    public @JsonValue @JsonRawValue@ResponseBody String listSkus(){
        return apiShoppingCartService.getShoppingCartJson();
    }
    
    @RequestMapping("/stockitems")
    public @JsonValue @JsonRawValue@ResponseBody String listCartItems(){
        return apiShoppingCartService.getStockItemsJson();
    }
	
}