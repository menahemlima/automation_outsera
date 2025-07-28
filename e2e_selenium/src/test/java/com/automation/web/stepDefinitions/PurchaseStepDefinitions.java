package com.automation.web.stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import com.automation.web.config.DriverManager;
import com.automation.web.pages.CartPage;
import com.automation.web.pages.CheckoutCompletePage;
import com.automation.web.pages.CheckoutInformationPage;
import com.automation.web.pages.CheckoutOverviewPage;
import com.automation.web.pages.ProductsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PurchaseStepDefinitions {
	private WebDriver driver;
	private ProductsPage productsPage;
	private CartPage cartPage;
	private CheckoutInformationPage checkoutInformationPage;
	private CheckoutOverviewPage checkoutOverviewPage;
	private CheckoutCompletePage checkoutCompletePage;

	public PurchaseStepDefinitions() {
		this.driver = DriverManager.getDriver();
		this.productsPage = new ProductsPage(driver);
		this.cartPage = new CartPage(driver);
		this.checkoutInformationPage = new CheckoutInformationPage(driver);
		this.checkoutOverviewPage = new CheckoutOverviewPage(driver);
		this.checkoutCompletePage = new CheckoutCompletePage(driver);
	}

	@When("I add {string} and {string} to the cart")
	public void iAddAndToTheCart(String product01, String product02) {
		productsPage.addProductToCart(product01);
		productsPage.addProductToCart(product02);
		productsPage.clickShoppingCartIcon();

		Assert.assertTrue("Product " + product01 + " not found in cart.", cartPage.isProductInCart(product01));
		Assert.assertTrue("Product " + product02 + " not found in cart.", cartPage.isProductInCart(product02));
		Assert.assertEquals("Incorrect number of items in cart.", 2, cartPage.getNumberOfItemsInCart());

		cartPage.clickCheckoutButton();
	}

	@When("I complete the checkout process with first name {string}, last name {string} and zip code {string}")
	public void iCompleteTheCheckoutProcessWithFirstNameLastNameAndZipCode(String first_name, String last_name,
			String zip_code) {
		double delta = 0.001;

		checkoutInformationPage.enterFirstName(first_name);
		checkoutInformationPage.enterLastName(last_name);
		checkoutInformationPage.enterZipCode(zip_code);
		checkoutInformationPage.clickContinueButton();

		double calculatedTotal = checkoutOverviewPage.getCalculatedTotal();
		double totalDisplayed = checkoutOverviewPage.getTotalValue();

		Assert.assertTrue("Not on Checkout Overview page.", checkoutOverviewPage.isOverviewPageDisplayed());
		Assert.assertEquals("The calculated total does not match the displayed total on the Overview page.",
				calculatedTotal, totalDisplayed, delta);
		checkoutOverviewPage.clickFinishButton();
	}

	@Then("I should see the order confirmation message {string}")
	public void iShouldSeeTheOrderConfirmationMessage(String messageConfirmation) {
		Assert.assertTrue("Not on Checkout Complete page.", checkoutCompletePage.isCheckoutCompletePageDisplayed());

		String actualConfirmationMessage = checkoutCompletePage.getConfirmationMessage();
		Assert.assertEquals("Order confirmation message is incorrect.", messageConfirmation, actualConfirmationMessage);
	}

}
