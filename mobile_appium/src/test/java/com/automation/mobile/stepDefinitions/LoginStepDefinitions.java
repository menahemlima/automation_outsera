package com.automation.mobile.stepDefinitions;

import com.automation.mobile.config.DriverManager;
import com.automation.mobile.pages.LoginPage;
import com.automation.mobile.pages.MenuPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefinitions {
	private MenuPage menuPage;
	private LoginPage loginPage;

	public LoginStepDefinitions() {
		menuPage = new MenuPage(DriverManager.getDriver());
		loginPage = new LoginPage(DriverManager.getDriver());
	}

	@Given("on the Skill2Lead application home screen")
	public void onTheSkill2LeadApplicationHomeScreen() {
	}

	@When("performs login with username {string} and password {string}")
	public void performsLoginWithUsernameAndPassword(String user, String password) {
		menuPage.clickLoginButton();
		loginPage.loginOnApp(user, password);
	}

	@Then("the login result message {string} is displayed")
	public void loginIsSuccessfulShowingMessage(String message) {
		if (message.equals("Successfull login")) {
			loginPage.getSuccessMessage(message);
		} else {
			loginPage.getErrorMessage(message);
		}
	}
}
