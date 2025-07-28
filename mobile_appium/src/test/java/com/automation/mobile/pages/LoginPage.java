package com.automation.mobile.pages;

import java.time.Duration;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.automation.mobile.config.PropertyReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage {

	private AppiumDriver driver;
	private WebDriverWait wait;

	@AndroidFindBy(id = "com.skill2lead.appiumdemo:id/Et4")
	private WebElement txtUsername;

	@AndroidFindBy(id = "com.skill2lead.appiumdemo:id/Et5")
	private WebElement txtPassword;

	@AndroidFindBy(id = "com.skill2lead.appiumdemo:id/Btn3")
	private WebElement btnLogin;

	@AndroidFindBy(xpath = "//android.widget.Toast[1]")
	private WebElement msgSuccess;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.skill2lead.appiumdemo:id/Tv8\"]")
	private WebElement msgError;

	public LoginPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		long timeoutSeconds = Long.parseLong(PropertyReader.getProperty("implicit.wait.seconds"));
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
	}

	public void loginOnApp(String name, String password) {
		this.enterUsername(name);
		this.enterPassword(password);
		this.isLoginPageDisplayed();
		this.clickLoginButton();
	}

	private void enterUsername(String username) {
		wait.until(ExpectedConditions.visibilityOf(txtUsername)).sendKeys(username);
	}

	private void enterPassword(String password) {
		wait.until(ExpectedConditions.visibilityOf(txtPassword)).sendKeys(password);
	}

	private void clickLoginButton() {
		wait.until(ExpectedConditions.elementToBeClickable(btnLogin)).click();
	}

	private void isLoginPageDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(txtPassword)).isDisplayed();
	}

	public void getSuccessMessage(String message) {
		String actualResultText = msgSuccess.getText();
		Assert.assertTrue(actualResultText.contains(message));
	}

	public void getErrorMessage(String message) {
		String actualResultText = msgError.getText();
		Assert.assertTrue(actualResultText.contains(message));
	}

}
