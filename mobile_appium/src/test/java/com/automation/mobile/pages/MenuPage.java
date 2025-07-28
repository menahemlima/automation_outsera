package com.automation.mobile.pages;

import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.automation.mobile.config.PropertyReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MenuPage {

	private AppiumDriver driver;
	private WebDriverWait wait;

	@AndroidFindBy(id = "com.skill2lead.appiumdemo:id/Login")
	private WebElement btnLoginMenu;

	@AndroidFindBy(id = "com.skill2lead.appiumdemo:id/ContactUs")
	private WebElement btnFormsMenu;

	public MenuPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		long timeoutSeconds = Long.parseLong(PropertyReader.getProperty("implicit.wait.seconds"));
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
	}

	public void clickLoginButton() {
		wait.until(ExpectedConditions.elementToBeClickable(btnLoginMenu)).click();
	}

	public void clickFormsButton() {
		wait.until(ExpectedConditions.elementToBeClickable(btnFormsMenu)).click();
	}

}
