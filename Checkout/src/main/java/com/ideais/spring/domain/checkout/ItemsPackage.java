package com.ideais.spring.domain.checkout;

import java.util.List;

public class ItemsPackage {
	
	private static final int MAXIMUM_SUM_OF_DIMENSIONS = 200;
	private static final int MINIMUM_SUM_OF_DIMENSIONS = 29;
	private final String DEFAULT_FORMAT = "1";
	private final Integer DEFAULT_DEPTH = 16;
	private final Integer DEFAULT_HEIGHT = 2;
	private final Integer DEFAULT_WIDTH = 11;
	private Integer DEFAULT_DIAMETER = 2;
	private Integer volumetricWeight = 1;
	private final Integer WEIGHT_CONSTANT = 6000;
	private final Integer MINIMUM_VOLUME = 5;
	private Integer quantityOfItems = 0;
		
	public ItemsPackage(List<ShoppingCartLine> shoppingCartlines) {
		calculateVolumetricWeight(shoppingCartlines);
		this.quantityOfItems = calculateTotalOfItems(shoppingCartlines);
	}
	
	private Integer calculateTotalOfItems(List<ShoppingCartLine> shoppingCartlines) {
		Integer totalOfItems = 0;
		
		for (int i = 0; i < shoppingCartlines.size(); i++) {
			totalOfItems += shoppingCartlines.get(i).getQuantity();
		}
		
		return totalOfItems;
		
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
		Dimensions dimensions = item.getDimensions();
		
		if (dimensions != null && checkDimension(dimensions)) {
			return (int) ((dimensions.getHeight() * dimensions.getWidth() * dimensions.getDepth()) / WEIGHT_CONSTANT); 
		}
		
		return MINIMUM_VOLUME; //jogar exceção de nulo
	}
	
	private boolean checkDimension(Dimensions dimensions) {
		Integer sumOfDimensions = (int) (dimensions.getHeight() + dimensions.getWidth() + dimensions.getDepth());
		
		if (sumOfDimensions >= MINIMUM_SUM_OF_DIMENSIONS && sumOfDimensions <= MAXIMUM_SUM_OF_DIMENSIONS) {
			return true;
		}
		
		return false; //jogar exceção de dimensão inapropriado
	}

	private Integer getValueToIncrementWeight(Integer itemVolumetricWeight, ShoppingCartLine shoppingCartLine) {
		if (itemVolumetricWeight > MINIMUM_VOLUME) {
			return (itemVolumetricWeight) * shoppingCartLine.getQuantity();
		}
		
		return calculateShoppingCartLineWeight(shoppingCartLine);
	}
	
	private Integer calculateShoppingCartLineWeight(ShoppingCartLine shoppingCartLine) {
		if (shoppingCartLine.getItem().getWeight() == null) {
			return 0; //jogar exceção de peso nulo
		}
		
		return shoppingCartLine.getItem().getWeight() * shoppingCartLine.getQuantity();
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

	public Integer getQuantityOfItems() {
		return quantityOfItems;
	}

	public void setQuantityOfItems(Integer quantityOfItems) {
		this.quantityOfItems = quantityOfItems;
	}

	public void setVolumetricWeight(Integer volumetricWeight) {
		this.volumetricWeight = volumetricWeight;
	}

}