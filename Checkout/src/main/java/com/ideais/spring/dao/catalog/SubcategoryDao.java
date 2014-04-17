package com.ideais.spring.dao.catalog;

import java.util.List;

import javax.ws.rs.core.GenericType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideais.spring.dao.checkout.catalog.interfaces.SubcategoryDaoBehavior;
import com.ideais.spring.dao.domain.checkout.stock.Subcategory;

@Service
public class SubcategoryDao extends AbstractDao implements SubcategoryDaoBehavior {
	
	@Autowired
	protected String stockUrlSubcategory;
	
	@Override
	public Subcategory findById(Long id) {
		return (Subcategory) restClient.get(stockUrlSubcategory + id, new GenericType<Subcategory>() {});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Subcategory> list() {
		return (List<Subcategory>) restClient.get(stockUrlSubcategory, new GenericType< List<Subcategory> >() {});
	}	
}