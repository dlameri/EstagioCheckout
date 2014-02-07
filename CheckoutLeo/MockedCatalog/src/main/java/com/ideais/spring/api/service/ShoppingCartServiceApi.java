package com.ideais.spring.api.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.ideais.spring.api.service.model.json.Image;
import com.ideais.spring.api.service.model.json.ShoppingCart;
import com.ideais.spring.api.service.model.json.Sku;

@Component("shoppingCartServiceApi")
public class ShoppingCartServiceApi {
	
	public ShoppingCart getShoppingCart() {
		//criando images
		Image image = new Image();
		image.setImageId(1L);
		image.setAlt("imagem do sku1");
		image.setPath("/images/image1.jpg");
		
		Image image2 = new Image();
		image2.setImageId(2L);
		image2.setAlt("imagem do sku2");
		image2.setPath("/images/image2.jpg");
		
		//criando skus
		Sku sku = new Sku();
		sku.setId(1L);
		sku.setName("Sku de produto 1");
		sku.setPriceFor(15.0);
		sku.setPriceFrom(20.0);
		sku.setProductId(1L);
		sku.setQuantity(1);
		sku.setStock(80);
		sku.setImage(image);
		sku.setShortDescription("short description for sku1");
		sku.setLongDescription("Long description for Sku1");
		
		Sku sku2 = new Sku();
		sku2.setId(2L);
		sku2.setName("Sku de produto 2");
		sku2.setPriceFor(16.0);
		sku2.setPriceFrom(22.0);
		sku2.setProductId(2L);
		sku2.setQuantity(2);
		sku2.setStock(78);
		sku2.setImage(image2);
		sku2.setShortDescription("short description for sku2");
		sku2.setLongDescription("Long description for Sku2");
		
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