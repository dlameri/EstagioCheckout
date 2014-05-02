package com.ideais.spring.domain.checkout;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ideais.spring.domain.checkout.Dimensions;
import com.ideais.spring.domain.checkout.Item;
import com.ideais.spring.domain.checkout.ItemsPackage;
import com.ideais.spring.domain.checkout.ShoppingCartLine;
import com.ideais.spring.exceptions.ItemPackageDimensionException;
import com.ideais.spring.exceptions.ItemPackageVolumeException;
import com.ideais.spring.exceptions.ItemPackageWeightException;

public class ItemsPackageTest {	

	ShoppingCartLine shoppingCartLine = mock(ShoppingCartLine.class);
	List<ShoppingCartLine> shoppingCartlines = new ArrayList<ShoppingCartLine>();
	Dimensions dimensions = mock(Dimensions.class);
	Item item = mock(Item.class);
		
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void check_if_volumetric_weight_equals_0_when_shopping_cart_line_list_is_empty() {
		ItemsPackage itemspackage = null;
		
		try {
			itemspackage = new ItemsPackage(shoppingCartlines);
		} catch (ItemPackageWeightException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ItemPackageVolumeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ItemPackageDimensionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(new Integer(0),itemspackage.getVolumetricWeight());
	}
	
	@Test
	public void check_volumetric_weight_when_dimensions_checks_and_itemVolumetricWeight_is_greater_than_the_minimum_volume() {
		when(dimensions.getDepth()).thenReturn(60.0);
		when(dimensions.getHeight()).thenReturn(60.0);
		when(dimensions.getWidth()).thenReturn(60.0);
		
		when(item.getWeight()).thenReturn(8);
		when(item.getDimensions()).thenReturn(dimensions);

		when(shoppingCartLine.getItem()).thenReturn(item);
		
		ShoppingCartLine shoppingCartLine = new ShoppingCartLine(item);
		shoppingCartlines.add(shoppingCartLine);
		
		ItemsPackage itemspackage = null;
		
		try {
			itemspackage = new ItemsPackage(shoppingCartlines);
		} catch (ItemPackageWeightException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ItemPackageVolumeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ItemPackageDimensionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(new Integer(36), itemspackage.getVolumetricWeight());
	}
	
	@Test
	public void check_volumetric_weight_when_dimensions_checks_and_itemVolumetricWeight_is_not_greater_than_the_minimum_volume() {		
		when(dimensions.getDepth()).thenReturn(20.0);
		when(dimensions.getHeight()).thenReturn(20.0);
		when(dimensions.getWidth()).thenReturn(20.0);
		
		when(item.getWeight()).thenReturn(8);
		when(item.getDimensions()).thenReturn(dimensions);

		when(shoppingCartLine.getItem()).thenReturn(item);
		
		ShoppingCartLine shoppingCartLine = new ShoppingCartLine(item);
		shoppingCartlines.add(shoppingCartLine);
		
		ItemsPackage itemspackage = null;
		
		try {
			itemspackage = new ItemsPackage(shoppingCartlines);
		} catch (ItemPackageWeightException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ItemPackageVolumeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ItemPackageDimensionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(new Integer(8), itemspackage.getVolumetricWeight());
	}

	@Test
	public void check_volumetric_weight_when_dimensions_do_not_check() {		
		when(dimensions.getDepth()).thenReturn(10.0);
		when(dimensions.getHeight()).thenReturn(9.0);
		when(dimensions.getWidth()).thenReturn(8.0);
		
		when(item.getWeight()).thenReturn(8);
		when(item.getDimensions()).thenReturn(dimensions);

		when(shoppingCartLine.getItem()).thenReturn(item);
		
		ShoppingCartLine shoppingCartLine = new ShoppingCartLine(item);
		shoppingCartlines.add(shoppingCartLine);
		
		ItemsPackage itemspackage = null;
		try {
			itemspackage = new ItemsPackage(shoppingCartlines);
		} catch (ItemPackageWeightException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ItemPackageVolumeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ItemPackageDimensionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(new Integer(8), itemspackage.getVolumetricWeight());
	}
}