package SagarYadrami.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import SagarYadrami.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent{
    WebDriver driver;
	 
	@FindBy(css=".totalRow button")
	WebElement checkOutEle;
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartproducts;
	
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
	}
	
	public Boolean VerifyProductDisplay(String productName) {
		boolean match = cartproducts.stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productName));
		return match;
	}

	public checkOutPage goToCheckOut() {
		checkOutEle.click();
		return new checkOutPage(driver);
		
	}
}
