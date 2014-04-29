package com.ideais.spring.controller.checkout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.ideais.spring.controller.catalog.BaseController;
import com.ideais.spring.domain.checkout.Customer;
import com.ideais.spring.service.interfaces.CustomerServiceBehavior;

@Controller("LoginLogoutController")
@RequestMapping("/customer/authenticate")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class LoginLogoutController extends BaseController{
        
	protected static Logger logger = Logger.getLogger("controller");
    @Autowired
    private CustomerServiceBehavior customerService;
	private static final String CUSTOMER_KEY = "customer";
        
	@RequestMapping("loginForm")
	public ModelAndView loginForm(HttpServletRequest request) {
		return getBaseView("customer/loginpage", request);
	}
	
	@RequestMapping("loginOrderForm")
	public ModelAndView loginOrderForm(HttpServletRequest request) {
		return getBaseView("customer/loginorderpage", request);
	}

	@RequestMapping("loginUserToOrder")
	public String loginUserToOrder(@RequestParam(value="userLogin", required=false) String userLogin, 
			   @RequestParam(value="userPassword", required=false) String userPassword,
			   HttpServletResponse response,
			   HttpServletRequest request, 
			   HttpSession session) {
				
		Customer customer = customerService.customerLogin(userLogin, userPassword);
		
		if(customer != null) {
			session.setAttribute(CUSTOMER_KEY, customer);
			response.addCookie(customerService.createCustomerCookie(customer));
			
			return "redirect:../../purchaseOrder/paymentDetails";
	  }
		
	  return "redirect:loginOrderForm";
	}
	
	@RequestMapping("loginUser")
	public String loginUser(@RequestParam(value="userLogin", required=false) String userLogin, 
			   @RequestParam(value="userPassword", required=false) String userPassword,
			   HttpServletResponse response,
			   HttpServletRequest request, 
			   HttpSession session) {
				
		Customer customer = customerService.customerLogin(userLogin, userPassword);
		
		if(customer != null) {
			session.setAttribute(CUSTOMER_KEY, customer);
			response.addCookie(customerService.createCustomerCookie(customer));
			
			return "redirect:http://ideaiselectronics.com:8081/Catalogo/";
	  }
		
	  return "redirect:loginForm";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
	  session.setAttribute(CUSTOMER_KEY, null);
		
	  return "redirect:http://ideaiselectronics.com:8081/Catalogo/";
	}

}