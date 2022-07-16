package scenariosAndFeatures;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Capture_Tooltip {
	
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
	
	 
	 // When the tooltip is available in the ‘title’ attribute of the element itself. In this case, we can simply retrieve the 
	 // tooltip text using findElement and getAttribute(“<attribute name>”) method. 
	
	@Test
	public void getToolTipText() {
		driver.get("https://www.w3schools.com/bootstrap/tryit.asp?filename=trybs_tooltip&stacked=h"); 
		
		//Switching to the iframe as the button is inside an iframe
		 driver.switchTo().frame("iframeResult");
		 
		 WebElement hoverElement =driver.findElement(By.xpath("//a[contains(text(),'Hover over me')]")); 
		 String toolTipText = hoverElement.getAttribute("data-original-title");
	     System.out.println("ToolTip Text is: " + toolTipText);
	}
	
	// When the tooltip is available in another tag. Here, we can retrieve the tooltip using the Actions class and getText() 
	// method if the actual tooltip text is inside a span tag.
	
	@Test
	public void getToolTipText2() {
		driver.get("https://www.w3schools.com/css/tryit.asp?filename=trycss_tooltip"); 
		
		//Switching to the iframe as the button is inside an iframe
		driver.switchTo().frame("iframeResult");
		
		 WebElement hoverElement =driver.findElement(By.xpath("//div[contains(text(),'Hover over me')]")); 
		 Actions actions = new Actions(driver); 
	     actions.moveToElement(hoverElement).build().perform();
	     
	     //Locating the element having ToolTip and getting its text
	     String toolTipText = driver.findElement(By.xpath("//span[text()='Tooltip text']")).getText();
	     System.out.println("ToolTip Text is: " + toolTipText);
		
	}
	
	@Test
	public void getToolTipText3() {
		driver.get("https://www.wikipedia.org/"); 
	
		 WebElement hoverElement =driver.findElement(By.xpath("//strong[text()='English']")); 
		 Actions actions = new Actions(driver); 
	     actions.moveToElement(hoverElement).build().perform();
	     
	     //locating element having the tooltip
	     WebElement toolTip = driver.findElement(By.id("js-link-box-en"));
	     
	     //Using getAttribute method to get the tooltip text
		 String toolTipText = toolTip.getAttribute("title");
		 System.out.println("ToolTip Text is: " + toolTipText);
		
	}

}
