package SagarYadrami.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SagarYadrami.TestComponents.BaseTest;
import SagarYadrami.pageobjects.CartPage;
import SagarYadrami.pageobjects.LandingPage;
import SagarYadrami.pageobjects.OrderPage;
import SagarYadrami.pageobjects.ProductCatalogue;
import SagarYadrami.pageobjects.checkOutPage;
import SagarYadrami.pageobjects.confirmationPage;

public class SubmitOrderTest extends BaseTest {
 
	String productName = "ZARA COAT 3";
	     @Test 
		public void submitOrder() throws IOException, InterruptedException {

		

		ProductCatalogue productCatalogue =landingpage.loginApplication("sagaryadrami@gmail.com", "Sagar@123");
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage= productCatalogue.goToCartPage();

		 
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		checkOutPage checkOutPage1=cartPage.goToCheckOut();
		checkOutPage1.selectCountry("india");
		confirmationPage confirmationPage1 =checkOutPage1.submitOrder();

		// getting the confirmation message
		String confirmMessage = confirmationPage1.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

	}
	     @Test(dependsOnMethods="SubmitOrderTes")
	     public void OrderHistoryTest() {
	     ProductCatalogue productCatalogue =landingpage.loginApplication("sagaryadrami@gmail.com", "Sagar@123");
	     OrderPage orderPage = productCatalogue.goToOrderPage();
	     //orderPage.VerifyOrderDisplay(productName);
	     Assert.assertTrue( orderPage.VerifyOrderDisplay(productName));
	     }
}
