package com.ideais.spring.dao.interfaces;

import java.io.IOException;
import org.apache.commons.httpclient.HttpException;
import com.ideais.spring.domain.stock.json.ItemJSON;

public interface ItemDaoBehavior {
	
	public ItemJSON findById(Long id) throws HttpException, IOException;
	
	public Boolean updateStock(String cartJson);

}