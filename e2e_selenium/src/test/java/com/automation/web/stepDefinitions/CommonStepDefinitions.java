package com.automation.web.stepDefinitions;

import org.openqa.selenium.WebDriver;
import com.automation.web.config.ConfigurationManager;
import com.automation.web.config.DriverManager;
import com.automation.web.pages.LoginPage;
import io.cucumber.java.en.Given;

public class CommonStepDefinitions {
	private WebDriver driver;
	private LoginPage loginPage;

	public CommonStepDefinitions() {
		this.driver = DriverManager.getDriver();
		this.loginPage = new LoginPage(driver);
	}

	@Given("I had access the login page")
	public void i_had_access_the_login_page() {
		String loginUrl = ConfigurationManager.getProperty("base.url");
		loginPage.navigateTo(loginUrl);
	}

	@Given("I am logged on Swag Labs")
	public void iAmLoggedOnSwagLabs() {
		String loginUrl = ConfigurationManager.getProperty("base.url");
		loginPage.navigateTo(loginUrl);
		loginPage.loginOnApp(ConfigurationManager.getProperty("username"),
				ConfigurationManager.getProperty("password"));
		loginPage.clickLoginButton();
	}

}
