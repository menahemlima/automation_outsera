package com.automation.mobile.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyReader {

	private static final Logger logger = LoggerFactory.getLogger(PropertyReader.class);
	private static Properties properties;

	public static void loadProperties(String filePath) {
		properties = new Properties();
		try (FileInputStream fis = new FileInputStream(filePath)) {
			properties.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Failed to load properties from :", filePath, e.getMessage(), e);
			throw new RuntimeException("Failed to load properties from " + filePath);
		}
	}

	public static String getProperty(String key) {
		if (properties == null) {
			throw new IllegalStateException("Properties not loaded. Call loadProperties() first.");
		}
		return properties.getProperty(key);
	}

}
