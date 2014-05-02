package com.ideais.spring.controller.checkout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ideais.spring.controller.catalog.BaseController;
import com.ideais.spring.domain.checkout.Customer;
import com.ideais.spring.service.MailService;
import com.ideais.spring.service.interfaces.CustomerServiceBehavior;

@Controller("LoginLogoutController")
@RequestMapping("/customer/authenticate")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class LoginLogoutController extends BaseController{
        
	protected static Logger logger = Logger.getLogger("controller");
    @Autowired
    private CustomerServiceBehavior customerService;
    @Autowired
    private MailService mailService;
	private static final String CUSTOMER_KEY = "customer";
        
	@RequestMapping("loginForm")
	public ModelAndView loginForm(HttpServletRequest request) {
		return getBaseView("customer/loginpage", request);
	}
	
	@RequestMapping("loginForm/{status}")
	public ModelAndView loginFormMessage(@PathVariable String status, HttpServletRequest request) {
		ModelAndView view = getBaseView("customer/loginpage", request);
		
		if ("error".equals(status)) {
			view.addObject("errorMessage", "Login ou senha inválidos!");
		} else if ("successRegister".equals(status)) {
			view.addObject("successMessage", "Você foi cadastrado com sucesso.");
		}
		
		return view;
	}
	
	@RequestMapping("loginUser")
	public String loginUser(@RequestParam(value="userLogin", required=false) String userLogin, 
			   @RequestParam(value="userPassword", required=false) String userPassword,
			   HttpServletResponse response,
			   HttpServletRequest request, 
			   HttpSession session) {
				
		Customer customer = customerService.customerLogin(userLogin, userPassword);
		String targetUrl = (String) request.getSession().getAttribute("loginTarget");
		
		if(customer != null) {
			session.setAttribute(CUSTOMER_KEY, customer);
			response.addCookie(customerService.createCustomerCookie(customer));
			
			if (targetUrl == null || (targetUrl != null && (targetUrl.contains("address") || (targetUrl.contains("Checkout/customer/authenticate/loginForm"))))) {
				return "redirect:http://ideaiselectronics.com:8081/Catalogo/";
			} 

			return "redirect:http://ideaiselectronics.com:9082" + targetUrl;
	  }
		
	  return "redirect:loginForm/error";
	}
	
	@RequestMapping("recoverPasswordForm")
	public ModelAndView recoverPasswordForm(HttpServletRequest request) {
		return getBaseView("customer/recoverpassword", request);
	}
	
	@RequestMapping("recoverPasswordForm/{status}")
	public ModelAndView recoverPasswordFormMessage(@PathVariable String status, HttpServletRequest request) {
		ModelAndView view = getBaseView("customer/recoverpassword", request);
		
		if ("error".equals(status)) {
			view.addObject("errorMessage", "Erro ao enviar o email, email não encontrado.");
		} else if ("success".equals(status)) {
			view.addObject("successMessage", "Email enviado com sucesso.");
		}
		
		return view;
	}
	
	@RequestMapping("recoverPassword")
	public String recoverPassword(@RequestParam(value="userEmail", required=false) String userEmail, 
			@RequestParam(value="userLogin", required=false) String userName,
		    HttpServletResponse response,
		    HttpServletRequest request, 
		    HttpSession session) {
		try {				
			String password = customerService.findPassword(userName, userEmail);
			 
			if (password != null) {
				mailService.sendPasswordMail(password, userEmail);
				return "redirect:http://ideaiselectronics.com:9082/Checkout/customer/authenticate/recoverPasswordForm/success";
			}
			return "redirect:http://ideaiselectronics.com:9082/Checkout/customer/authenticate/recoverPasswordForm/error";
  		} catch (MailException e) {
		  		return "redirect:http://ideaiselectronics.com:9082/Checkout/customer/authenticate/recoverPasswordForm/error";
  		}
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session, HttpServletResponse response) {
	  session.setAttribute(CUSTOMER_KEY, null);
	  customerService.removeCustomerCookie(response);
	  
	  return "redirect:http://ideaiselectronics.com:8081/Catalogo/";
	}

}