package com.ideais.spring.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ValueFormatter {
	
	public static String format(BigDecimal value) {
		Locale local = new Locale("pt", "BR");
		DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(local));
		
		return df.format(value);
	}
	
}