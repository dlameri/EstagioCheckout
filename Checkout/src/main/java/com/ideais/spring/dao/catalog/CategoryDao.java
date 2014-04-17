package com.ideais.spring.dao.catalog;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.GenericType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideais.spring.dao.checkout.catalog.interfaces.CategoryDaoBehavior;
import com.ideais.spring.dao.domain.checkout.stock.Category;

@Service
public class CategoryDao extends AbstractDao implements CategoryDaoBehavior {

	@Autowired
	private String stockUrlCategory;
	
	private List<Category> categoriesCache = new ArrayList<Category>();
	
	public CategoryDao() {
		//loadCache();
	}
	
	@Override
	public Category findById(Long id) {
		for (Category category : categoriesCache) {
			if ( category.getId().equals(id) ) {
				return category;
			}
		}
		
		return null;
	}
	
	@Override
	public List<Category> list() {
		loadCache();
		return categoriesCache;
	}

	@SuppressWarnings("unchecked")
	private void loadCache() {
		categoriesCache.clear();
		categoriesCache.addAll( (List<Category>) restClient.get(stockUrlCategory, new GenericType< List<Category> >() {}) );
	}

	@Override
	public Boolean clearCache() {
		try {
			loadCache();
			return Boolean.TRUE;
		} catch(Exception e) {
			//TODO logar o motivo de ter dado zica
			return Boolean.FALSE;
		}
	}
}
