package AutomationPracticeWebsite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class automationpracticewebsiteTest {

	WebDriver driver;
	Properties prop = new Properties();
	String s;
	
	@BeforeClass
	public void Initialization() throws IOException {
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\Properties\\config1.properties");
		prop.load(fis);
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Driver\\chromedriver.exe");
		
		
		driver = new ChromeDriver();
		driver.get(prop.getProperty("Test_Url"));
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void women() throws InterruptedException {
		WebElement women = driver.findElement(By.xpath(prop.getProperty("Women")));
		Actions act = new Actions(driver);
		act.moveToElement(women).perform();
		driver.findElement(By.xpath(prop.getProperty("Dress"))).click();
		Thread.sleep(2000);
	}
	
	@Test(dependsOnMethods = { "women" })
	//ITestContext context is another way to share variables from 1 method to another 
	//which was earlier implemented in the below methods but now not used, instead the 
	//variable is declared at the class level
	public void dress(ITestContext context) throws InterruptedException {
		//WebElement PrintedDress = driver.findElement(By.className("product-image-container"));
		WebElement PrintedDress = driver.findElement(By.xpath(prop.getProperty("Printed_Dress")));
		Actions act = new Actions(driver);
		Thread.sleep(2000);
		//JavascriptExecutor je = (JavascriptExecutor) driver; Javascript method to scroll down
		//je.executeScript("window.scrollBy(0,700);");
		//je.executeScript("arguments[0].ScrollIntoView(true);", PrintedDress);
		act.moveToElement(PrintedDress).perform();
		driver.findElement(By.xpath(prop.getProperty("Add_To_Cart"))).click();
		Thread.sleep(3000);
		s = driver.findElement(By.xpath(prop.getProperty("Cart_Total"))).getText();
		context.setAttribute("Price", s);
		System.out.println(s);
		
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("Proceed_To_Checkout"))).click();
		//WebElement proceed = driver.findElement(By.xpath("//a[@title='Proceed to checkout']"));
		//act.moveToElement(proceed).click();
		//je.executeScript("window.scrollBy(0,700);");
		Thread.sleep(3000);
	}
		
		@Test(dependsOnMethods = {"dress"})
		public void pricecheck(ITestContext context) {
		//String s1 = (String) context.getAttribute("Price");
		String f = driver.findElement(By.xpath(prop.getProperty("Total_Price"))).getText();
		System.out.println(f);
		if (s.equalsIgnoreCase(f)) {
			System.out.println("The price is matching");
			}
		else {
			System.out.println("The price is not matching");
		}
		
		driver.findElement(By.xpath(prop.getProperty("Final_Checkout"))).click();
	}

	@AfterClass
	public void cleanup() {
		
		driver.close();
	}
	
}
