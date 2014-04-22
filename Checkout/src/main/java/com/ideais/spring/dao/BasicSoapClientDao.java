package com.ideais.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.ideais.spring.dao.interfaces.Methods;

@Component
public abstract class BasicSoapClientDao {
	
	@Autowired
	@Qualifier("soapClient")
	protected Methods client;

}