package com.automation.web.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {

	private WebDriverWait wait;

	@FindBy(css = ".title")
	private WebElement productsHeader;

	@FindBy(id = "shopping_cart_container")
	private WebElement shoppingCartIcon;

	@FindBy(id = "add-to-cart-sauce-labs-backpack")
	private WebElement addToCartBackpackButton;

	@FindBy(id = "add-to-cart-sauce-labs-bike-light")
	private WebElement addToCartBikeLightButton;

	public ProductsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	public boolean isProductsHeaderDisplayed() {
		try {
			wait.until(ExpectedConditions.visibilityOf(productsHeader));
			return productsHeader.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public String getProductsPageTitle() {
		return wait.until(ExpectedConditions.visibilityOf(productsHeader)).getText();
	}

	public void addProductToCart(String productName) {
		WebElement productButton;
		switch (productName) {
		case "Sauce Labs Backpack":
			productButton = addToCartBackpackButton;
			break;
		case "Sauce Labs Bike Light":
			productButton = addToCartBikeLightButton;
			break;
		default:
			throw new IllegalArgumentException(
					"Product '" + productName + "' not found or not mapped in ProductsPage.");
		}
		wait.until(ExpectedConditions.elementToBeClickable(productButton)).click();
	}

	public void clickShoppingCartIcon() {
		wait.until(ExpectedConditions.elementToBeClickable(shoppingCartIcon)).click();
	}

}
