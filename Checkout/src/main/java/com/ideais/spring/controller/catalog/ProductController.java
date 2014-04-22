package com.ideais.spring.controller.catalog;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ideais.spring.dao.interfaces.ProductDaoBehavior;

@Controller("productController")
@RequestMapping("/product")
public class ProductController extends BaseController {
	
	@Autowired @Qualifier("productDao")
	private ProductDaoBehavior productDao;
	
	@RequestMapping ( value = "/search", method = RequestMethod.GET)
	public ModelAndView searchProduct(@RequestParam(value="name", required=false) String productName, HttpServletRequest request) {
		ModelAndView view = getBaseView("catalogo/productSearch", request);
		
		try {
			view.addObject("product", productDao.findByName(productName));
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