package com.automation.mobile.stepDefinitions;

import com.automation.mobile.config.DriverManager;
import com.automation.mobile.config.PropertyReader;
import com.automation.mobile.pages.LoginPage;
import com.automation.mobile.pages.MenuPage;
import com.automation.mobile.pages.AdminScreen;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdminStepDefinitions {

	private MenuPage menuPage;
	private LoginPage loginPage;
	private AdminScreen adminScreen;
	private String text;

	public AdminStepDefinitions() {
		menuPage = new MenuPage(DriverManager.getDriver());
		loginPage = new LoginPage(DriverManager.getDriver());
		adminScreen = new AdminScreen(DriverManager.getDriver());
	}

	@Given("I am logged on Skill2Lead app")
	public void iAmLoggedOnSkill2LeadApp() {
		String user = PropertyReader.getProperty("app.username");
		String password = PropertyReader.getProperty("app.password");

		menuPage.clickLoginButton();
		loginPage.loginOnApp(user, password);
	}

	@When("I enter information into the {string} field")
	public void iEnterInformationIntoTheTextField(String nameAdmin) {
		this.text = PropertyReader.getProperty("app.admin");
		adminScreen.sendInformation(nameAdmin);
	}

	@Then("should be show a message with the previously entered value")
	public void shouldBeShowAMessageWithThePreviouslyEnteredValue() {
		adminScreen.validateTextOnScreen(text);
	}

}
