package com.ideais.spring.dao.interfaces;

import java.io.IOException;
import java.util.List;
import org.apache.commons.httpclient.HttpException;
import com.ideais.spring.domain.stock.json.ImageJSON;

public interface ImageDaoBehavior {
	
	public List<ImageJSON> findById(String linkId) throws HttpException, IOException;

}