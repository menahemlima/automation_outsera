package com.automation.web.stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import com.automation.web.config.ConfigurationManager;
import com.automation.web.config.DriverManager;
import com.automation.web.pages.LoginPage;
import com.automation.web.pages.ProductsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefinitions {
	private WebDriver driver;
	private LoginPage loginPage;
	private ProductsPage productsPage;

	public LoginStepDefinitions() {
		this.driver = DriverManager.getDriver();
		this.loginPage = new LoginPage(driver);
		this.productsPage = new ProductsPage(driver);
	}

	@When("I fill the field with {string} and {string} valid")
	public void iFillTheFieldWithAndValid(String username, String password) {
		loginPage.loginOnApp(username, password);
	}

	@When("I fill the field with {string} and {string} invalid")
	public void iFillTheFieldWithAndInvalid(String username, String password) {
		loginPage.loginOnApp(username, password);
	}

	@When("I click on the {string} button")
	public void iClickOnTheButton(String buttonName) {
		loginPage.clickLoginButton();
	}

	@Then("I should be redirected to the {string} page")
	public void iShouldBeRedirectedToThePage(String expectedPage) {
		Assert.assertTrue("Not redirected to the Products/Dashboard page. Current URL: " + driver.getCurrentUrl(),
				driver.getCurrentUrl().contains("inventory.html"));

		String actualTitle = productsPage.getProductsPageTitle();
		Assert.assertEquals("Is not on the Products page.", expectedPage, actualTitle);
	}

	@Then("I should not be redirected to the page Products")
	public void iShouldNotBeRedirectedToThePageProducts() {
		Assert.assertTrue("Unexpectedly redirected to another page. Current URL: " + driver.getCurrentUrl(),
				driver.getCurrentUrl().equals(ConfigurationManager.getProperty("base.url")));
		Assert.assertTrue("Login page elements not visible, even without redirection.",
				loginPage.isLoginPageDisplayed());
	}

	@Then("the message {string} should be displayed")
	public void theMessageShouldBeDisplayed(String expectedMessage) {
		String actualMessage = loginPage.getErrorMessage();
		Assert.assertEquals("The displayed error message is incorrect.", expectedMessage, actualMessage);
	}
}