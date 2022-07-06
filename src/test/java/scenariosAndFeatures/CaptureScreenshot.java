package scenariosAndFeatures;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
 * In Selenium 3, users could take a screenshot of the entire visible page only.
 * But with Selenium 4, users can also take the screenshot of a specific web element as well as full page
 * from top to bottom in case page is scroll-able.
 */

public class CaptureScreenshot {
	
	WebDriver driver;

	@BeforeClass
	public void setUp() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		//WebDriverManager.chromedriver().setup();
		//driver = new ChromeDriver();
		driver.get("https://www.selenium.dev/");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	
	@Test
	public void takeVisiblePageScreenshot() throws IOException {
		//Here we need to type cast webdriver object to TakesScreenshot interface

		//Convert web driver object to TakeScreenshot
		TakesScreenshot srcShot = (TakesScreenshot) driver;

		//Call getScreenshotAs method to create image file
		File SrcFile = srcShot.getScreenshotAs(OutputType.FILE);

		//Move image file to new destination
		File DestFile=new File(System.getProperty("user.dir") + "/screenshots/sel3_FullPage.jpg");

		//Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
	}
	
	
	@Test
	public void takeFullPageScreenshot() {
		/*
		   Now we can take full page screenshots with the getFullPageScreenshotAs() method in Firefox. But
		   instead of typecasting it to the ‘TakesScreenshot’ interface, we need to typecast it to FirefoxDriver
		   instance.
		 */

		FirefoxDriver srcShot = (FirefoxDriver) driver;
		File sourceF = srcShot.getFullPageScreenshotAs(OutputType.FILE);
		File destF=new File(System.getProperty("user.dir") + "/screenshots/el4_FullPage.jpg");
		try {
			FileUtils.copyFile(sourceF, destF);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void takeElementScreesnhot() {
		WebElement eleLink = driver.findElement(By.xpath("//a[@class='navbar-brand']"));
		File srcFile = eleLink.getScreenshotAs(OutputType.FILE);
		File destFile=new File(System.getProperty("user.dir") + "/screenshots/sel4_Element.jpg");
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}
