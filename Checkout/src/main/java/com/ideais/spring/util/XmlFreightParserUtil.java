package com.ideais.spring.util;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

import com.ideais.spring.domain.checkout.CorreiosCodes;
import com.ideais.spring.domain.checkout.FreightDetails;
import com.ideais.spring.exceptions.FreightException;
import com.ideais.spring.exceptions.FreightZipCodeException;

public class XmlFreightParserUtil {
	
	private static final String EMPTY_STRING = "";
	private static final String ERROR_NODE = "MsgErro";
	private static final String GRAND_CHILD_NODE = "cServico";
	private static final String CHILD_NODE = "Servicos";
	private static final String VALUE_NODE = "Valor";
	private static final String DELIVERY_TIME_NODE = "PrazoEntrega";

	public static FreightDetails getFreightFromXmlString(String serviceType, String destinationZipCode, String storeZipCode, String xml) throws FreightZipCodeException {		
		try {
			FreightDetails freightDetails = new FreightDetails(serviceType, destinationZipCode, storeZipCode);
			freightDetails.setFreightValue(new BigDecimal(getFreightValue(serviceType, xml)));
			
			return freightDetails; 	    
		} catch (JDOMException e) {
		    // handle JDOMException
		} catch (IOException e) {
		    // handle IOException
		}
		
		return null;
	}
	
	private static String getFreightValue(String serviceType, String xml) throws FreightZipCodeException, JDOMException, IOException {
		SAXBuilder saxBuilder = new SAXBuilder();
	    Document doc = saxBuilder.build(new StringReader(xml));
	    
	    Element rootNode = doc.getRootElement();    		    
		Namespace ns = rootNode.getNamespace();		
		Element cService = rootNode.getChild(CHILD_NODE, ns).getChild(GRAND_CHILD_NODE, ns);
								
		return getValueFromNode(serviceType, cService, ns);
	}
	
	private static String getValueFromNode(String serviceType, Element cService, Namespace ns) throws FreightZipCodeException {
		String errorMessage = cService.getChildText(ERROR_NODE, ns);
	    
		if (!EMPTY_STRING.equals(errorMessage) && errorMessage.contains("CEP")) {			    	
	    	throw new FreightZipCodeException(errorMessage); 
	    } else if(!EMPTY_STRING.equals(errorMessage)) {
	    	return CorreiosCodes.valueOf(serviceType).getDefaultFreight().toString();
	    }
		
		return cService.getChildText(VALUE_NODE, ns).replace(',', '.');
	}
	
	public static String getFreightDaysFromXmlString(String serviceType, String xml) throws FreightZipCodeException, JDOMException, IOException {
		SAXBuilder saxBuilder = new SAXBuilder();
	    Document doc = saxBuilder.build(new StringReader(xml));
	    
	    Element rootNode = doc.getRootElement();    		    
		Namespace ns = rootNode.getNamespace();		
		Element cService = rootNode.getChild(CHILD_NODE, ns).getChild(GRAND_CHILD_NODE, ns);
								
		return getDaysFromNode(serviceType, cService, ns);
	}
	
	private static String getDaysFromNode(String serviceType, Element cService, Namespace ns) throws FreightZipCodeException {
		String errorMessage = cService.getChildText(ERROR_NODE, ns);
	    
		if (!EMPTY_STRING.equals(errorMessage) && errorMessage.contains("CEP")) {			    	
	    	throw new FreightZipCodeException(errorMessage); 
	    } else if (!EMPTY_STRING.equals(errorMessage)){
	    	return CorreiosCodes.valueOf(serviceType).getDefaultDays();
	    }

		return cService.getChildText(DELIVERY_TIME_NODE, ns);
	}
	
}