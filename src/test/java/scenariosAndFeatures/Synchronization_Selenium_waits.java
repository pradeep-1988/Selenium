package scenariosAndFeatures;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Synchronization_Selenium_waits {
	
WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	
	/* Static wait: Thread.sleep
	 * It stops the execution for the specified time in milliseconds. Our Script waits for given time even if 
	 * synchronization is completed before given time. And that is the Disadvantage of using Static Wait.
	 */
	@Test
	public void testthreadSleep() throws InterruptedException {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@type='submit']")).click(); 
	}
	
	
	/* Dynamic wait: Page load timeout
	 * It is used for Web browser page load synchronization. If page load takes more time, then
	 * TimeOutException is thrown by Webdriver. To overcome this situation we use PageLoadTimeout.
	 */
	
	@Test
	public void testPageLoadTimeOut() {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	}
	
	/* Dynamic wait: Implicitwait
	 * It is used to synchronize all the elements of a webpage. The implicit wait will tell the webdriver to poll the 
	 * DOM for a given time. If element is found within the time period, webdriver will come out of wait whereas if 
	 * element is NOT found within the time period, webdriver will throw NoSuchElementException.
	 */
	
	@Test
	public void testImplicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	}
	
	
	/* Dynamic wait: WebdriverWait (Explicit Wait)
	 * It is used to achieve synchronization for a specific condition such as ElementIsClickable etc. If a specific 
	 * element is taking more time to load we use WebDriver Wait. It polls DOM for the specific element with Dynamic wait.
	 */

	@Test
	public void testExplicitWait() {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		WebElement element = driver.findElement(By.xpath("//input[@type='submit']"));
		
		//explicit wait - to wait for the submit button to be click-able
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	/* Dynamic wait: FluentWait
	 * It provides polling mechanism which is used for how frequently we want to poll for a specific element to appear 
	 * or load. It tries to find the web element repeatedly at regular intervals(as specified in polling period) of time 
	 * until the timeout or till the object is found. It can define the maximum amount of time to wait for a specific 
	 * condition and frequency with which to check the condition before throwing an ElementNotVisibleException exception.
	 */
	@Test
	public void testFluentWait() {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		WebElement element = driver.findElement(By.xpath("//input[@type='submit']"));
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver) //Declare and initialise a fluent wait
				.withTimeout(Duration.ofSeconds(30)) //Specify the timout of the wait
				.pollingEvery(Duration.ofSeconds(3)) //Sepcify polling time
				.ignoring(NoSuchElementException.class); //Specify what exceptions to ignore
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
		element.click();
	}

}
