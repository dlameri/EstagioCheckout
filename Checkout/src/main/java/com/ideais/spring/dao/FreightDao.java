package com.ideais.spring.dao;

import java.math.BigDecimal;

import javax.ws.rs.core.GenericType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ideais.spring.dao.interfaces.FreightDaoBehavior;
import com.ideais.spring.domain.checkout.CorreiosCodes;
import com.ideais.spring.domain.checkout.FreightDetails;
import com.ideais.spring.domain.checkout.ItemsPackage;
import com.ideais.spring.util.XmlFreightParserUtil;

@Component("freightDao")
public class FreightDao extends BasicSoapClientDao implements FreightDaoBehavior{
	
	private final String EMPTY_STRING = "";
	private final String PARAMETER_RECEIVED_NOTIFICATION = "sCdAvisoRecebimento=";
	private final String PARAMETER_DECLARED_VALUE = "nVlValorDeclarado=";
	private final String PARAMETER_HAND_DELIVER = "sCdMaoPropria=";
	private final String PARAMETER_DIAMETER = "nVlDiametro=";
	private final String PARAMETER_WIDTH = "nVlLargura=";
	private final String PARAMETER_HEIGHT = "nVlAltura=";
	private final String PARAMETER_DEPTH = "nVlComprimento=";
	private final String PARAMETER_FORMAT = "nCdFormato=";
	private final String PARAMETER_WEIGHT = "nVlPeso=";
	private final String PARAMETER_ZIPCODE_DESTINY = "sCepDestino=";
	private final String PARAMETER_ZIPCODE_ORIGIN = "sCepOrigem=";
	private final String PARAMETER_CODE_SERVICE = "nCdServico=";
	private final String PARAMETER_STORE_PASSWORD = "sDsSenha=";
	private final String PARAMETER_STORE_CODE = "nCdEmpresa=";
	private final String PARAMETER_SEPARATOR = "&";
	private String freightPriceUrl = "CalcPreco?";
	private String freightDeliverytimeUrl = "CalcPrazo?";
	@Autowired
	private String freightIntegrationUrl;
	@Autowired
	private String storeZipCode;
	@Autowired
	private String defaultCdStore;
	@Autowired
	private String defaultPassword;
	@Autowired
	private String defaultServiceType;
	@Autowired
	private String handDeliver;
	@Autowired
	private String declaredValue;
	@Autowired
	private String receivedNotification;
	
	@Override
	public FreightDetails getFreight(ItemsPackage itemsPackage, String destinationZipCode) throws Exception {
		return getFreight(itemsPackage, EMPTY_STRING, destinationZipCode);
	}
	
	@Override
	public FreightDetails getFreight(ItemsPackage itemsPackage, String serviceType, String destinationZipCode) throws Exception {   
		FreightDetails freightDetails;		
	
		if (EMPTY_STRING.equals(serviceType) && itemsPackage.getVolumetricWeight() > CorreiosCodes.valueOf(defaultServiceType).getMaximumWeight()) {
			
			freightDetails = new FreightDetails(defaultServiceType, destinationZipCode, storeZipCode);
			freightDetails.setFreightValue(CorreiosCodes.valueOf(defaultServiceType).getMaximumFreight());
		
		} else if (!EMPTY_STRING.equals(serviceType) && itemsPackage.getVolumetricWeight() > CorreiosCodes.valueOf(serviceType).getMaximumWeight()) {
			
			freightDetails = new FreightDetails(serviceType, destinationZipCode, storeZipCode);
			freightDetails.setFreightValue(CorreiosCodes.valueOf(serviceType).getMaximumFreight());
		
		} else {
		   
			freightDetails = makeFreightValueRequestFromCorreios(serviceType, destinationZipCode, itemsPackage);
		}
		
	    freightDetails.setDeliveryDays(makeFreightDeiveryDaysRequestFromCorreios(serviceType, destinationZipCode));
		
        return freightDetails;
	}
	
	private String makeFreightDeiveryDaysRequestFromCorreios(String serviceType, String destinationZipCode) throws Exception {
		String url = buildFreightDaysUrl(serviceType, destinationZipCode);  			
		String response = (String) client.get(url, new GenericType<String>() {});		
				
		if (response == null || EMPTY_STRING.equals(response)) {
			throw new Exception("erro na request, bad request!"); //criar propria exceção
		}
		
		return XmlFreightParserUtil.getFreightDaysFromXmlString(response);
	}

