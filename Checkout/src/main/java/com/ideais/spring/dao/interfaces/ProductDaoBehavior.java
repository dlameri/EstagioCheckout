package com.ideais.spring.dao.interfaces;

import java.io.IOException;
import java.util.List;
import org.apache.commons.httpclient.HttpException;
import com.ideais.spring.domain.catalog.Product;
import com.ideais.spring.domain.stock.json.ProductJSON;

public interface ProductDaoBehavior {
	
	public ProductJSON findById(String linkId) throws HttpException, IOException;
	
	public List<Product> findByName(String name)throws HttpException, IOException;

}