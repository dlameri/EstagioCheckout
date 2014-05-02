package com.ideais.spring.dao.interfaces;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;
import org.jdom2.JDOMException;

import com.ideais.spring.domain.checkout.FreightDetails;
import com.ideais.spring.domain.checkout.ItemsPackage;
import com.ideais.spring.exceptions.FreightException;
import com.ideais.spring.exceptions.FreightZipCodeException;

public interface FreightDaoBehavior {
	
	public FreightDetails getFreight(ItemsPackage itemsPackage, String destinationZipCode) throws HttpException, IOException, FreightException, FreightZipCodeException, JDOMException;
	
	public FreightDetails getFreight(ItemsPackage itemsPackage, String serviceType, String destinationZipCode) throws HttpException, IOException, FreightException, FreightZipCodeException, JDOMException;

}