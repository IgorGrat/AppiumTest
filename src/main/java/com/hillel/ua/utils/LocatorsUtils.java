package com.hillel.ua.utils;

import java.io.*;
import java.util.Properties;

public class LocatorsUtils {
  
  private static LocatorsUtils  LocatorsUtils;
  private static Properties locatorsProperties;
  private static final String FILE_PROPERTY="locatorAndroid.properties";
//  private static final String FILE_PROPERTY="locator.properties";
  
  private LocatorsUtils() {
    locatorsProperties=new Properties();
        InputStream inputStream=getClass()
        .getClassLoader()
        .getResourceAsStream(FILE_PROPERTY);
    try {
      locatorsProperties.load(inputStream);
    } catch (IOException ex) {
      throw new IllegalStateException(FILE_PROPERTY+" is missing");
    }
  }
  public static LocatorsUtils getLocatorsInstance(){
    if(LocatorsUtils==null){LocatorsUtils=new LocatorsUtils();
    }return LocatorsUtils;
  }
  public String getLocatorValue(String key){
     return locatorsProperties.getProperty(key);
  }
}
