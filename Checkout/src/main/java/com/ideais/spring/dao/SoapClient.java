package com.ideais.spring.dao;

import java.io.IOException;
import javax.ws.rs.core.GenericType;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.stereotype.Component;
import com.ideais.spring.dao.interfaces.Methods;

@Component("soapClient")
public class SoapClient implements Methods {

	private final static int STATUS_OK = 200;
	private HttpClient client = new HttpClient();

	public SoapClient() {}

	@Override
	public <T> Object get(String url, GenericType<T> type) throws HttpException, IOException {
		GetMethod method = new GetMethod(url);  
		
		if (client.executeMethod(method) == STATUS_OK) {
			return method.getResponseBodyAsString();
		}

		return null;
	}

	@Override
	public boolean post(String url, String json) {
		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public boolean put(String url, String json) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void options() {
		// TODO Auto-generated method stub
	}

}