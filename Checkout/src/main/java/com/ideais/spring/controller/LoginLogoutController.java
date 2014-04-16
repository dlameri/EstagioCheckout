package com.ideais.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class LoginLogoutController {
        
	protected static Logger logger = Logger.getLogger("controller");
    private static final String CUSTOMER_KEY = "customer";

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(@RequestParam(value="error", required=false) boolean error,  ModelMap model, 
								HttpServletRequest request) {
		
		logger.debug("Received request to show login page");

		hadleError(model, error);
		
		return filterLoggedCustomer(request);
	}
	
	private String filterLoggedCustomer(HttpServletRequest request) {
		if (request.getSession().getAttribute(CUSTOMER_KEY) != null) {
			return "";
		}
		
		return "loginpage";
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
		
		return "deniedpage";
	}
}