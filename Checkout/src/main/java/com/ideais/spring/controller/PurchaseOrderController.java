package com.ideais.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ideais.spring.dao.domain.checkout.Customer;
import com.ideais.spring.dao.domain.checkout.Payment;
import com.ideais.spring.dao.domain.checkout.PurchaseOrder;
import com.ideais.spring.dao.domain.checkout.ShoppingCart;
import com.ideais.spring.service.GenericService;
import com.ideais.spring.service.PurchaseOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("PurchaseOrderController")
@RequestMapping("/purchaseorder")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService; 
    private static final String CART_KEY = "cart";
    private static final String CUSTOMER_KEY = "customer";
    
    @RequestMapping("/paymentDetails")
    public ModelAndView showPaymentDetails(){
        return new ModelAndView();
    }
    
    @RequestMapping(value = "/orderDetails/{id}",method = RequestMethod.GET)
    public ModelAndView showOrderDetails(@PathVariable Long id, HttpServletRequest request){
    	Long customerId =  ((Customer) request.getSession().getAttribute(CUSTOMER_KEY)).getId();
    	
    	PurchaseOrder purchaseOrder = purchaseOrderService.findById(id, customerId); //achar as ordens do determinado cliente
    	
        return new ModelAndView("purchaseorder/order", "order", purchaseOrder);
    }
    
//    @RequestMapping("/list")
//    public ModelAndView list(){
//        return new ModelAndView("purchaseorder/list", "list", purchaseOrderService.listObjects());
//    }

    //TODO: quando fechar a compra destruir cookie do carrinho e destruir carrinho e ordem de compra da sessão
    @RequestMapping(value = "/processorder",method = RequestMethod.GET)
    public ModelAndView processPurchaseOrder(){
    	//TODO: pega customer e shoppingcart da sessão
    	//TODO: pegar opção de pagamento e endereço de entrega (escolher qual dos endereços
    	//TODO: redirecionar para tela de finalização
    	
        return new ModelAndView("purchaseorder/new", "purchaseHistory", new PurchaseOrder());
    }

}