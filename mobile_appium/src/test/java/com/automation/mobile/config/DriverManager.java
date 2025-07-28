package com.automation.mobile.config;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.net.URL;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverManager {

	private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
	private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

	public static void initializeDriver() {
		logger.info("Loading properties for driver initialization.");
		PropertyReader.loadProperties("src/test/resources/application.properties");

		if (driver.get() == null) {
			String platform = PropertyReader.getProperty("platform.name");
			String appiumServerURLString = PropertyReader.getProperty("appium.server.url");

			try {
				URI appiumServerURI = new URI(appiumServerURLString);
				URL appiumServerURL = appiumServerURI.toURL();

				switch (platform) {
				case MobilePlatform.ANDROID:
					UiAutomator2Options androidOptions = CapabilitiesManager.getAndroidCapabilities();
					driver.set(new AndroidDriver(appiumServerURL, androidOptions));
					break;
				case MobilePlatform.IOS:
					throw new IllegalArgumentException("iOS platform not fully configured yet.");
				default:
					throw new IllegalArgumentException("Unsupported platform:: " + platform);
				}
				driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			} catch (URISyntaxException e) {
				throw new RuntimeException("Invalid Appium Server URL syntax: " + appiumServerURLString, e);
			} catch (MalformedURLException e) {
				throw new RuntimeException("Malformed Appium Server URL: " + appiumServerURLString, e);
			} catch (Exception e) {
				throw new RuntimeException("Appium Driver initialization failed for platform: " + platform, e);
			}
		} else {
			logger.info("Driver already initialized for this thread. Skipping re-initialization.");
		}
	}

	public static AppiumDriver getDriver() {
		AppiumDriver currentDriver = driver.get();
		if (currentDriver == null) {
			throw new IllegalStateException("Driver not initialized. Call initializeDriver() first.");
		}
		return currentDriver;
	}

	public static void quitDriver() {
		if (driver.get() != null) {
			logger.info("Quitting AppiumDriver instance for current thread.");
			driver.get().quit();
			driver.remove();
		}
	}

}
