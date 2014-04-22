package com.ideais.spring.service.interfaces;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpException;

import com.ideais.spring.domain.catalog.Category;

public interface CategoryServiceBehavior {
	
	public List<Category> listAll() throws HttpException, IOException;
	
} 