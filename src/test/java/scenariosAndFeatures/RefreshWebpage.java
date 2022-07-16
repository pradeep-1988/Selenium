package scenariosAndFeatures;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RefreshWebpage {
	
	/*
	4 Ways to Refresh Page In Selenium Webdriver:
		* driver.navigate().refresh() command
		* driver.get(driver.getCurrentUrl()) command
		* Keyboard events or Send Keys method
		* driver.navigate.to(driver.getCurrentURL()) command
	
	*/
	
	WebDriver driver;

	@BeforeClass
	public void setUp() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void RefreshPageInSelenium() throws InterruptedException {
		driver.get("https://www.google.com/");
		Thread.sleep(2000);
		System.out.println("Refreshing page");
		
		// Refresh command
		driver.navigate().refresh();
		
		System.out.println("Page Refreshed");
		Thread.sleep(2000);
	}
	
	@Test
	public void RefreshPageInSelenium2() throws InterruptedException {
		driver.get("https://www.google.com/");
		Thread.sleep(2000);
		System.out.println("Refreshing page");
		
		// Refresh command
		driver.get(driver.getCurrentUrl());
		
		System.out.println("Page Refreshed");
		Thread.sleep(2000);
	}
	
	@Test
	public void RefreshPageInSelenium3() throws InterruptedException {
		driver.get("https://www.google.com/");
		Thread.sleep(2000);
		System.out.println("Refreshing page");
		
		// Refreshing the page by pressing F5 Keys
		driver.findElement(By.name("q")).sendKeys(Keys.F5); 
		
		System.out.println("Page Refreshed");
		Thread.sleep(2000);
	}
	
	@Test
	public void RefreshPageInSelenium4() throws InterruptedException {
		driver.get("https://www.google.com/");
		Thread.sleep(2000);
		System.out.println("Refreshing page");
		
		// Refresh command
		driver.navigate().to(driver.getCurrentUrl());
		
		System.out.println("Page Refreshed");
		Thread.sleep(2000);
	}

	

}
