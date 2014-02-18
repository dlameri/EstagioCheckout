package com.ideais.spring.dao;

import java.io.IOException;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import com.ideais.spring.dao.domain.checkout.stock.Item;
import com.ideais.spring.util.XmlFreightParserUtil;
import org.apache.commons.httpclient.methods.GetMethod;

@Component("freightXmlDao")
public class FreightXmlDao {
	
	private static final String EMPTY_STRING = "";
	private static final String PARAMETER_RECEIVED_NOTIFICATION = "sCdAvisoRecebimento=";
	private static final String PARAMETER_DECLARED_VALUE = "nVlValorDeclarado=";
	private static final String PARAMETER_HAND_DELIVER = "sCdMaoPropria=";
	private static final String PARAMETER_DIAMETER = "nVlDiametro=";
	private static final String PARAMETER_WIDTH = "nVlLargura=";
	private static final String PARAMETER_HEIGHT = "nVlAltura=";
	private static final String PARAMETER_DEPTH = "nVlComprimento=";
	private static final String PARAMETER_FORMAT = "nCdFormato=";
	private static final String PARAMETER_WEIGHT = "nVlPeso=";
	private static final String PARAMETER_ZIPCODE_DESTINY = "sCepDestino=";
	private static final String PARAMETER_ZIPCODE_ORIGIN = "sCepOrigem=";
	private static final String PARAMETER_CODE_SERVICE = "nCdServico=";
	private static final String PARAMETER_STORE_PASSWORD = "sDsSenha=";
	private static final String PARAMETER_STORE_CODE = "nCdEmpresa=";
	private static final String PARAMETER_SEPARATOR = "&";
	@Autowired
	private String freightIntegrationUrl;
	@Autowired
	private Integer packageExtraSize;
	@Autowired
	private String storeZipCode;
	@Autowired
	private String defaultCdStore;
	@Autowired
	private String defaultPassword;
	@Autowired
	private String defaultServiceType;
	@Autowired
	private String defaultFormat;
	@Autowired
	private Integer defaultDepth;
	@Autowired
	private Integer defaultHeight;
	@Autowired
	private Integer defaultWidth;
	@Autowired
	private Integer defaultDiameter;
	@Autowired
	private String handDeliver;
	@Autowired
	private String declaredValue;
	@Autowired
	private String receivedNotification;
	
	public BigDecimal getFreight(Item item, String destinationZipCode) throws Exception {
		return getFreight(item, EMPTY_STRING, destinationZipCode);
	}
	
	public BigDecimal getFreight(Item item, String serviceType, String destinationZipCode) throws Exception {
		HttpClient client = new HttpClient();
		String request = buildFreightUrl(item, serviceType, destinationZipCode);
    			
        GetMethod method = new GetMethod(request);     
        client.executeMethod(method);
                        
        return  XmlFreightParserUtil.getFreightFromXmlString(method.getResponseBodyAsString());
	}

	private String buildFreightUrl(Item item, String serviceType, String destinationZipCode) {
		String request = freightIntegrationUrl;
		
		request += buildStoreProperties();
		request += buildServiceTypeProperties(serviceType);
		request += buildZipCodesProperties(destinationZipCode);
		request += buildSkuProperties(item);
		request += buildExtraInfo();
		
		return request;
	}

	private String buildExtraInfo() {
		String request = "";
		
		request += PARAMETER_HAND_DELIVER + handDeliver + PARAMETER_SEPARATOR;
		request += PARAMETER_DECLARED_VALUE + declaredValue + PARAMETER_SEPARATOR;
		request += PARAMETER_RECEIVED_NOTIFICATION + receivedNotification;
		
		return request;
	}

	private String buildSkuProperties(Item item) {
		String request = "";
		
		request += buildSkuWeight(item);
		request += buildSkuFormat();
		request += buildSkuDepth(item);
		request += buildSkuHeight(item);
		request += buildSkuWidth(item);
		request += buildSkuDiameter(request);
		
		return request;
	}

	private String buildSkuDiameter(String request) {
		return PARAMETER_DIAMETER + defaultDiameter + PARAMETER_SEPARATOR;
	}

	private String buildSkuWidth(Item item) {
		if (item.getProduct().getDimensions().getWidth() < defaultWidth) {
			return PARAMETER_WIDTH + defaultWidth + PARAMETER_SEPARATOR;
		}
		
		return PARAMETER_WIDTH + (item.getProduct().getDimensions().getWidth() + packageExtraSize) + PARAMETER_SEPARATOR;
	}

	private String buildSkuHeight(Item item) {
		if (item.getProduct().getDimensions().getHeight() < defaultHeight) {
			return PARAMETER_HEIGHT + defaultHeight + PARAMETER_SEPARATOR;
		} 
		
		return PARAMETER_HEIGHT + (item.getProduct().getDimensions().getHeight() + packageExtraSize) + PARAMETER_SEPARATOR;

	}

	private String buildSkuDepth(Item item) {
		if (item.getProduct().getDimensions().getDepth() < defaultDepth) {
			return PARAMETER_DEPTH + defaultDepth + PARAMETER_SEPARATOR;
		} 
		
		return PARAMETER_DEPTH + (item.getProduct().getDimensions().getDepth() + packageExtraSize) +  PARAMETER_SEPARATOR; 
	}

	private String buildSkuFormat() {
		return PARAMETER_FORMAT + defaultFormat + PARAMETER_SEPARATOR;
	}

	private String buildSkuWeight(Item item) {
		return PARAMETER_WEIGHT + item.getProduct().getWeight() + PARAMETER_SEPARATOR;
	}

	private String buildZipCodesProperties(String destinationZipCode) {
		String request = "";
		
		request += PARAMETER_ZIPCODE_ORIGIN + storeZipCode + PARAMETER_SEPARATOR;
		request += PARAMETER_ZIPCODE_DESTINY + destinationZipCode + PARAMETER_SEPARATOR;
		
		return request;
	}

	private String buildServiceTypeProperties(String serviceType) {
		if (EMPTY_STRING.equals(serviceType)) {
			return PARAMETER_CODE_SERVICE + defaultServiceType + PARAMETER_SEPARATOR;
		} 
		
		return PARAMETER_CODE_SERVICE + serviceType + PARAMETER_SEPARATOR;
	}

	private String buildStoreProperties() {
		String request = "";

		request += PARAMETER_STORE_CODE + defaultCdStore + PARAMETER_SEPARATOR;
		request += PARAMETER_STORE_PASSWORD + defaultPassword + PARAMETER_SEPARATOR;
		
		return request;
	}
	
	public Integer getDeliverDays(String xml) {
		return null;	
	}
	
}