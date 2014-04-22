package com.ideais.spring.controller.catalog;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import com.ideais.spring.service.interfaces.CategoryServiceBehavior;
import com.ideais.spring.service.interfaces.ShoppingCartServiceBehavior;

@Component
public abstract class BaseController {
	
	@Autowired
	protected CategoryServiceBehavior categoryService;
	@Autowired
	protected ShoppingCartServiceBehavior shoppingCartService;
	
	public BaseController() {}
	
	public ModelAndView getBaseView(String pageName, HttpServletRequest request) {
		ModelAndView view = new ModelAndView(pageName);
		
		try {
			view.addObject("categories", categoryService.listAll());
			view.addObject("cartQtdItens", shoppingCartService.cartQtd(request));
			
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return view;
	}

}