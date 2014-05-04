package com.ideais.spring.dao.interfaces;

import java.io.IOException;
import javax.ws.rs.core.GenericType;
import org.apache.commons.httpclient.HttpException;

public interface Methods {
	
	public <T> Object get(String url, GenericType<T> type) throws HttpException, IOException;
	
	public boolean post(String url, String json);
	
	public boolean put(String url, String json);
	
	public void options();

}