package com.ideais.spring.dao;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.springframework.stereotype.Component;

import com.ideais.spring.dao.interfaces.Methods;

@Component("restClient")
public class RestClient implements Methods {
	
	private static Log LOGGER = LogFactory.getLog(RestClient.class);
	private final static int STATUS_OK = 200;

	public RestClient() {}
	
	public ResteasyClient openConnection() {
		return new ResteasyClientBuilder().build();
	}
	
	public void closeConnection(ResteasyClient client) {
		client.close();
	}

	@Override
	public <T> Object get(String url, GenericType<T> entity) {
		ResteasyClient client = openConnection();
		Response response = null;
		try{
			response = client.target(url).request().get();
			if (response.getStatus() == STATUS_OK) {
				T entityResponse = response.readEntity(entity);
				response.close();
				return entityResponse;
			}
			response.close();
		} catch(ProcessingException exception) {
			LOGGER.fatal("Nao foi possivel acessar o seguinte servico ["+url+"]", exception);
			closeConnection(client);
			response.close();
			return null;
		} catch(Exception exception) {
			LOGGER.fatal("Ocorreu um erro não mapeado", exception);
			return null;
		}

		closeConnection(client);
		return null;
	}

	@Override
	public boolean post(String url, String json) {
		ResteasyClient client = openConnection();
		Response response = null;
		try {
			response = client.target(url).request().post(Entity.entity(json, "application/json"));
			if (response.getStatus() == STATUS_OK) {
				closeConnection(client);
				response.close();
				return true;
			}
		} catch(ResponseProcessingException exception) {
			LOGGER.fatal("Nao foi possivel enviar o json ["+json+"] para o seguinte servico ["+url+"]", exception);
			closeConnection(client);
			response.close();
			return false;
		}
		closeConnection(client);
		response.close();
		return false;
	}

	@Override
	public void put(String url, String json) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void options() {
		// TODO Auto-generated method stub
		
	}
}