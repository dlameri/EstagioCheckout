package com.ideais.spring.dao.domain.checkout;

import java.util.List;
import com.ideais.spring.dao.domain.checkout.stock.Dimensions;
import com.ideais.spring.dao.domain.checkout.stock.Item;

public class ItemsPackage {
	
	private final String DEFAULT_FORMAT = "1";
	private final Integer DEFAULT_DEPTH = 16;
	private final Integer DEFAULT_HEIGHT = 2;
	private final Integer DEFAULT_WIDTH = 11;
	private Integer DEFAULT_DIAMETER = 2;
	private Integer volumetricWeight = 1;
	private final Integer WEIGHT_CONSTANT = 6000;
	private final Integer MINIMUM_VOLUME = 5;
		
	public ItemsPackage(List<ShoppingCartLine> shoppingCartlines) {
		calculateVolumetricWeight(shoppingCartlines);
	}
	
	private void calculateVolumetricWeight(List<ShoppingCartLine> shoppingCartLines) {
		Integer weight = 0;
		
		for (int i = 0; i < shoppingCartLines.size(); i++) {
			Integer itemVolumetricWeight = getItemVolumetricWeight(shoppingCartLines.get(i).getItem());	
			weight += getValueToIncrementWeight(itemVolumetricWeight, shoppingCartLines.get(i));	
		}
		
		volumetricWeight = weight;
	}
	
	private Integer getItemVolumetricWeight(Item item) {
		Dimensions dimensions = item.getProduct().getDimensions();
		
		if (dimensions != null) {
			return (dimensions.getHeight() * dimensions.getWidth() * dimensions.getDepth()) / WEIGHT_CONSTANT; 
		}
		
		return MINIMUM_VOLUME;
	}
	
	private Integer getValueToIncrementWeight(Integer itemVolumetricWeight, ShoppingCartLine shoppingCartLine) {
		if (itemVolumetricWeight > MINIMUM_VOLUME) {
			return (itemVolumetricWeight) * shoppingCartLine.getQuantity();
		}
		
		return shoppingCartLine.getItem().getProduct().getWeight() * shoppingCartLine.getQuantity();
	}

	public String getDefaultFormat() {
		return DEFAULT_FORMAT;
	}

	public Integer getDefaultDepth() {
		return DEFAULT_DEPTH;
	}

	public Integer getDefaultHeight() {
		return DEFAULT_HEIGHT;
	}

	public Integer getDefaultWidth() {
		return DEFAULT_WIDTH;
	}

	public Integer getDefaultDiameter() {
		return DEFAULT_DIAMETER;
	}

	public Integer getVolumetricWeight() {
		return volumetricWeight;
	}

}