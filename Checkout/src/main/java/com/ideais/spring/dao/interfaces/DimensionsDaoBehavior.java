package com.ideais.spring.dao.interfaces;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

import com.ideais.spring.domain.checkout.Dimensions;

public interface DimensionsDaoBehavior {
	
	public Dimensions findById(String linkId) throws HttpException, IOException;

}