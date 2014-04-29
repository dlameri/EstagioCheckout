package com.ideais.spring.dao.interfaces;

import com.ideais.spring.domain.checkout.FreightDetails;
import com.ideais.spring.domain.checkout.ItemsPackage;

public interface FreightDaoBehavior {
	
	public FreightDetails getFreight(ItemsPackage itemsPackage, String destinationZipCode) throws Exception;
	
	public FreightDetails getFreight(ItemsPackage itemsPackage, String serviceType, String destinationZipCode) throws Exception;

}