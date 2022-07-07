package scenariosAndFeatures;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JavaScript_Usage_InSelenium {
	
	/*
	 JavaScript helps us to interact with Webelement where Selenium fails to do so.
	 JavaScript(JS) is a full-fledged dynamic programming language that, when applied to an HTML document, can provide dynamic interactivity on websites.”
	 Every browser is JS enabled by default which we can disable as well. We can run any JS command in a browser. We just need to launch browser console. In console, type any JS command and hit enter.
	 
	 Why we need to execute JS commands in Selenium:
	 Sometimes we will find Selenium commands are not working properly. Below examples are from my working experience on Selenium:
	 - Handling nested web elements. I have seen some toggle buttons , check boxes etc which are nested by some other tags like label etc. Normal selenium command “Click” will not be able to click on toggle button sometimes, as it will find it is not clickable.
	 - Complete area of some web elements like button, checkbox etc are not  clickable. You need to click on specific part of element to perform action. Selenium might fail here some times. In this case also js is very useful.
	 - Handling different types of calendar. There are so many types of calendar which are used by developers in different applications. You may need to write separate methods to handle calendar. Here js will solve your problem.
	 - Scrolling is also a big problem in selenium. Using js, you can scroll by pixels or to the web element.
	 - Handling hidden elements. You can use js to get text or attribute value from hidden web element which can not be done by normal selenium methods.
	 - Drag and drop issues can be handled using js.
	
	 How to run javascript commands in Selenium:
	 Selenium provides an interface named “JavascriptExecutor“, which provides declaration of following two methods:
	 - executeScript: To execute synchronized JavaScript script commands
	 - executeAsyncScript: To execute asynchronous JavaScript commands
	 This "JavascriptExecutor" interface helps to execute js commands using above methods in selenium.
	 To execute the js command in selenim, we need to type cast the driver object to "JavascriptExecutor" interface:
	 - JavascriptExecutor jsObj = (JavascriptExecutor)driver;
	
	
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
	public void SimpleJavaScriptTest() throws InterruptedException {
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("alert('Hello')"); // It would display an alert message with 'Hello' text
		Thread.sleep(2000);
		
	}
	
	/*
	Understand ExecuteScript Method Of JavascriptExecutor:
	executeScript method takes two arguments:
	- javaScript command which you want to run in form of String. It is mandatory.
	- An array of arguments. It is optional. It is same as we have in main() method of java program. These arguments will be used in javaScript command.
	In below test, we have parameterized JavaScript command and passed arguments value as String.
	- executeScript method returns an Object type as it may return different types of values based on javaScript command is executed.
	*/
	
	@Test
	public void UnderstandExecuteScriptMethod() throws InterruptedException {
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("alert('Hello '+arguments[0] +' Welcome to '+arguments[1]);","Pradeep","MakeSeleniumEasy");
		Thread.sleep(2000);
		
		//Verify return type:
		Object response = js.executeScript("return 1===2");
		System.out.println(response);
		
	}
	
	/*
	 When to use javascript commands to locate web element:
	 - We already have enough locators in Selenium webdriver, which help us in identifying web elements on a web page. But sometimes, these locating strategies fail when web element is hidden or it is deeply nested. When we perform any action on these types of web elements, it doesn’t work.
	 - If any website has a lot of AJAX calls, in that case also javascript locating commands are very useful.
	 
	 Javascript provides below command to locate web elements on a web page:
	 - getElementById
	 - getElementsByClassName
	 - getElementsByName
	 - getElementsByTagName
	 If you notice, first command has “element” and remaining three have “elements”. Since, id is unique, so it will always return single element which other attributes like Class Name, Name and Tag Name are not unique, so they might return more than one elements.
	 
	 */
	@Test
	public void ExampleTestFor_getElementById() {
		driver.get("https://www.makemytrip.com/");
		JavascriptExecutor js= (JavascriptExecutor)driver;
		
		// Type casting to WebElement because executeScript returns Object type.
		WebElement element= (WebElement) js.executeScript("return document.getElementById('searchBtn')");
				
		// Getting text
		String text= element.getText();
		System.out.println("Text: "+text);
		
	}
	
	
	@Test
	public void ExampleTestFor_getElementsByClassName() {
		driver.get("https://www.makemytrip.com/");
		JavascriptExecutor js= (JavascriptExecutor)driver;
		
		// Type casting to List<WebElement> because executeScript returns Object type.
		List<WebElement> element= (List<WebElement>) js.executeScript("return document.getElementsByClassName('non-pot')");
				
		// Getting text
		System.out.println(element.size());
		
	}
	
	/*
	 While automating, you may encounter below issues:
	 - Element is not clickable, other element would receive click.
	 - Screenshot of required web element is not captured. <Update>: Solved by Selenium4.
	 - Element is not visible while element is present in DOM.
	 - Unable to capture screen of whole web page vertically or horizontally. <Update>: Solved by Selenium4.
	 
	 All these problems could be solved using scrolling the page up and down properly. Selenium has no default method to scroll web page. But we can achieve this using javascript commands.
	 Note: Almost all browsers scroll to web element on which action needs to be performed by default, but sometimes it does not happen. In this case you need to observe behavior and if does not scroll correctly, you need to do it using JavaScript.
	 Javascript provides below methods to scroll:
	 - scrollBy (x-coord, y-coord)
	 - scrolTo (x-coord, y-coord)
	 - scroll (x-coord, y-coord) : Javascript scroll method is same as scrollTo method. There is no difference in functionality but implementation by browser. Some browsers supports both methods or some will not. Major browsers Chrome, firefox, safari support both methods of javascript to scroll.
	 */
	
	@Test
	public void ScrollToExample() throws InterruptedException {
		driver.get("https://www.makemytrip.com/");
		JavascriptExecutor js= (JavascriptExecutor)driver;
		
		String command1 = "window.scrollTo(0,400)";
		String command2 = "window.scrollTo(0,600)";
		String command3 = "window.scrollTo(0,800)";
		// First scroll vertically
		js.executeScript(command1);
		Thread.sleep(2000);

		// Second scroll vertically
		js.executeScript(command2);
		Thread.sleep(2000);

		// Third scroll vertically
		js.executeScript(command3);
		Thread.sleep(2000);
		
	}
	
	@Test
	public void ScrollByExample() throws InterruptedException {
		driver.get("https://www.makemytrip.com/");
		JavascriptExecutor js= (JavascriptExecutor)driver;
		
		// Since scrollBy works on distance. So whenever below command is called, page will be scrolled down vertically
		String command1 = "window.scrollBy(0,100)";
		
		// First scroll vertically
		js.executeScript(command1);
		Thread.sleep(2000);

		// Second scroll vertically
		js.executeScript(command1);
		Thread.sleep(2000);

		// Third scroll vertically
		js.executeScript(command1);
		Thread.sleep(2000);	
	}
	
	/*
	How To Scroll Page By Page In Selenium WebDriver Using Javascript
	On pressing Key “Page Down”, we have scrolled web page by a page. If we keep pressing “Page Down” key, we will reach bottom of page.
	The same we can achieve using javascript in selenium webdriver:
	- First we need to get page height of one page.
	- Then we need to get how much is scroll-able height. Generally it will be “Height of a page * No of pages”.
	- Now get number of pages. It will be number of times of pressing “Page Down” key to get bottom of page.
	
	*/
	
	@Test
	public void ScrolPageByPage() throws InterruptedException {
		driver.get("http://www.makeseleniumeasy.com/");
		JavascriptExecutor js= (JavascriptExecutor)driver;
		
		// It returns height of view part. You can say it as page height. When you click on page down key, Page scroll by one page. 
		long pageHeight= (long)js.executeScript("return window.innerHeight");
		System.out.println("Page height: "+pageHeight);
		
		// It is how much you can scroll. It is height if you scroll, you will reach to bottom of page.
		long scrollableHeight= (long)js.executeScript("return document.body.scrollHeight");
		System.out.println("Total scrollable height: "+scrollableHeight);
		
		// Finding number of pages. Adding 1 extra to consider decimal part.
		int numberOfPages=(int) (scrollableHeight/pageHeight)+1;
		
		System.out.println("Total pages: "+numberOfPages);
		
		// Now scrolling page by page
		for(int i=0;i<numberOfPages;i++){
			js.executeScript("window.scrollBy(0,"+pageHeight+")");
			Thread.sleep(2000);
			}
	}
	
	
	/* 
	 * To scroll down the web page at the bottom of the page.
	 * Syntax: js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	 * “document.body.scrollHeight” returns the complete height of the body i.e web page.
	 */
	
	@Test
	public void testScrolltoBottom() throws InterruptedException {
	
		driver.get("https://www.guru99.com/scroll-up-down-selenium-webdriver.html");
		JavascriptExecutor js = (JavascriptExecutor) driver;		
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)"); //This will scroll the web page till end.
        Thread.sleep(3000);
	}
	
	/*
	How To Scroll To Any WebElement In Selenium WebDriver i.e. To scroll down the web page by the visibility of the element.
	Syntax: js.executeScript("arguments[0].scrollIntoView();",Element );
	“arguments[0]” means first index of page starting at 0. 
	Where an ” Element ” is the locator on the web page.

	*/
	
	@Test
	public void testScrollByVisibilityofElementUsing_scrollIntoView() throws InterruptedException {
	driver.get("https://www.guru99.com/scroll-up-down-selenium-webdriver.html");
	WebElement Element = driver.findElement(By.xpath("//*[@class='pagenav' and text()='Next']"));
			
	JavascriptExecutor js = (JavascriptExecutor) driver;		
	js.executeScript("arguments[0].scrollIntoView();", Element); //This will scroll the page till the element is found
	Thread.sleep(3000);
		}
	
	/* 
	 * To scroll down the web page by pixel.
	 * Syntax: executeScript("window.scrollBy(x-pixels,y-pixels)");
	 * x-pixels is the number at x-axis, it moves to the left if number is positive and it move to the right if number is negative.
	 * y-pixels is the number at y-axis, it moves to the down if number is positive and it move to the up if number is in negative .
	 */
	
	@Test
	public void testScrollByPixel() throws InterruptedException {
		driver.get("https://www.guru99.com/scroll-up-down-selenium-webdriver.html");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,5000)"); //This  will scroll down the page by 5000 pixel vertical
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,-5000)"); //This  will scroll up the page by 5000 pixel vertical
		Thread.sleep(3000);
	}
	
	/*
	How To Type In Input Box Using Javascript:
	Javascript is the alternative for sendKeys method. First basic question why we would require alternative of sendKeys? You must have encountered some text box which is nested and selenium is not able to interact. Another place I find is selecting a date from calendar. Different applications use different types of calendar and it is difficult to create methods for each type of calendar. Javascript saves you here.
	
	HTML web element has an attribute called “value” which stores the current value. If we type “Amod” in a First Name text box, “value” attribute of text box will be as “value=Pradeep”. Using javascript, we can directly set the value of “value” attribute.
	*/
	
	@Test
	public void TypeUsingJavascript() throws InterruptedException {
		driver.get("https://www.google.com/");
		WebElement searchBox= driver.findElement(By.name("q"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		// Setting value for "value" attribute
		js.executeScript("arguments[0].value='selenium'", searchBox);
		Thread.sleep(3000);
	}

}
