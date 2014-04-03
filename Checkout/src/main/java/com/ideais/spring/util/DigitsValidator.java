package com.ideais.spring.util;

public class DigitsValidator {
	
	private static final String ZIP_CODE_REGEX = "[0-9]+";
	private static final String QUANTITY_REGEX = "[1-9]+";

	public static boolean validateZipCodeInput(String input) {
		return input != null && input.matches(ZIP_CODE_REGEX);
	}
	
	public static boolean validateQuantityInput(String input) {
		return input != null && input.matches(QUANTITY_REGEX);
	}
	
}