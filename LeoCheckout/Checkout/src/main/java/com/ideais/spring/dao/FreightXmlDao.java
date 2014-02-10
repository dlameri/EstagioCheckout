package com.ideais.spring.dao;

import java.io.IOException;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import com.ideais.spring.dao.domain.catalog.Sku;
import com.ideais.spring.util.XmlFreightParserUtil;
import org.apache.commons.httpclient.methods.GetMethod;

@Component("freightXmlDao")
public class FreightXmlDao {
	
	@Autowired
	private String freightIntegrationUrl;
	@Autowired
	private String storeZipCode;
	@Autowired
	private String defaultCdStore;
	@Autowired
	private String defaultPassword;
	//serviço PAC como default
	@Autowired
	private String defaultServiceType;
	@Autowired
	private String defaultFormat;
	@Autowired
	private Integer defaultDeph;
	@Autowired
	private Integer defaultHeight;
	@Autowired
	private Integer defaultWidth;
	@Autowired
	private Integer defaultDiameter;
	@Autowired
	private String inHand;
	@Autowired
	private String declaredValue;
	@Autowired
	private String returnReceipt;
	
	public BigDecimal getFreight(Sku sku, String destinationZipCode) throws Exception {
		return getFreight(sku, "", destinationZipCode);
	}
	
	public BigDecimal getFreight(Sku sku, String serviceType, String destinationZipCode) throws Exception {
		HttpClient client = new HttpClient();
		String request = freightIntegrationUrl;
		
		request += "nCdEmpresa=" + defaultCdStore + "&";
		request += "sDsSenha=" + defaultPassword + "&";
		
		if (serviceType.equals("")) {
			request += "nCdServico=" + defaultServiceType + "&";
		} else {
			request += "nCdServico=" + serviceType + "&";
		}
		
		request += "sCepOrigem=" + storeZipCode + "&";
		request += "sCepDestino=" + destinationZipCode + "&";
		request += "nVlPeso=" + sku.getWeight() + "&";
		request += "nCdFormato=" + defaultFormat + "&";
		
		if (sku.getDepth() < defaultDeph) {
			request += "nVlComprimento=" + defaultDeph + "&";
		} else {
			//comprimento do produto + 1 cm da caixa da embalagem
			request += "nVlComprimento=" + (sku.getDepth() + 1) +  "&"; 
		}
		if (sku.getHeight() < defaultHeight) {
			request += "nVlAltura=" + defaultHeight + "&";
		} else {
			//altura do produto + 1cm da caixa da embalagem
			request += "nVlAltura=" + (sku.getHeight() + 1) + "&";
		}
		if (sku.getWidth() < defaultWidth) {
			request += "nVlLargura=" + defaultWidth + "&";
		} else {
			//largura do produto + 1cm da caixa da embalagem
			request += "nVlLargura=" + sku.getWidth() + "&";
		}
		
		request += "nVlDiametro=" + defaultDiameter + "&";
		request += "sCdMaoPropria=" + inHand + "&";
		request += "nVlValorDeclarado=" + declaredValue + "&";
		request += "sCdAvisoRecebimento=" + returnReceipt;
    			
        GetMethod method = new GetMethod(request);
        
        int statusCode = client.executeMethod(method);
                        
        return  XmlFreightParserUtil.getFreightFromXmlString(method.getResponseBodyAsString());
	}
	
	public Integer getDeliverDays(String xml) {
		return null;	
	}
	
}