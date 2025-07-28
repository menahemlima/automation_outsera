package com.automation.mobile.stepDefinitions;

import com.automation.mobile.config.DriverManager;
import com.automation.mobile.pages.MenuPage;
import com.automation.mobile.pages.FormPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ContactUSStepDefinitions {

	private MenuPage menuPage;
	private FormPage formPage;
	private String name;
	private String email;
	private String address;
	private String mobile;

	public ContactUSStepDefinitions() {
		menuPage = new MenuPage(DriverManager.getDriver());
		formPage = new FormPage(DriverManager.getDriver());
	}

	@When("I access the Contact us form")
	public void iAccessTheContactUsForm() {
		menuPage.clickFormsButton();
	}

	@When("I submit the form with {string}, {string}, {string}, {string}")
	public void iSubmitTheFormWith(String name, String email, String address, String mobile) {
		this.name = name;
		this.email = email;
		this.address = address;
		this.mobile = mobile;

		formPage.fillContactUsForm(name, email, address, mobile);
	}

	@Then("a success message {string} should be displayed")
	public void aSuccessMessageShouldBeDisplayed(String string) {
		formPage.getSuccessMessage("register Successfully!");
	}

	@Then("the submitted information should be correctly displayed")
	public void theSubmittedInformationShouldBeCorrectlyDisplayed() {
		formPage.resultContactUsFormFilled(name, email, address, mobile);
	}

}
