package com.ideais.spring.web;

import com.ideais.spring.model.checkout.PurchaseHistory;
import com.ideais.spring.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("PurchaseHistoryController")
@RequestMapping("/purchaseHistory")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class PurchaseHistoryController {

    @Autowired
    private GenericService<PurchaseHistory> purchaseHistoryService;
    
    @RequestMapping("/list")
    public ModelAndView list(){
        return new ModelAndView("purchaseHistory/list", "list", purchaseHistoryService.listObjects());
    }

    @RequestMapping(value = "/new",method = RequestMethod.GET)
    public ModelAndView newPurchaseHistory(){
        return new ModelAndView("purchaseHistory/new", "purchaseHistory", new PurchaseHistory());
    }

    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public String newPurchaseHistory(PurchaseHistory purchaseHistory){
    	purchaseHistoryService.save(purchaseHistory);
        return "redirect:list";
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Long id){
        PurchaseHistory purchaseHistory = purchaseHistoryService.find(id);
        return new ModelAndView("purchaseHistory/edit", "purchaseHistory", purchaseHistory);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public String edit(PurchaseHistory purchaseHistory) {
    	purchaseHistoryService.save(purchaseHistory);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Long id){
        PurchaseHistory purchaseHistory = purchaseHistoryService.find(id);
        return new ModelAndView("purchaseHistory/delete", "purchaseHistory", purchaseHistory);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public String delete(PurchaseHistory purchaseHistory){
    	purchaseHistoryService.remove(purchaseHistory);
        return "redirect:list";
    }

    public void setCustomerService(GenericService<PurchaseHistory> purchaseHistoryService) {
        this.purchaseHistoryService = purchaseHistoryService;
    }

}