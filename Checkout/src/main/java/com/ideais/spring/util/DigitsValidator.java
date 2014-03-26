package com.ideais.spring.util;

public class DigitsValidator {
	
	private static final String DIGITS_REGEX = "[0-9]+";

	public static boolean validate(String input) {
		return input != null && input.matches(DIGITS_REGEX);
	}
	
}