package com.ideais.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.ideais.spring.domain.LoginForm;
import com.ideais.spring.service.interfaces.CustomerServiceBehavior;

@Controller
@RequestMapping("/auth")
public class LoginLogoutController {

	@Autowired
	private CustomerServiceBehavior customerService;

	protected static Logger logger = Logger.getLogger("controller");
	private static final String CUSTOMER_KEY = "customer";

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLoginForm() {
		LoginForm loginForm = new LoginForm();
		ModelAndView model = new ModelAndView("auth/login", "loginForm", loginForm);
		return model;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView processCredentials(LoginForm loginForm,  HttpServletRequest request) {
		String message = "Invalid credentials";
		
		if(isLoginCorrect(loginForm)) {
			message = "Bem-vindo " + loginForm.getUserName() + "!!";
			return new ModelAndView("auth/success","message",message);
		}
		return new ModelAndView("auth/denied","message",message);
	}

	private boolean isLoginCorrect(LoginForm loginForm) {
		if(customerService.findByLogin(loginForm.getUserName(), loginForm.getPassword())!=null){
			return true;
		}
	
		return false;
	}
	
	private String filterLoggedCustomer(HttpServletRequest request) {
		if (request.getSession().getAttribute(CUSTOMER_KEY) != null) {
			return "auth/success";
		}
		

		return "/customer/login";
	}

	private void hadleError(ModelMap model, boolean error) {
		if (error == true) {
			model.put("error", "You have entered an invalid username or password!");
		} else {
			model.put("error", "");
		}	
	}

	@RequestMapping(value = "/denied", method = RequestMethod.GET)
 	public String getDeniedPage() {
		logger.debug("Received request to show denied page");
		
		return "auth/login";
	}
}