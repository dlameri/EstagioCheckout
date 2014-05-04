package com.ideais.spring.controller.checkout.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ideais.spring.domain.checkout.PurchaseOrder;

public class AuthenticatorInterceptor extends HandlerInterceptorAdapter {
	
    private static final String CUSTOMER_KEY = "customer";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) throws Exception {
		String uri = request.getRequestURI();
		
		if((!uri.contains("purchase")  && !uri.contains("Details") && !uri.contains("address") &&
			!(uri.contains("edit") && uri.contains("customer")) && 
			!uri.contains("shipping")) || request.getSession().getAttribute(CUSTOMER_KEY) != null){
			return true;
		}
				
		request.getSession().setAttribute("loginTarget", uri);
		response.sendRedirect("http://ideaiselectronics.com:9082/Checkout/customer/authenticate/loginForm");
		
		return false;
	}

}