package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MultiTest1 {

	WebDriver driver;
	
	@BeforeClass
	public void initialization() {
		
	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Driver\\chromedriver.exe");
	
	}
	
	@BeforeMethod
	public void openWebsite() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.get("https://www.yatra.com");
	
	driver.manage().window().maximize(); //maximize our window
	
	System.out.println(driver.getTitle());
	}
	
	@Test
	public void yatra() throws InterruptedException {
		WebElement support = driver.findElement(By.xpath("//a[text()='Support ']"));
		
		Actions act = new Actions(driver);
		
		act.moveToElement(support).perform();
				
	}
	
	@Test
	public void yatra1() throws InterruptedException {
		WebElement support = driver.findElement(By.xpath("//a[text()='Support ']"));
		
		Actions act = new Actions(driver);
		
		act.moveToElement(support).perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Talk To Us']")).click();
		
		Thread.sleep(2000);
		driver.switchTo().frame("iframeChatBot"); //user inside into the frame
		
		driver.findElement(By.xpath("//button[text()='Cancellation']")).click();
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[text()='Start a new chat']")).click();
		
		driver.findElement(By.xpath("//button[text()='Invoices']")).click();
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[text()='Start a new chat']")).click();
		
		driver.findElement(By.xpath("//button[text()='Refunds']")).click();
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[text()='Start a new chat']")).click();
		
		driver.findElement(By.xpath("//button[text()='Modification']")).click();
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[text()='Start a new chat']")).click();
		
		driver.findElement(By.xpath("//button[text()='e-Tickets']")).click();
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[text()='Start a new chat']")).click();
		
		driver.switchTo().defaultContent(); //come out from frame
		
		driver.findElement(By.id("chatbot_close_button")).click();
	}
	
	@AfterMethod
	public void close() {
		driver.close();;
	}
	
	@AfterClass
	public void quit() {
		driver.quit();
	}
}
