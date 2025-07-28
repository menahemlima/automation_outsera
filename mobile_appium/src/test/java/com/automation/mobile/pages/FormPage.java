package com.automation.mobile.pages;

import java.time.Duration;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.automation.mobile.config.PropertyReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage {

	private static final Logger logger = LoggerFactory.getLogger(FormPage.class);
	private AppiumDriver driver;
	private WebDriverWait wait;

	@AndroidFindBy(id = "com.skill2lead.appiumdemo:id/Et2")
	private WebElement txtName;

	@AndroidFindBy(id = "com.skill2lead.appiumdemo:id/Et3")
	private WebElement txtEmail;

	@AndroidFindBy(id = "com.skill2lead.appiumdemo:id/Et6")
	private WebElement txtAddress;

	@AndroidFindBy(id = "com.skill2lead.appiumdemo:id/Et7")
	private WebElement txtMobile;

	@AndroidFindBy(id = "com.skill2lead.appiumdemo:id/Btn2")
	private WebElement btnSubmit;

	@AndroidFindBy(id = "com.skill2lead.appiumdemo:id/Tv2")
	private WebElement lblName;

	@AndroidFindBy(id = "com.skill2lead.appiumdemo:id/Tv7")
	private WebElement lblEmail;

	@AndroidFindBy(id = "com.skill2lead.appiumdemo:id/Tv5")
	private WebElement lblAddress;

	@AndroidFindBy(id = "com.skill2lead.appiumdemo:id/Tv6")
	private WebElement lblMobile;

	@AndroidFindBy(xpath = "//android.widget.Toast[1]")
	private WebElement msgSuccess;

	public FormPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		long timeoutSeconds = Long.parseLong(PropertyReader.getProperty("implicit.wait.seconds"));
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
		logger.info("FormPage initialized with WebDriverWait timeout: {} seconds", timeoutSeconds);
	}

	public void fillContactUsForm(String name, String email, String address, String mobile) {
		this.enterName(name);
		this.enterEmail(email);
		this.enterAddress(address);
		this.enterMobile(mobile);
		this.clickSubmitButton();
	}

	public void resultContactUsFormFilled(String name, String email, String address, String mobile) {
		this.labelName(name);
		this.lblEmail(email);
		this.lblAddress(address);
		this.lblMobile(mobile);
	}

	private void labelName(String name) {
		wait.until(ExpectedConditions.visibilityOf(lblName)).getText();
	}

	private void lblEmail(String email) {
		wait.until(ExpectedConditions.visibilityOf(lblEmail)).getText();
	}

	private void lblAddress(String address) {
		wait.until(ExpectedConditions.visibilityOf(lblAddress)).getText();
	}

	private void lblMobile(String mobile) {
		wait.until(ExpectedConditions.visibilityOf(lblMobile)).getText();
	}

	private void enterName(String name) {
		wait.until(ExpectedConditions.visibilityOf(txtName)).sendKeys(name);
	}

	private void enterEmail(String email) {
		wait.until(ExpectedConditions.visibilityOf(txtEmail)).sendKeys(email);
	}

	private void enterAddress(String address) {
		wait.until(ExpectedConditions.visibilityOf(txtAddress)).sendKeys(address);
	}

	private void enterMobile(String mobile) {
		wait.until(ExpectedConditions.visibilityOf(txtMobile)).sendKeys(mobile);
	}

	private void clickSubmitButton() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit)).click();
	}

	public void getSuccessMessage(String message) {
		String actualResultText = msgSuccess.getText();
		Assert.assertTrue(actualResultText.contains(message));
	}

}
