package com.automation.web.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutCompletePage {

	private WebDriverWait wait;

	@FindBy(css = "[data-test='title']")
	private WebElement lblCompleteTitle;

	@FindBy(css = "[data-test='complete-header']")
	private WebElement lblConfirmationHeader;

	public CheckoutCompletePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public boolean isCheckoutCompletePageDisplayed() {
		return wait.until(ExpectedConditions.visibilityOf(lblCompleteTitle)).isDisplayed();
	}

	public String getConfirmationMessage() {
		return wait.until(ExpectedConditions.visibilityOf(lblConfirmationHeader)).getText();
	}

}
