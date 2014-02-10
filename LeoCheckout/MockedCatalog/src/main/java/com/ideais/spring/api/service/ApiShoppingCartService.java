package com.ideais.spring.api.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ideais.spring.api.service.model.json.Image;
import com.ideais.spring.api.service.model.json.Product;
import com.ideais.spring.api.service.model.json.ShoppingCart;
import com.ideais.spring.api.service.model.json.Sku;

@Component("apiShoppingCartService")
public class ApiShoppingCartService {
	
	public ShoppingCart getShoppingCart() {
		//criando images
		Image image = new Image();
		image.setId(1L);
		image.setAlt("imagem do sku1");
		image.setPath("/images/image1.jpg");
		
		Image image2 = new Image();
		image2.setId(2L);
		image2.setAlt("imagem do sku2");
		image2.setPath("/images/image2.jpg");
		
		List<Image> images = new ArrayList<Image>();
		images.add(image);
		images.add(image2);
		
		//criando produtos
		Product product = new Product();
		product.setId(1L);
		product.setName("Product1");
		product.setPriceFor(new BigDecimal(15.0));
		product.setPriceFrom(new BigDecimal(20.0));
		
		Product product2 = new Product();
		product2.setId(2L);
		product2.setName("Product2");
		product2.setPriceFor(new BigDecimal(17.0));
		product2.setPriceFrom(new BigDecimal(25.0));
		
		
		//criando skus
		Sku sku = new Sku();
		sku.setId(1L);
		sku.setName("Sku de produto 1");
		sku.setProduct(product);
		sku.setQuantity(1);
		sku.setStock(80);
		sku.setImages(images);
		sku.setShortDescription("short description for sku1");
		sku.setLongDescription("Long description for Sku1");
		sku.setDepth(1);
		sku.setHeight(1);
		sku.setWeight(1);
		sku.setWarranty(1);
		sku.setWidth(1);
		sku.setModel("Model for sku1");
		
		Sku sku2 = new Sku();
		sku2.setId(2L);
		sku2.setName("Sku de produto 2");
		sku2.setProduct(product2);
		sku2.setQuantity(2);
		sku2.setStock(78);
		sku2.setImages(images);
		sku2.setShortDescription("short description for sku2");
		sku2.setLongDescription("Long description for Sku2");
		sku2.setDepth(2);
		sku2.setHeight(2);
		sku2.setWeight(2);
		sku2.setWarranty(2);
		sku2.setWidth(2);
		sku2.setModel("Model for sku2");
		
		List<Sku> skus = new ArrayList<Sku>();
		skus.add(sku);
		skus.add(sku2);
		
		//criando shoppingCart
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setId(1L);
		shoppingCart.setSkus(skus);
		
		return shoppingCart;
	}

}