package com.ideais.spring.domain.checkout;

import java.util.List;

import com.ideais.spring.exceptions.ItemPackageDimensionException;
import com.ideais.spring.exceptions.ItemPackageVolumeException;
import com.ideais.spring.exceptions.ItemPackageWeightException;

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
		
	public ItemsPackage(List<ShoppingCartLine> shoppingCartlines) throws ItemPackageWeightException, ItemPackageVolumeException, ItemPackageDimensionException {
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

	private void calculateVolumetricWeight(List<ShoppingCartLine> shoppingCartLines) throws ItemPackageWeightException, ItemPackageVolumeException, ItemPackageDimensionException {
		Integer weight = 0;
		
		for (int i = 0; i < shoppingCartLines.size(); i++) {
			Integer itemVolumetricWeight = getItemVolumetricWeight(shoppingCartLines.get(i).getItem());	
			weight += getValueToIncrementWeight(itemVolumetricWeight, shoppingCartLines.get(i));	
		}
		
		volumetricWeight = weight / 50;
	}
	
	private Integer getItemVolumetricWeight(Item item) throws ItemPackageVolumeException, ItemPackageDimensionException {
		Dimensions dimensions = item.getDimensions();
		
		if (dimensions != null && checkDimension(dimensions)) {
			return (int) ((dimensions.getHeight() * dimensions.getWidth() * dimensions.getDepth()) / WEIGHT_CONSTANT); 
		}
		
		throw new ItemPackageVolumeException("Volumedo pacote se encontra nulo");
	}
	
	private boolean checkDimension(Dimensions dimensions) throws ItemPackageDimensionException {
		Integer sumOfDimensions = (int) (dimensions.getHeight() + dimensions.getWidth() + dimensions.getDepth());
		
		if (sumOfDimensions >= MINIMUM_SUM_OF_DIMENSIONS && sumOfDimensions <= MAXIMUM_SUM_OF_DIMENSIONS) {
			return true;
		}
		
		throw new ItemPackageDimensionException("Dimensão inapropriada do pacote");
	}

	private Integer getValueToIncrementWeight(Integer itemVolumetricWeight, ShoppingCartLine shoppingCartLine) throws ItemPackageWeightException {
		if (itemVolumetricWeight > MINIMUM_VOLUME) {
			return (itemVolumetricWeight) * shoppingCartLine.getQuantity();
		}
		
		return calculateShoppingCartLineWeight(shoppingCartLine);
	}
	
	private Integer calculateShoppingCartLineWeight(ShoppingCartLine shoppingCartLine) throws ItemPackageWeightException {
		if (shoppingCartLine.getItem().getWeight() == null) {
			throw new ItemPackageWeightException("Peso do pacote se encontra nulo");
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