package com.ideais.spring.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideais.spring.dao.interfaces.CategoryDaoBehavior;
import com.ideais.spring.dao.interfaces.SubcategoryDaoBehavior;
import com.ideais.spring.domain.catalog.Category;
import com.ideais.spring.domain.catalog.Subcategory;
import com.ideais.spring.domain.stock.json.CategoryJSON;
import com.ideais.spring.domain.stock.json.SubcategoryJSON;
import com.ideais.spring.service.interfaces.CategoryServiceBehavior;

@Service("categoryService")
public class CategoryService implements CategoryServiceBehavior {

	@Autowired
    private CategoryDaoBehavior categoryDao;
	@Autowired
    private SubcategoryDaoBehavior subcategoryDao;

	@Override
	public List<Category> listAll() throws HttpException, IOException {
		List<CategoryJSON> categoriesJSON = categoryDao.list();
		List<Category> categories = new ArrayList<Category>();
		
		buildCategories(categoriesJSON, categories);	
		
		return categories;
	}

	private void buildCategories(List<CategoryJSON> categoriesJSON, List<Category> categories) throws HttpException, IOException {
		if (categoriesJSON != null) {
			for (int i = 0; i < categoriesJSON.size(); i++) {
				CategoryJSON categoryJSON = categoriesJSON.get(i);
				Category category = new Category(categoryJSON);
				
				buildSubcategories(i, categoryJSON, category);		
				categories.add(category);
			}
		}
	}

	private void buildSubcategories(int i, CategoryJSON categoryJSON, Category category) throws HttpException, IOException {
		List<SubcategoryJSON> subcategoriesJSON = subcategoryDao.findAllByCategoryId(categoryJSON.getURI("subcategory"));
		List<Subcategory> subcategories = new ArrayList<Subcategory>();
		
		if (subcategoriesJSON != null) {
			for (int j = 0; j < subcategoriesJSON.size(); j++) {
				Subcategory subcategory = new Subcategory(subcategoriesJSON.get(j));
				subcategory.setCategory(category);
				
				subcategories.add(subcategory);
			}
		}
		
		category.setSubcategories(subcategories);
	}

}