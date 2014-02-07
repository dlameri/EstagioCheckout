package com.ideais.spring.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.JSONException;

public class JsonReaderDaoUtil {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    
    return sb.toString();
  }

  public static String readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      
      return readAll(rd);
    } finally {
      is.close();
    }
  }

}