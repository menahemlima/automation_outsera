package com.automation.mobile.hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.automation.mobile.config.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

	@Before
	public void setup() {
		logger.info("Executing @Before hook: Initializing Appium Driver...");
		DriverManager.initializeDriver();
	}

	@After
	public void tearDown(Scenario scenario) {
		WebDriver driver = DriverManager.getDriver();
		final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

		if (scenario.isFailed()) {
			try {
				scenario.attach(screenshot, "image/png", scenario.getName() + "_screenshot");
			} catch (Exception e) {
			}
		} else {
			scenario.attach(screenshot, "image/png", scenario.getName() + "_screenshot");
		}
		if (driver != null) {
			DriverManager.quitDriver();
		}
		logger.info("#### Finished Scenario: " + scenario.getName() + " ####\n");
	}

}
