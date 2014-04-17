package com.ideais.spring.dao.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ideais.spring.dao.checkout.catalog.interfaces.Methods;

@Component
public abstract class AbstractDao {
	
	@Autowired
	protected Methods restClient;
}
