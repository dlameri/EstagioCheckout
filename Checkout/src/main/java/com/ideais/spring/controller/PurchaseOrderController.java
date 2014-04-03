package com.ideais.spring.controller;

import com.ideais.spring.dao.domain.checkout.PurchaseOrder;
import com.ideais.spring.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("PurchaseOrderController")
@RequestMapping("/purchaseHistory")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class PurchaseOrderController {

    @Autowired
    private GenericService<PurchaseOrder> purchaseOrderService;
    
    @RequestMapping("/list")
    public ModelAndView list(){
        return new ModelAndView("purchaseorder/list", "list", purchaseOrderService.listObjects());
    }

    @RequestMapping(value = "/new",method = RequestMethod.GET)
    public ModelAndView newPurchaseHistory(){
        return new ModelAndView("purchaseorder/new", "purchaseHistory", new PurchaseOrder());
    }

    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public String newPurchaseHistory(PurchaseOrder purchaseHistory){
    	purchaseOrderService.save(purchaseHistory);
        return "redirect:list";
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Long id){
        PurchaseOrder purchaseHistory = purchaseOrderService.find(id);
        return new ModelAndView("purchaseorder/edit", "purchaseHistory", purchaseHistory);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public String edit(PurchaseOrder purchaseHistory) {
    	purchaseOrderService.save(purchaseHistory);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Long id){
        PurchaseOrder purchaseHistory = purchaseOrderService.find(id);
        return new ModelAndView("purchaseorder/delete", "purchaseHistory", purchaseHistory);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public String delete(PurchaseOrder purchaseHistory){
    	purchaseOrderService.remove(purchaseHistory);
        return "redirect:list";
    }

}