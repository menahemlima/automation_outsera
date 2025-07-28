package com.automation.web.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutOverviewPage {

	private WebDriverWait wait;

	@FindBy(css = "[data-test='title']")
	private WebElement lblOverviewTitle;

	@FindBy(css = "[data-test='subtotal-label']")
	private WebElement lblSubTotal;

	@FindBy(css = "[data-test='tax-label']")
	private WebElement lblTaxLabel;

	@FindBy(css = "[data-test='total-label']")
	private WebElement lblTotalLabel;

	@FindBy(id = "finish")
	private WebElement btnFinish;

	public CheckoutOverviewPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public boolean isOverviewPageDisplayed() {
		return wait.until(ExpectedConditions.visibilityOf(lblOverviewTitle)).isDisplayed();
	}

	private double getSubTotalValue() {
		String subTotalText = wait.until(ExpectedConditions.visibilityOf(lblSubTotal)).getText();
		return Double.parseDouble(subTotalText.replace("Item total: $", ""));
	}

	private double getTaxValue() {
		String taxText = wait.until(ExpectedConditions.visibilityOf(lblTaxLabel)).getText();
		return Double.parseDouble(taxText.replace("Tax: $", ""));
	}

	public double getTotalValue() {
		String totalText = wait.until(ExpectedConditions.visibilityOf(lblTotalLabel)).getText();
		return Double.parseDouble(totalText.replace("Total: $", ""));
	}

	public double getCalculatedTotal() {
		double subTotal = getSubTotalValue();
		double tax = getTaxValue();
		double calculatedTotal = subTotal + tax;
		return calculatedTotal;
	}

	public void clickFinishButton() {
		wait.until(ExpectedConditions.elementToBeClickable(btnFinish)).click();
	}

}
