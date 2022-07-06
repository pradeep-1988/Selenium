package scenariosAndFeatures;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
When we launch a browser without window or UI that is called a headless browser.
A headless browser gives you a real browser context without the memory overhead of running a full version of a browser. All major browsers like Chrome, Firefox, Microsoft Edge support headless mode.

Running automated UI scripts in a headless browser may lower test execution time but not that much significantly. You can experience a little better performance during automated UI scripts execution. But using a headless browser we can minimize the chances of encountering system configuration issues like memory, the abrupt closure of the browser, etc. Execution of scripts in a headless browser is helpful when you don’t have any grid set up as it allows you to perform other tasks with scripts executions.
There are two ways of launching a Chrome browser in headless mode:-
- Using setHeadless() method of ChromeOption class
- Using addArguments() method of ChromeOption class
Whatever way you use make sure you set window size as well. It helps in the smooth running of scripts, better resolution, and screen capturing.
*/
public class LaunchBrowser_HeadlessMode {
	
	WebDriver driver;

	@BeforeClass
	public void setUp() {
		
		//WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		
		//ChromeOptions option=new ChromeOptions();
		FirefoxOptions option=new FirefoxOptions();
		
		// Set headless option/argument
		option.setHeadless(true);
		// OR - No need to add '--'
		//option.addArguments("headless");
		
		// You should set window size for better resolution and screen capture
		option.addArguments("window-size=1200x600");
				
		//driver = new ChromeDriver(option);
		driver = new FirefoxDriver(option);
		
	}
	
	

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	
	
	@Test
	public void OpenWebSite() {
		driver.get("https://www.selenium.dev/");
		System.out.println("Title is : "+driver.getTitle());
		
	}
	
	@Test(dependsOnMethods= {"OpenWebSite"})
	public void CaptureScreenshot() throws IOException {
		TakesScreenshot srcShot = (TakesScreenshot) driver;
		File SrcFile = srcShot.getScreenshotAs(OutputType.FILE);
		File DestFile=new File(System.getProperty("user.dir") + "/screenshots/HeadLessBrowser_FullPage1.jpg");
		FileUtils.copyFile(SrcFile, DestFile);
	}

}
