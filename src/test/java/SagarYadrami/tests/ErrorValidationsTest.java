package SagarYadrami.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SagarYadrami.TestComponents.BaseTest;
import SagarYadrami.pageobjects.CartPage;
import SagarYadrami.pageobjects.LandingPage;
import SagarYadrami.pageobjects.ProductCatalogue;
import SagarYadrami.pageobjects.checkOutPage;
import SagarYadrami.pageobjects.confirmationPage;

public class ErrorValidationsTest extends BaseTest {

	     @Test(groups= {"ErrorHandling"}) 
		public void LoginErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";

		landingpage.loginApplication("sagaryadrami@gmail.com", "Sagar@kljfeg123");
		landingpage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password", landingpage.getErrorMessage());
	}
	     
	     @Test 
			public void ProductErrorValidation() throws IOException, InterruptedException {

			String productName = "ZARA COAT 3";

			ProductCatalogue productCatalogue =landingpage.loginApplication("sagaryadrami@gmail.com", "Sagar@123");
			
			List<WebElement> products = productCatalogue.getProductList();
			productCatalogue.addProductToCart(productName);
			CartPage cartPage= productCatalogue.goToCartPage();
			Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
			Assert.assertFalse(match);
			

		}


}
