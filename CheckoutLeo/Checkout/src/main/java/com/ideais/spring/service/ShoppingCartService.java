package com.ideais.spring.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.ideais.spring.model.checkout.MeanOfPayment;
import com.ideais.spring.model.checkout.ShoppingCart;
import com.ideais.spring.model.checkout.ShoppingCartLine;
import com.ideais.spring.model.stock.Brand;
import com.ideais.spring.model.stock.Image;
import com.ideais.spring.model.stock.Product;
import com.ideais.spring.model.stock.Sku;

@Component("shoppingCartService")
public class ShoppingCartService implements GenericService<ShoppingCart>{

	//se produto(sku) está no carrinho então qtd-- p/ catalogo
	
	public List<ShoppingCart> listObjects() {
		return null;
	}

	
	public List<ShoppingCartLine> listShoppingCartLines() {
		//criando produto
		Product product = new Product();
		
		//criando imagens
		List<Image> images = new ArrayList<Image>();
		
		//criando imagem
		Image image = new Image();
		image.setPath("image.jpg");
		image.setMain(true);
		//image.setSku(item);
		
		//seta imagem em imagens
		images.add(image);
		
		//seta atributos de produto
		product.setId(1L);
		product.setName("Produto 1");
		product.setOptionName("Cor");
		product.setOptionValue("Verde");
		product.setPriceFor(10.0);
		product.setPriceFrom(15.0);
		
		//cria marca
		Brand brand = new Brand();
		brand.setName("Marca1");
		brand.setDescription("Decrição da Marca1");
		
		//cria sku
		Sku sku1 = new Sku();
		sku1.setBrand(brand);
		sku1.setDepth(1);
		sku1.setHeight(2);
		sku1.setWidth(3);
		sku1.setId(1L);
		sku1.setImages(images);
		sku1.setLongDescription("Description for Sku1");
		sku1.setModel("Model for Sku1");
		sku1.setName("Sku1");
		sku1.setProduct(product);
		sku1.setShortDescription("Short Description for Sku1");
		sku1.setStock(2);
		sku1.setWarranty(2);
		
		//cria Array de skus
		List<Sku> skus = new ArrayList<Sku>();
		skus.add(sku1);
		product.setSkus(skus);
		
		//criando shoppingCartLine
		ShoppingCartLine shoppingCartLine = new ShoppingCartLine();
		shoppingCartLine.setId(1L);
		shoppingCartLine.setSku(sku1);
		try {
			shoppingCartLine.setQuantity(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shoppingCartLine.setPrice();
		
		//cria array de cartLines
		List<ShoppingCartLine> shoppingCartLines = new ArrayList<ShoppingCartLine>();
		shoppingCartLines.add(shoppingCartLine);
		
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setId(1L);
		shoppingCart.setMeanOfPayment(MeanOfPayment.DEPOSIT);
		shoppingCart.setShoppingCartLines(shoppingCartLines);
		
		//amarra
		image.setSku(sku1);
		brand.setSkus(skus);
		shoppingCartLine.setShoppingCart(shoppingCart);
		
		
		//coloca na sessão
		
		
		return shoppingCartLines;
	}

	@Override
	public ShoppingCart find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(ShoppingCart shoppingCart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(ShoppingCart shoppingCart) {
		// TODO Auto-generated method stub
		
		//deleta e coloca na sessão de novo
	}

}