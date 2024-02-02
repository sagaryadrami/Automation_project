package SagarYadrami.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SagarYadrami.AbstractComponent.AbstractComponent;

public class OrderPage extends AbstractComponent{
    WebDriver driver;
	 
	@FindBy(css=".totalRow button")
	WebElement checkOutEle;
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productsNames;
	
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public Boolean VerifyOrderDisplay(String productName) {
		boolean match = productsNames.stream()
				.anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}


}
