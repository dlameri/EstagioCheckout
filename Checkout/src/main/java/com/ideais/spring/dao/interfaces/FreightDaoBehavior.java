package com.ideais.spring.dao.interfaces;

import com.ideais.spring.domain.FreightDetails;
import com.ideais.spring.domain.ItemsPackage;

public interface FreightDaoBehavior {
	
	public FreightDetails getFreight(ItemsPackage itemsPackage, String destinationZipCode) throws Exception;
	
	public FreightDetails getFreight(ItemsPackage itemsPackage, String serviceType, String destinationZipCode) throws Exception;

}