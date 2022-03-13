package AutomationPracticeWebsite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class automationpracticewebsiteTest {

	WebDriver driver;
	
	//Actions act = new Actions(driver);
	@BeforeClass
	public void initialization() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\muthu\\Desktop\\SeleniumJars\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php");
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void women() throws InterruptedException {
		WebElement women = driver.findElement(By.xpath("//a[text()='Women']"));
		Actions act = new Actions(driver);
		act.moveToElement(women).perform();
		driver.findElement(By.xpath("//a[text()='Casual Dresses']")).click();
		Thread.sleep(2000);
	}
	
	@Test(dependsOnMethods = { "women" })
	public void dress(ITestContext context) throws InterruptedException {
		//WebElement PrintedDress = driver.findElement(By.className("product-image-container"));
		WebElement PrintedDress = driver.findElement(By.xpath("//img[@title='Printed Dress']"));
		Actions act = new Actions(driver);
		Thread.sleep(2000);
		//JavascriptExecutor je = (JavascriptExecutor) driver;
		//je.executeScript("window.scrollBy(0,700);");
		//je.executeScript("arguments[0].ScrollIntoView(true);", PrintedDress);
		act.moveToElement(PrintedDress).perform();
		driver.findElement(By.xpath("//span[text()='Add to cart']")).click();
		Thread.sleep(3000);
		String s = driver.findElement(By.xpath("(//span[contains(@class,'cart_total')])[3]")).getText();
		context.setAttribute("Price", s);
		System.out.println(s);
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@title='Proceed to checkout']")).click();
		//WebElement proceed = driver.findElement(By.xpath("//a[@title='Proceed to checkout']"));
		//act.moveToElement(proceed).click();
		//je.executeScript("window.scrollBy(0,700);");
		Thread.sleep(3000);
	}
		
		@Test(dependsOnMethods = {"dress"})
		public void pricecheck(ITestContext context) {
		String s1 = (String) context.getAttribute("Price");
		String f = driver.findElement(By.xpath("//span[@id='total_price']")).getText();
		System.out.println(f);
		if (s1.equalsIgnoreCase(f)) {
			System.out.println("The price is matching");
			}
		else {
			System.out.println("The price is not matching");
		}
		
		driver.findElement(By.xpath("//span[text()='Proceed to checkout']")).click();
	}

	@AfterClass
	public void cleanup() {
		
		driver.close();
	}
	
}
