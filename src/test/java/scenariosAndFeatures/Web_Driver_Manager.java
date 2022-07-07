package scenariosAndFeatures;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Web_Driver_Manager {
	
	/*
	 
	 WebDriverManager – Get Rid Of Compatibility Issues Between Browser And Drivers.
	 While automating a Web-based application using Selenium WebDriver, we must have faced the below issues:-
	 - Unable to create session due to out-dated browser driver executable OR Compatibility issues between currently installed browser and available driver executable in the framework.
	 - Maintaining the same browser version across the team so that your framework can run scripts.
	 - Managing browsers on the different operating system
	 All these issues we can solve using a library called WebDriverManager.
	 
	 In Selenium WebDriver, we know that we need to explicitly download the compatible browser’s driver executable binary file and set the JVM path or properties so that selenium commands can be executed. These browser driver executables work as a bridge between our selenium commands/codes and browser. Below lines of code will explain more about this statement.
	 - System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
	 - System.setProperty("webdriver.gecko.driver", "/path/to/geckodriver");
	 
	 We can auto manage the download of browser executable and setting up of path using WebDriverManager.
	 WebDriverManager is a Java library. So we can either download it or use it as a dependency in a Maven project.
	 
	 It downloads the compatible browser driver executable as per the currently installed browser on your system and stores it on your local itself and an environment path is set up.
	 It will download only if the compatible browser executable is not available in the cache.
	 
	 if we want to use a specific version of driver, we can do that by using 
	 - WebDriverManager.chromedriver().version("2.40").setup();
	 */

	public static void main(String[] args) {
		
		// It will download compatible chrome executable and set the path for it.
		//WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		//WebDriverManager.safaridriver().setup();
		//WebDriverManager.edgedriver().setup();
		
		System.out.println("Browser executable path is set as :- "+System.getProperty("webdriver.gecko.driver"));
		
		//WebDriver driver = new ChromeDriver();
		//WebDriver driver = new SafariDriver();
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.google.com/");
		System.out.println("Title is "+driver.getTitle());
		driver.quit();	

	}

}
