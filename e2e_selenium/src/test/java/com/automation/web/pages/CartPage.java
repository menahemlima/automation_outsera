package com.automation.web.pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

	private WebDriverWait wait;

	@FindBy(css = "[data-test='inventory-item']")
	private List<WebElement> lblCartItems;

	@FindBy(css = "[data-test='inventory-item-name']")
	private List<WebElement> lblItemNamesInCart;

	@FindBy(id = "checkout")
	private WebElement btnCheckout;

	public CartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public boolean isProductInCart(String productName) {
		wait.until(ExpectedConditions.visibilityOfAllElements(lblItemNamesInCart));
		return lblItemNamesInCart.stream().anyMatch(item -> item.getText().equals(productName));
	}

	public int getNumberOfItemsInCart() {
		return wait.until(ExpectedConditions.visibilityOfAllElements(lblCartItems)).size();
	}

	public void clickCheckoutButton() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCheckout)).click();
	}

}
