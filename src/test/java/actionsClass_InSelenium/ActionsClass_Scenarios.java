package actionsClass_InSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ActionsClass_Scenarios {
	/*
	What is Actions Class in Selenium?
		- Actions Class in Selenium is used to handle keyboard and mouse events such as 
			* Double click an element
			* Right-click on an element
			* Drag and Drop and element
			* Moving mouse at the desired offset position
			* Keyboard press and release events
			* Selecting multiple elements using control key and etc
	
	Mouse Actions in Selenium:
		* click() – Clicks at the current mouse location
		* doubleClick(): Performs double click on the element
		* contextClick(): Performs right-click on the mouse
		* dragAndDrop(WebElement source, WebElement target): Drags the element from source location and drops to the target location.
		* moveToElement(WebElement target): Moves the mouse to the center of the element
		* clickAndHold(): Performs long click in the middle of the element
	
	Keyboard Actions in Selenium:
		* sendKeys​(java.lang.CharSequence… keys): Sends keys to the active element.
		* sendKeys(WebElement target, java.lang.CharSequence… keys) : Send keys to the provided element.
		* keyUp(WebElement target, java.lang.CharSequence key): performs a key release after focusing on an element
		* keyDown(WebElement target, java.lang.CharSequence key):performs a keypress after focusing on an element
	
	Note: If you are using more than one action method together you will have to use build() method in conjunction with the respective actions. 
	And eventually, you will have to use the perform() method to perform the currently built action.
	
	How to Define Actions Class in Selenium WebDriver?
		WebDriver driver = new ChromeDriver();
		Actions action = new Actions(driver);
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
	
	@Test(priority=0)
	public void MouseHover_Test() throws InterruptedException {
		driver.get("https://www.w3schools.com/");
		
		//Get the Background color of TUTORIALS Menu link
		WebElement linkTutorial = driver.findElement(By.xpath("//a[@id='navbtn_tutorials']"));
        String bgColor = linkTutorial.getCssValue("background-color");
        System.out.println("Background Color Before Mouse hover: " + bgColor); 
        
        //Moving mouse over the TUTORIALS Menu link and get the background color
        Actions act = new Actions(driver);
        act.moveToElement(linkTutorial).perform();
        String bgColor2 = linkTutorial.getCssValue("background-color");
        System.out.println("Background Color Before Mouse hover: " + bgColor2); 
        Thread.sleep(2000);
	}
	
	@Test(priority=1)
	public void OpenNewTabUsingActionsClass() throws InterruptedException {
		driver.get("https://www.w3schools.com/");
		
		WebElement linkTutorial = driver.findElement(By.xpath("//a[@id='navbtn_exercises']/following-sibling::a[text()='Videos']")); 
		
		Actions act = new Actions(driver);
		act.keyDown(Keys.COMMAND)
			.click(linkTutorial)
			.keyUp(Keys.COMMAND)
			.build()
			.perform();
		
		Thread.sleep(5000);
	}
	
	@Test(priority=2)
	public void RightClick_Test() throws InterruptedException {
		driver.get("https://demo.guru99.com/test/simple_context_menu.html");
		
		WebElement link = driver.findElement(By.cssSelector(".context-menu-one"));
		Actions act = new Actions(driver);
		act.contextClick(link)
			.perform();
		Thread.sleep(2000);
	}
	
	@Test(priority=3)
	public void DoubleClick_Test() throws InterruptedException {
		driver.get("https://demo.guru99.com/test/simple_context_menu.html");
		
		WebElement link =driver.findElement(By.xpath("//button[text()='Double-Click Me To See Alert']"));
		Actions act = new Actions(driver);
		act.doubleClick(link).perform();
		Thread.sleep(1000);
		
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
	}
	
	@Test(priority=4)
	public void DragAndDropAction_Test() throws InterruptedException {
		driver.get("https://javascript.info/article/mouse-drag-and-drop/ball4/");
		
		WebElement dragFrom = driver.findElement(By.xpath("//*[@id=\"ball\"]"));  
        WebElement dropTo = driver.findElement(By.xpath("//*[@id=\"gate\"]")); 
        
        //Performing Drag and drop Action
        Actions act = new Actions(driver);
        act.dragAndDrop(dragFrom, dropTo).build().perform();
        Thread.sleep(2000);
		
	}
	
	@Test(priority=5)
	public void DragAndDropByClickAndHoldAction_Test() throws InterruptedException {
		driver.get("https://javascript.info/article/mouse-drag-and-drop/ball4/");
		
		WebElement dragFrom = driver.findElement(By.xpath("//*[@id=\"ball\"]"));  
        WebElement dropTo = driver.findElement(By.xpath("//*[@id=\"gate\"]")); 
        
        //Performing Drag and drop Action
        Actions act = new Actions(driver);
        act.clickAndHold(dragFrom).moveToElement(dropTo).release(dropTo).build().perform();
        Thread.sleep(2000);
		
	}
	
	@Test(priority=6)
	public void DragAndDropByXYCoordinates_Test() throws InterruptedException {
		driver.get("https://javascript.info/article/mouse-drag-and-drop/ball4/");
		
		WebElement dragFrom = driver.findElement(By.xpath("//*[@id=\"ball\"]"));  
        
        
        //Performing Drag and drop Action using X Y coordinates
        Actions act = new Actions(driver);
        act.dragAndDropBy(dragFrom, -60, -90).perform();
        Thread.sleep(2000);
		
	}
	/*
	
	SendKeys in Selenium Webdriver:
	Why Emulating Keyboard Events is Required in Selenium WebDriver?
		* Copy and Paste: Copying all existing text from any TextBox/ TextArea to another TextBox/ TextArea  by pressing “CTRL+A” and “CTRL+C” followed by “CTRL+V“.
		* Typing Camel Case letters: If you need to type either capital letters or camel case letters like (GoodDay).In such cases, you can press the “SHIFT” key and then type the required characters in the input text field, the letters will be typed in capital letters until the “SHIFT” key is pressed.
		* Pressing Tab: Pressing the “Tab” key.
	
	Note: After performing any keyDown(theKey) method you must call the keyUp(theKey) or sendKeys(Keys.NULL) to release the pressed the key otherwise it would remain in the pressed state.


	
	*/
	
	@Test(priority=7)
	public void CopyAndPaste_Test() throws InterruptedException {
		driver.get("https://www.w3schools.com/html/html_forms.asp");
		
		WebElement fName = driver.findElement(By.id("fname"));
		
		
		// Clear existing value from the first name text box.
		fName.clear();
		
		
		// Setting a new name in First name text box.
		fName.sendKeys("Pradeep");
		
		// Create object of the Actions class
        Actions act = new Actions(driver);
        
		// Select the value of First Name using CTRL + A
        act.keyDown(Keys.COMMAND)
        	.sendKeys("a")
        	.keyUp(Keys.COMMAND)
        	.build().perform();
        Thread.sleep(1000);
		
        // Copy the value of First Name using CTRL + C
        act.keyDown(Keys.COMMAND)
        	.sendKeys("c")
        	.keyUp(Keys.COMMAND)
        	.build().perform();
        Thread.sleep(1000);
        
        // Press the TAB Key to Switch Focus to Last Name
        act.sendKeys(Keys.TAB)
        	.perform();
        Thread.sleep(1000);
		
		// Select the existing value of Last Name using CTRL + A
		act.keyDown(Keys.COMMAND)
			.sendKeys("a")
		    .keyUp(Keys.COMMAND)
		    .build().perform();
		Thread.sleep(1000);
		
		// Paste the value of First Name using CTRL + V
		act.keyDown(Keys.COMMAND)
			.sendKeys("v")
			.keyUp(Keys.COMMAND)
			.build().perform();
		
		Thread.sleep(1000);
	}
	
	
	@Test(priority=8)
	public void TypingCamelCaseLetters() throws InterruptedException {
		driver.get("https://www.w3schools.com/html/html_forms.asp");
		WebElement fName = driver.findElement(By.id("fname"));
		
		// Clear existing value in the first name textbox
		fName.clear();	
        
        // Create object of the Actions class
        Actions act = new Actions(driver);
        act.moveToElement(fName)
        	.click()
        	.keyDown(Keys.SHIFT).sendKeys("p").keyUp(Keys.SHIFT).sendKeys("radeep ")
        	.keyDown(Keys.SHIFT).sendKeys("k").keyUp(Keys.SHIFT).sendKeys("umar ")
        	.keyDown(Keys.SHIFT).sendKeys("j").keyUp(Keys.SHIFT).sendKeys("akhar ")
        	.build().perform();
        
        Thread.sleep(2000);
        
		
	}

}
