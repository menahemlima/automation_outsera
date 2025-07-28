package com.automation.mobile.pages;

import java.time.Duration;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import com.automation.mobile.config.PropertyReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AdminScreen {

	private static final Logger logger = LoggerFactory.getLogger(MenuPage.class);
	private AppiumDriver driver;
	private WebDriverWait wait;

	@AndroidFindBy(id = "com.skill2lead.appiumdemo:id/Edt_admin")
	private WebElement textField;

	@AndroidFindBy(id = "com.skill2lead.appiumdemo:id/Btn_admin_sub")
	private WebElement btnSubmit;

	@AndroidFindBy(id = "com.skill2lead.appiumdemo:id/Tv_admin")
	private WebElement lblResult;

	public AdminScreen(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		long timeoutSeconds = Long.parseLong(PropertyReader.getProperty("implicit.wait.seconds"));
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
		logger.info("AdminScreen initialized with WebDriverWait timeout: {} seconds", timeoutSeconds);
	}

	public void sendInformation(String text) {
		this.enterText(text);
		this.clickSendButton();
		this.validateTextOnScreen(text);
	}

	private void enterText(String text) {
		wait.until(ExpectedConditions.visibilityOf(textField)).sendKeys(text);
	}

	private void clickSendButton() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit)).click();
	}

	public void validateTextOnScreen(String text) {
		String actualResultText = lblResult.getText();
		Assert.assertTrue(actualResultText.contains(text));
	}

}
