package com.automation.mobile.config;

import io.appium.java_client.android.options.UiAutomator2Options;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CapabilitiesManager {

	private static final Logger logger = LoggerFactory.getLogger(CapabilitiesManager.class);

	public static UiAutomator2Options getAndroidCapabilities() {
		logger.info("Initializing Appium Android desired capabilities.");
		UiAutomator2Options capabilities = new UiAutomator2Options();

		capabilities.setPlatformName(PropertyReader.getProperty("platform.name"));
		capabilities.setDeviceName(PropertyReader.getProperty("device.name"));
		String platformVersion = System.getProperty("platform.version", PropertyReader.getProperty("platform.version"));
		capabilities.setPlatformVersion(platformVersion);
		capabilities.setAutomationName(PropertyReader.getProperty("automation.name"));
		capabilities.setAppPackage(PropertyReader.getProperty("app.package"));
		capabilities.setAppActivity(PropertyReader.getProperty("app.activity"));
		capabilities.setNoReset(Boolean.parseBoolean(PropertyReader.getProperty("no.reset")));
		capabilities.setFullReset(Boolean.parseBoolean(PropertyReader.getProperty("full.reset")));

		String appPath = System.getProperty("user.dir") + File.separator + PropertyReader.getProperty("app.path");
		capabilities.setApp(appPath);

		String installTimeout = PropertyReader.getProperty("uiautomator2.server.install.timeout");
		if (installTimeout != null && !installTimeout.isEmpty()) {
			capabilities.setCapability("uiautomator2ServerInstallTimeout", Integer.parseInt(installTimeout));
		}

		String adbExecTimeout = PropertyReader.getProperty("adb.exec.timeout");
		if (adbExecTimeout != null && !adbExecTimeout.trim().isEmpty()) {
			capabilities.setCapability("adbExecTimeout", Integer.parseInt(adbExecTimeout));
		}

		logger.info("Final Android capabilities prepared: {}", capabilities.toJson());
		return capabilities;
	}

}