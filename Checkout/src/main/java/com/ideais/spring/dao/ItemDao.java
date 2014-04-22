package com.ideais.spring.dao;

import java.io.IOException;
import javax.ws.rs.core.GenericType;
import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ideais.spring.dao.interfaces.ItemDaoBehavior;
import com.ideais.spring.domain.stock.json.ItemJSON;

@Service
public class ItemDao extends BasicRestClientDao implements ItemDaoBehavior {
	
	@Autowired
	private String stockIntegrationUrl;
	private final String ITEM_URL_BASE = "item/";
	private final String UPDATE_STOCK_URL = "item/updatestock";

	@Override
	public ItemJSON findById(Long id) throws HttpException, IOException {
		return (ItemJSON) client.get(buildFindItemByIdUrl(id), new GenericType<ItemJSON>() {});
	}

	private String buildFindItemByIdUrl(Long id) {
		return stockIntegrationUrl + ITEM_URL_BASE + id;
	}
	
	@Override
	public Boolean updateStock(String cartJson) {
        return client.post(buildUpdateStockString(), cartJson);
	}
	
	private String buildUpdateStockString() {
		return stockIntegrationUrl + UPDATE_STOCK_URL;
	}

}