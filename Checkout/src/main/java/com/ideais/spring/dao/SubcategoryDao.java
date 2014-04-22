package com.ideais.spring.dao;

import java.io.IOException;
import java.util.List;
import javax.ws.rs.core.GenericType;
import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ideais.spring.dao.interfaces.SubcategoryDaoBehavior;
import com.ideais.spring.domain.stock.json.SubcategoryJSON;

@SuppressWarnings("unchecked")
@Service
public class SubcategoryDao extends BasicRestClientDao implements SubcategoryDaoBehavior {
	
	@Autowired
	private String stockIntegrationUrl;

	@Override
	public List<SubcategoryJSON> findAllByCategoryId(String linkId) throws HttpException, IOException {
		return (List<SubcategoryJSON>) client.get(stockIntegrationUrl + linkId, new GenericType<List<SubcategoryJSON>>() {});
	}
	
}