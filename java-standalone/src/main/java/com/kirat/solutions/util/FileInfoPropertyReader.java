package com.kirat.solutions.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

public class FileInfoPropertyReader {

private static Map<String, String> propertiesMap = new Hashtable<String, String>();

	
	private FileInfoPropertyReader() {
		Properties properties = new Properties();
		InputStream inputStream = null;

		try {
			inputStream = getClass().getClassLoader().getResourceAsStream("FileInfo.properties");
			if(null != inputStream){
				properties.load(inputStream);
					for (String keyName : properties.stringPropertyNames()) {
					String keyValue = properties.getProperty(keyName);
					propertiesMap.put(keyName, keyValue);
				}
			}
		} catch (IOException ioException) {
			System.out.println(ioException.getMessage());
		}finally{
			try {
				if(null != inputStream){
					inputStream.close();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

	}

	private static class LazyLoader {
			private static final FileInfoPropertyReader INSTANCE = new FileInfoPropertyReader();
	}

	
	public static FileInfoPropertyReader getInstance() {
		return LazyLoader.INSTANCE;
	}

	
	public String getString(String keyName) {
		// return String.valueOf(properties.get(keyName));
		return propertiesMap.get(keyName);
	}
	
}
