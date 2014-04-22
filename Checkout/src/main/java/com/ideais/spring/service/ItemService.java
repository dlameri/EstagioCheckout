package com.ideais.spring.service;

import java.io.IOException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ideais.spring.dao.interfaces.ItemDaoBehavior;
import com.ideais.spring.dao.interfaces.ProductDaoBehavior;
import com.ideais.spring.domain.Item;
import com.ideais.spring.domain.catalog.Product;
import com.ideais.spring.domain.stock.json.ItemJSON;
import com.ideais.spring.domain.stock.json.ProductJSON;
import com.ideais.spring.service.interfaces.ItemServiceBehavior;

@Service("itemService")
public class ItemService implements ItemServiceBehavior {
	
	@Autowired
    private ItemDaoBehavior itemDao;
	@Autowired
    private ProductDaoBehavior productDao;
	
	@Override
	public Item getItem(Long id) throws IOException, JSONException {		
		ItemJSON itemJSON = itemDao.findById(id); 
		Item item = new Item(itemJSON);
		
		if (itemJSON != null) {
			ProductJSON productJSON = productDao.findById(itemJSON.getURI("product"));
			Product product = new Product(productJSON);
			
			if (productJSON != null) {
				item.setProductName(product.getName());
				item.setProductId(product.getId());
			}
			
			//pegar imagem e dimensão das urls

		}
		
		return item;
	}
	
	@Override
	public Boolean refreshItemQuantity(String cartJson) {
		return itemDao.updateStock(cartJson);
	}
	
}