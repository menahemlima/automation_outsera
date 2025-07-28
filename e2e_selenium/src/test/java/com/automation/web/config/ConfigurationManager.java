package com.automation.web.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigurationManager {

	private static final Logger logger = LogManager.getLogger(ConfigurationManager.class);
	private static Properties properties;

	static {
		properties = new Properties();
		try (InputStream input = ConfigurationManager.class.getClassLoader()
				.getResourceAsStream("application.properties")) {
			if (input == null) {
				logger.error("Error: File config.properties not found on classpath.");
				System.exit(1);
			}
			properties.load(input);
			logger.info("config.properties loader successfully.");
		} catch (IOException ex) {
			logger.error("Error to load config.properties: " + ex.getMessage());
			System.exit(1);
		}
	}

	public static String getProperty(String key) {
		String value = System.getProperty(key);
		if (value != null) {
			return value;
		}
		return properties.getProperty(key);
	}

}