package com.automation.web.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutInformationPage {

	private WebDriverWait wait;

	@FindBy(id = "first-name")
	private WebElement txtFirstName;

	@FindBy(id = "last-name")
	private WebElement txtLastName;

	@FindBy(id = "postal-code")
	private WebElement txtZipCode;

	@FindBy(id = "continue")
	private WebElement btnContinue;

	public CheckoutInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public void enterFirstName(String firstName) {
		wait.until(ExpectedConditions.elementToBeClickable(txtFirstName)).sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		wait.until(ExpectedConditions.elementToBeClickable(txtLastName)).sendKeys(lastName);
	}

	public void enterZipCode(String zipCode) {
		wait.until(ExpectedConditions.elementToBeClickable(txtZipCode)).sendKeys(zipCode);
	}

	public void clickContinueButton() {
		wait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
	}

}
