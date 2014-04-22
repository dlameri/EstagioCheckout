package com.ideais.spring.dao.interfaces;

import java.io.IOException;
import java.util.List;
import org.apache.commons.httpclient.HttpException;
import com.ideais.spring.domain.stock.json.SubcategoryJSON;

public interface SubcategoryDaoBehavior {
	
	public List<SubcategoryJSON> findAllByCategoryId(String linkId) throws HttpException, IOException;
	
}