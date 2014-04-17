package com.ideais.spring.dao.checkout.catalog.interfaces;

import java.util.List;

import com.ideais.spring.dao.domain.checkout.stock.Category;

public interface CategoryDaoBehavior {
	
	public Category findById(Long id);
	
	public List<Category> list();
	
	public Boolean clearCache();

}
