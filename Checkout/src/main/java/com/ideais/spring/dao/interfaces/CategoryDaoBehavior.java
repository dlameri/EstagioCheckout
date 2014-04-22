package com.ideais.spring.dao.interfaces;

import java.io.IOException;
import java.util.List;
import org.apache.commons.httpclient.HttpException;
import com.ideais.spring.domain.stock.json.CategoryJSON;

public interface CategoryDaoBehavior {
	
	public CategoryJSON findById(Long id);
	
	public List<CategoryJSON> list() throws HttpException, IOException;
	
	public Boolean clearCache();

}