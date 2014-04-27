package com.ideais.spring.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class FreightDetailsTest {

	FreightDetails freight = new FreightDetails();	
	
	@Test
	public void check_if_message_appears_when_it_has_no_delivery_days() {
		assertEquals("Digite o CEP acima para calcular o prazo de entrega.", freight.getDeliveryDays());
	}
	
	@Test
	public void check_format_with_delivery_days_equals_1() {
		freight.setDeliveryDays("1");
		assertEquals("1 dia útil.", freight.getDeliveryDays());
	}
	
	@Test
	public void check_format_with_delivery_days_higher_than_1() {
		freight.setDeliveryDays("2");
		assertEquals("2 dias úteis.", freight.getDeliveryDays());
	}
	
	@Test
	public void check_if_freight_was_not_calculated_with_delivery_days_equals_null() {
		freight.setStoreZipCode("26600000");
		freight.setServiceType("PAX");
		freight.setDestinationZipCode("26600000");
		assertFalse(freight.wasCalculated());
	}

	@Test
	public void check_if_freight_was_not_calculated_with_store_zip_code_equals_null() {
		freight.setDeliveryDays("2");
		freight.setServiceType("PAX");
		freight.setDestinationZipCode("26600000");
		assertFalse(freight.wasCalculated());
	}

	@Test
	public void check_if_freight_was_not_calculated_with_service_type_equals_null() {
		freight.setDeliveryDays("2");
		freight.setStoreZipCode("26600000");
		freight.setDestinationZipCode("26600000");
		assertFalse(freight.wasCalculated());
	}

	@Test
	public void check_if_freight_was_not_calculated_with_destination_zip_code_equals_null() {
		freight.setDeliveryDays("2");
		freight.setStoreZipCode("26600000");
		freight.setServiceType("PAX");
		assertFalse(freight.wasCalculated());
	}
	
	@Test
	public void check_if_freight_was_not_calculated_with_all_info_equals_null() {
		assertFalse(freight.wasCalculated());
	}
	
	@Test
	public void check_if_freight_was_calculated_with_all_info() {
		freight.setDeliveryDays("2");
		freight.setStoreZipCode("26600000");
		freight.setServiceType("PAX");
		freight.setDestinationZipCode("26600000");
		assertTrue(freight.wasCalculated());	
	}
}