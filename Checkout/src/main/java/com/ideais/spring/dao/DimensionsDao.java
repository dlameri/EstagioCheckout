package com.ideais.spring.dao;

import java.io.IOException;

import javax.ws.rs.core.GenericType;

import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideais.spring.dao.interfaces.DimensionsDaoBehavior;
import com.ideais.spring.domain.checkout.Dimensions;

@Service
public class DimensionsDao extends BasicRestClientDao implements DimensionsDaoBehavior {
	
	@Autowired
	private String stockIntegrationUrl;
	
	@Override
	public Dimensions findById(String linkId) throws HttpException, IOException {
		return (Dimensions) client.get(stockIntegrationUrl + linkId, new GenericType<Dimensions>() {});
	}
	
}