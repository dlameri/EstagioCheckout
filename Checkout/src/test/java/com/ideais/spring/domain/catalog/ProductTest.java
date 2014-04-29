package com.ideais.spring.domain.catalog;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.ideais.spring.domain.stock.json.ProductJSON;

public class ProductTest {

	@Test
	public void check_if_product_is_created_correctly() {
		ProductJSON productJSON = mock(ProductJSON.class);
		
		when(productJSON.getId()).thenReturn(1L);
		when(productJSON.getName()).thenReturn("iPod");
		when(productJSON.getLongDescription()).thenReturn("Existe fino e leve. E existe fino e leve de um jeito totalmente diferente. O iPod touch tem agora uma estrutura de alumínio ultrafina que não pesa quase nada na sua mão nem no seu bolso. As cores são tão atraentes que você não vai saber qual delas escolher (mas vai ter que escolher uma). Com os novos Apple EarPods, o som do iPod touch ficou tão perfeito quanto a sua aparência. E agora com o iPod touch loop é que você não vai conseguir largar dele mesmo. ");
		when(productJSON.getShortDescription()).thenReturn("Pensado com diversão na cabeça.");
		when(productJSON.getWeight()).thenReturn(90);
		when(productJSON.getWarranty()).thenReturn(12);
		when(productJSON.getBrand()).thenReturn("Apple");
		when(productJSON.getModel()).thenReturn("Touch 64GB");
		when(productJSON.getActive()).thenReturn(true);
		when(productJSON.getRank()).thenReturn(1);
		
		Product product = new Product(productJSON);
	
		assertEquals(product.getId(),productJSON.getId());
		assertEquals(product.getName(),productJSON.getName());
		assertEquals(product.getLongDescription(),productJSON.getLongDescription());
		assertEquals(product.getShortDescription(),productJSON.getShortDescription());
		assertEquals(product.getWeight(),productJSON.getWeight());
		assertEquals(product.getWarranty(),productJSON.getWarranty());
		assertEquals(product.getBrand(),productJSON.getBrand());
		assertEquals(product.getModel(),productJSON.getModel());
		assertEquals(product.getActive(),productJSON.getActive());
		assertEquals(product.getRank(),productJSON.getRank());		
	}
}
