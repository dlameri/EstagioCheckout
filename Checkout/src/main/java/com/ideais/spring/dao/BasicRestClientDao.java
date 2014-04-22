package com.ideais.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.ideais.spring.dao.interfaces.Methods;

@Component
public abstract class BasicRestClientDao {
	
	@Autowired
	@Qualifier("restClient")
	protected Methods client;

}