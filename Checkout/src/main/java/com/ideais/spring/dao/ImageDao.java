package com.ideais.spring.dao;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.GenericType;

import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideais.spring.dao.interfaces.ImageDaoBehavior;
import com.ideais.spring.domain.stock.json.ImageJSON;

@SuppressWarnings("unchecked")
@Service
public class ImageDao extends BasicRestClientDao implements ImageDaoBehavior {
	
	@Autowired
	private String stockIntegrationUrl;
	
	@Override
	public List<ImageJSON> findById(String linkId) throws HttpException, IOException {
		return (List<ImageJSON>) client.get(stockIntegrationUrl + linkId, new GenericType<List<ImageJSON>>() {});
	}
	
}