package com.ideais.spring.util;

import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class JsonUtil {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static Object readJsonToObject(String objectInJsonFormat, Class<?> classDomain) {
		try {
			return mapper.readValue(objectInJsonFormat, classDomain);
		} catch (JsonParseException e) {
			e.printStackTrace();
			return new Object();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return new Object();
		} catch (IOException e) {
			e.printStackTrace();
			return new Object();
		}
	}
	
	public static <T> Object readJsonToObject(String objectInJsonFormat, TypeReference<T> type){
	
		try {
			return mapper.readValue(objectInJsonFormat, type);
		} catch (JsonParseException e) {
			e.printStackTrace();
			return new Object();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return new Object();
		} catch (IOException e) {
			e.printStackTrace();
			return new Object();
		}
	}
	
	public static String writeObjectToJson(Object objectToParse){
		
		try {
			return mapper.writeValueAsString(objectToParse);
		} catch (JsonParseException e) {
			e.printStackTrace();
			return new String();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return new String();
		} catch (IOException e) {
			e.printStackTrace();
			return new String();
		}
	}

}