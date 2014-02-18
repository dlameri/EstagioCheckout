package com.ideais.spring.util;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

public class XmlFreightParserUtil {
	
	private static final String ERROR_NODE = "MsgErro";
	private static final String GRAND_CHILD_NODE = "cServico";
	private static final String CHILD_NODE = "Servicos";

	public static BigDecimal getFreightFromXmlString(String xml) throws Exception {
		SAXBuilder saxBuilder = new SAXBuilder();
		
		try {
		    Document doc = saxBuilder.build(new StringReader(xml));
		    
		    Element rootNode = doc.getRootElement();    		    
			Namespace ns = rootNode.getNamespace();		
			Element cservice = rootNode.getChild(CHILD_NODE, ns).getChild(GRAND_CHILD_NODE);
									
			String errorMessage = cservice.getChildText(ERROR_NODE, ns);
						    
			if (!"".equals(errorMessage)) {			    	
		    	throw new Exception(errorMessage); //criar exceção própria
		    }
			
			return new BigDecimal(cservice.getChildText("Valor", ns).replace(',', '.')); 
		    
		} catch (JDOMException e) {
		    // handle JDOMException
		} catch (IOException e) {
		    // handle IOException
		}
		
		return null;
	}
	
}