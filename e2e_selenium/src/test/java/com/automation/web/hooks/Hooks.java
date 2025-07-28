package com.automation.web.hooks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.time.Duration;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.automation.web.config.DriverManager;

public class Hooks {

	private static final Logger logger = LogManager.getLogger(Hooks.class);

	@Before
	public void setup(Scenario scenario) {
		logger.info("#### Starting Scenario: " + scenario.getName() + " ####\n");
		WebDriver driver = DriverManager.getDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().deleteAllCookies();
	}

	@After
	public void teardown(Scenario scenario) {
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
