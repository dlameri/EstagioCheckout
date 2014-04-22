package com.ideais.spring.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.GenericType;
import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ideais.spring.dao.interfaces.ProductDaoBehavior;
import com.ideais.spring.domain.catalog.Product;
import com.ideais.spring.domain.stock.json.ProductJSON;

@SuppressWarnings("unchecked")
@Service
public class ProductDao extends BasicRestClientDao implements ProductDaoBehavior {
	
	@Autowired
	private String stockIntegrationUrl;
	
	@Override
	public ProductJSON findById(String linkId) throws HttpException, IOException {
		return (ProductJSON) client.get(stockIntegrationUrl + linkId, new GenericType<ProductJSON>() {});
	}

	@Override
	public List<Product> findByName(String name) throws HttpException, IOException {
		List<ProductJSON> productsJSON = (List<ProductJSON>) client.get(stockIntegrationUrl + "product/search/"+ name, new GenericType<List<ProductJSON> >(){});
		List<Product> products = new ArrayList<Product>();
		
		if (productsJSON != null) {
			for (int i = 0; i < productsJSON.size(); i++) {
				products.add(new Product(productsJSON.get(i)));
			}
		}
		
		return products;
	}
	
}