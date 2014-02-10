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
	
	public static BigDecimal getFreightFromXmlString(String xml) throws Exception {
		SAXBuilder saxBuilder = new SAXBuilder();
		BigDecimal freight = BigDecimal.ZERO;
		
		try {
		    Document doc = saxBuilder.build(new StringReader(xml));
		    
		    Element rootNode = doc.getRootElement();    		    
			Namespace ns = rootNode.getNamespace();
			
			Element service = rootNode.getChild("Servicos", ns);
			Element cservice = service.getChild("cServico", ns);
						
			String errorMessage = cservice.getChildText("MsgErro", ns);
						    
			if (!errorMessage.equals("")) {			    	
		    	throw new Exception(errorMessage); //criar exceção própria
		    }
		    			    
	        freight = new BigDecimal(cservice.getChildText("Valor", ns).replace(',', '.')); 
			
			return freight;
		    
		} catch (JDOMException e) {
		    // handle JDOMException
		} catch (IOException e) {
		    // handle IOException
		}
		
		return freight;
	}
	
}