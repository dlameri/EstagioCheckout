package com.ideais.spring.service;

import java.io.IOException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ideais.spring.dao.ItemJsonDao;
import com.ideais.spring.dao.domain.checkout.stock.Item;

@Component("itemService")
public class ItemService {
	
	@Autowired
    private ItemJsonDao itemJsonDao;
	
	public Item getItem(Long id) throws IOException, JSONException {		
		return itemJsonDao.getItemFromStock(id);
	}
	
}