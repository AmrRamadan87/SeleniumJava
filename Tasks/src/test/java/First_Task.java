import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class First_Task {
	
	WebDriver driver;

	@BeforeTest

	public void Broswing() {

		WebDriverManager.chromiumdriver().setup();
		driver = new ChromeDriver();
		String baseUrl = "http://magento-demo.lexiconn.com/";
		driver.manage().window().maximize();
		driver.get(baseUrl);
	}

	@Test()
	public void Selecting_REGISTER() throws InterruptedException {

		Thread.sleep(1000);
		driver.findElement(By.xpath("//header/div[1]/div[2]/a[3]/span[2]")).click();
		// Instantiate Action Class
		Actions actions = new Actions(driver);
		WebElement menuOption = driver.findElement(By.xpath("//a[contains(text(),'Register')]"));
		actions.moveToElement(menuOption).perform();
		WebElement selectMenuOption = driver.findElement(By.xpath("//a[contains(text(),'Register')]"));
		selectMenuOption.click();
	}

	// Registering New Account And Message Assertion.
	@Test(dependsOnMethods = "Selecting_REGISTER")
	public void RegisteringNewAccount() {
		driver.findElement(By.id("firstname")).sendKeys("Ahmed");
		driver.findElement(By.id("lastname")).sendKeys("Hussien");
		driver.findElement(By.id("email_address")).sendKeys("haxem31213@rxcay.com");
		driver.findElement(By.id("password")).sendKeys("3NnjWxcZnwYVXiK");
		driver.findElement(By.id("confirmation")).sendKeys("3NnjWxcZnwYVXiK");
		driver.findElement(By.id("is_subscribed")).click();
		driver.findElement(By.xpath("//*[@id=\"form-validate\"]/div[2]/button")).click();
		
		String ActualStatement = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div/div/div[2]/p[1]/strong")).getText();
		String ExpectedStatement = "Hello, Ahmed Hussien!";
		Assert.assertEquals(ActualStatement, ExpectedStatement);
	}

}
