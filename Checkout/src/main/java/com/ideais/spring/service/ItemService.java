package com.ideais.spring.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideais.spring.dao.interfaces.DimensionsDaoBehavior;
import com.ideais.spring.dao.interfaces.ImageDaoBehavior;
import com.ideais.spring.dao.interfaces.ItemDaoBehavior;
import com.ideais.spring.dao.interfaces.ProductDaoBehavior;
import com.ideais.spring.domain.catalog.Product;
import com.ideais.spring.domain.checkout.Dimensions;
import com.ideais.spring.domain.checkout.Item;
import com.ideais.spring.domain.stock.json.ImageJSON;
import com.ideais.spring.domain.stock.json.ItemJSON;
import com.ideais.spring.domain.stock.json.ProductJSON;
import com.ideais.spring.service.interfaces.ItemServiceBehavior;

@Service("itemService")
public class ItemService implements ItemServiceBehavior {
	
	@Autowired
    private ItemDaoBehavior itemDao;
	@Autowired
    private ProductDaoBehavior productDao;
	@Autowired
    private DimensionsDaoBehavior dimensionsDao;
	@Autowired
    private ImageDaoBehavior imageDao;
	
	@Override
	public Item getItem(Long id) throws IOException, JSONException {		
		ItemJSON itemJSON = itemDao.findById(id); 
		Item item = new Item(itemJSON);
		
		return buildItem(itemJSON, item);
	}

	private Item buildItem(ItemJSON itemJSON, Item item) throws HttpException, IOException {
		if (itemJSON != null) {
			getProductFromStock(itemJSON, item);
						
			return item;
		}
		
		return null;
	}

	private void getProductFromStock(ItemJSON itemJSON, Item item) throws HttpException, IOException {
		ProductJSON productJSON = productDao.findById(itemJSON.getURI("product"));
		Product product = new Product(productJSON);
		
		if (productJSON != null) {
			getDImensionsFromStock(item, productJSON);
						
			item.setProductName(product.getName());
			item.setProductId(product.getId());
			item.setWeight(product.getWeight());
			
			getImagesFromStock(itemJSON, item);	
		}
	}

	private void getImagesFromStock(ItemJSON itemJSON, Item item) throws HttpException, IOException {
		List<ImageJSON> imageJSONs = imageDao.findById(itemJSON.getURI("image"));
		
		if (imageJSONs != null) {
			for (int i = 0; i < imageJSONs.size(); i++) {
				if (imageJSONs.get(i).getMain()) {							
					item.setImageUrl(imageJSONs.get(i).getShoppingCartUrl());
				}
			}
		}
	}

	private void getDImensionsFromStock(Item item, ProductJSON productJSON) throws HttpException, IOException {
		Dimensions dimensions = dimensionsDao.findById(productJSON.getURI("dimensions"));
		item.setDimensions(dimensions);
	}
	
	@Override
	public Boolean refreshItemQuantity(String cartJson) {
		return itemDao.updateStock(cartJson);
	}
	
}