	private String buildFreightDaysUrl(String serviceType, String destinationZipCode) {
		String request = freightIntegrationUrl + freightDeliverytimeUrl;
		
		request += buildServiceTypeProperties(serviceType);
		request += buildZipCodesPropertiesForDeliveryDays(destinationZipCode);
		
		return request;
	}

	private FreightDetails makeFreightValueRequestFromCorreios(String serviceType, String destinationZipCode, ItemsPackage itemsPackage) throws Exception {
		String url = buildFreightValueUrl(itemsPackage, serviceType, destinationZipCode);    			
        String response = (String) client.get(url, new GenericType<String>() {});	
		
		if (response == null || EMPTY_STRING.equals(response)) {
			throw new Exception("erro na request, bad request!"); //criar propria exceção
		}
        
		return XmlFreightParserUtil.getFreightFromXmlString(serviceType, destinationZipCode, storeZipCode, response);
	}

	private String buildFreightValueUrl(ItemsPackage itemsPackage, String serviceType, String destinationZipCode) {
		String request = freightIntegrationUrl + freightPriceUrl;
		
		request += buildStoreProperties();
		request += buildServiceTypeProperties(serviceType);
		request += buildZipCodesProperties(destinationZipCode);
		request += buildPackageProperties(itemsPackage);
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

	private String buildPackageProperties(ItemsPackage itemsPackage) {
		String request = "";
		
		request += buildPackageWeight(itemsPackage);
		request += buildPackageFormat(itemsPackage);
		request += buildPackageDepth(itemsPackage);
		request += buildPackageHeight(itemsPackage);
		request += buildPackageWidth(itemsPackage);
		request += buildPackageDiameter(itemsPackage);
		
		return request;
	}

	private String buildPackageDiameter(ItemsPackage itemsPackage) {
		return PARAMETER_DIAMETER + itemsPackage.getDefaultDiameter() + PARAMETER_SEPARATOR;
	}

	private String buildPackageWidth(ItemsPackage itemsPackage) {
		return PARAMETER_WIDTH + itemsPackage.getDefaultWidth() + PARAMETER_SEPARATOR;
	}

	private String buildPackageHeight(ItemsPackage itemsPackage) {
		return PARAMETER_HEIGHT + itemsPackage.getDefaultHeight() + PARAMETER_SEPARATOR;
	}

	private String buildPackageDepth(ItemsPackage itemsPackage) {
		return PARAMETER_DEPTH + itemsPackage.getDefaultDepth() + PARAMETER_SEPARATOR;
	}

	private String buildPackageFormat(ItemsPackage itemsPackage) {
		return PARAMETER_FORMAT + itemsPackage.getDefaultFormat() + PARAMETER_SEPARATOR;
	}

	private String buildPackageWeight(ItemsPackage itemsPackage) {
		return PARAMETER_WEIGHT + itemsPackage.getVolumetricWeight() + PARAMETER_SEPARATOR;
	}

	private String buildZipCodesProperties(String destinationZipCode) {
		String request = "";
		
		request += PARAMETER_ZIPCODE_ORIGIN + storeZipCode + PARAMETER_SEPARATOR;
		request += PARAMETER_ZIPCODE_DESTINY + destinationZipCode + PARAMETER_SEPARATOR;
		
		return request;
	}
	
	private String buildZipCodesPropertiesForDeliveryDays(String destinationZipCode) {
		String request = "";
		
		request += PARAMETER_ZIPCODE_ORIGIN + storeZipCode + PARAMETER_SEPARATOR;
		request += PARAMETER_ZIPCODE_DESTINY + destinationZipCode;
		
		return request;
	}

	private String buildServiceTypeProperties(String serviceType) {
		if (EMPTY_STRING.equals(serviceType)) {
			return PARAMETER_CODE_SERVICE + CorreiosCodes.valueOf(defaultServiceType).getFreightServiceCode() + PARAMETER_SEPARATOR;
		} 
		
		return PARAMETER_CODE_SERVICE + serviceType + PARAMETER_SEPARATOR;
	}

	private String buildStoreProperties() {
		String request = "";

		request += PARAMETER_STORE_CODE + defaultCdStore + PARAMETER_SEPARATOR;
		request += PARAMETER_STORE_PASSWORD + defaultPassword + PARAMETER_SEPARATOR;
		
		return request;
	}
	
}