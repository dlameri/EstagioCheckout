package com.ideais.spring.service;

import java.io.IOException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ideais.spring.dao.ItemJsonDao;
import com.ideais.spring.dao.ProductJsonDao;
import com.ideais.spring.dao.domain.checkout.stock.Item;

@Service("itemService")
public class ItemService {
	
	@Autowired
    private ItemJsonDao itemJsonDao;
	@Autowired
    private ProductJsonDao productJsonDao;
	
	public Item getItem(Long id) throws IOException, JSONException {		
		Item item = itemJsonDao.getItemFromStock(id); 
		
		if (item != null) {
			item.setProduct(productJsonDao.getProductFromStock(item.getProductId()));
		}
		
		return item;
	}
	
	public Integer refreshItemQuantity(String cartJson) {
		return itemJsonDao.updateStock(cartJson);
	}
	
}