package com.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MultiTest {

	WebDriver driver;
	Properties prop = new Properties();

	@BeforeClass
	public void Initalization() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\properties\\config.properties");
		prop.load(fis);
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Driver\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get(prop.getProperty("QA_Url"));

		driver.manage().window().maximize(); // maximize our window

		System.out.println(driver.getTitle());
	}

	@Test
	public void SelectDropDown() {

		WebElement seldropdown = driver.findElement(By.id(prop.getProperty("dropdownbox_id")));

		Select sel = new Select(seldropdown);

		sel.selectByIndex(4);

		sel.selectByValue("search-alias=sporting");

		sel.selectByVisibleText("Under $10");

	}

	@Test(dependsOnMethods = "SelectDropDown")
	public void AdvanceDropDown() {

		WebElement seldropdown = driver.findElement(By.id(prop.getProperty("dropdownbox_id")));

		Select sel = new Select(seldropdown);

		List<WebElement> li = sel.getOptions();
		System.out.println(li.size());

		for (int i = 0; i < li.size(); i++) {

			li.get(i).click();
			String s = li.get(i).getText();
			System.out.println(s);
			if (s.equals("Books")) {
				break;
			}

		}

	}

	@AfterClass
	public void cleanup() {
		driver.close();
	}

}
