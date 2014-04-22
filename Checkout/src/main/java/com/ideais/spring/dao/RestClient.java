package com.ideais.spring.dao;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.springframework.stereotype.Component;
import com.ideais.spring.dao.interfaces.Methods;

@Component("restClient")
public class RestClient implements Methods {

	private final static int STATUS_OK = 200;
	private ResteasyClient client = new ResteasyClientBuilder().build();

	public RestClient() {}

	@Override
	public <T> Object get(String url, GenericType<T> type) {
		Response response = client.target(url).request().get();
		if (response.getStatus() == STATUS_OK) {
			return response.readEntity(type);
		}

		return null;
	}

	@Override
	public boolean post(String url, String json) {
		Response response = client.target(url).request().post(Entity.entity(json, "application/json"));
		if (response.getStatus() == STATUS_OK) {
			return true;
		}
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