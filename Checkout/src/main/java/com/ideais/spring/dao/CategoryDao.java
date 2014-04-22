package com.ideais.spring.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.GenericType;
import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ideais.spring.dao.interfaces.CategoryDaoBehavior;
import com.ideais.spring.domain.stock.json.CategoryJSON;

@Service
public class CategoryDao extends BasicRestClientDao implements CategoryDaoBehavior {

	@Autowired
	private String stockUrlCategory;
	private List<CategoryJSON> categoriesJSONCache = new ArrayList<CategoryJSON>();
	
	public CategoryDao() {}
	
	@Override
	public CategoryJSON findById(Long id) {
		for (CategoryJSON categoryJSON : categoriesJSONCache) {
			if (categoryJSON.getId().equals(id)) {
				return categoryJSON;
			}
		}
		
		return null;
	}
	
	@Override
	public List<CategoryJSON> list() throws HttpException, IOException {
		loadCache();
		return categoriesJSONCache;
	}

	@SuppressWarnings("unchecked")
	private void loadCache() throws HttpException, IOException {
		categoriesJSONCache.clear();
		categoriesJSONCache.addAll((List<CategoryJSON>) client.get(stockUrlCategory, new GenericType< List<CategoryJSON> >() {}));
	}

	@Override
	public Boolean clearCache() {
		try {
			loadCache();
			return Boolean.TRUE;
		} catch(Exception e) {
			return Boolean.FALSE;
		}
	}
	
}