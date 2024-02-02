package SagarYadrami.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import SagarYadrami.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String productName = "ZARA COAT 3";

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		driver.get("https://rahulshettyacademy.com/client");

		LandingPage landingpage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("sagaryadrami@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Sagar@123");
		driver.findElement(By.id("login")).click();

		// sometimes the items takes little time to load so we have to wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		// filtering for the desired product
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);

		// now clicking on the add to chart button
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		// we have to give wait for screen to load to continue further action
		// WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		// adding to the cart and clicking on it
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		// now verifying weather the items contains the desired item
		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match = cartproducts.stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase("ZARA COAT 3"));
		Assert.assertTrue(match);
		

		// now clicking on the checkout button
		
		 driver.findElement(By.cssSelector(".totalRow button")).click();
		

		// now selecting India in select country box
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();

		// placing the order
		driver.findElement(By.cssSelector(".action__submit")).click();

		// getting the confirmation message
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

	}

}
