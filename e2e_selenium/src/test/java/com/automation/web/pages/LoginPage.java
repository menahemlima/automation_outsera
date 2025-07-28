package com.automation.web.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(id = "user-name")
	private WebElement txtUsername;

	@FindBy(id = "password")
	private WebElement txtPassword;

	@FindBy(id = "login-button")
	private WebElement btnLogin;

	@FindBy(css = "[data-test='error']")
	private WebElement msgError;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	public void loginOnApp(String username, String Password) {
		this.isLoginPageDisplayed();
		this.enterUsername(username);
		this.enterPassword(Password);
	}

	public void navigateTo(String url) {
		this.driver.get(url);
		wait.until(ExpectedConditions.visibilityOf(txtUsername));
	}

	private void enterUsername(String username) {
		txtUsername.sendKeys(username);
	}

	private void enterPassword(String password) {
		txtPassword.sendKeys(password);
	}

	public void clickLoginButton() {
		btnLogin.click();
	}

	public boolean isLoginPageDisplayed() {
		return txtUsername.isDisplayed();
	}

	public String getErrorMessage() {
		wait.until(ExpectedConditions.visibilityOf(msgError));
		return msgError.getText();
	}

}
