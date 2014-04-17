package com.ideais.spring.dao.checkout.catalog.interfaces;

import java.util.List;

import com.ideais.spring.dao.domain.checkout.stock.Subcategory;

public interface SubcategoryDaoBehavior {
	public Subcategory findById(Long id);

	public List<Subcategory> list();
}
