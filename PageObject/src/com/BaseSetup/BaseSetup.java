package com.BaseSetup;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;

public class BaseSetup {

	public static Properties prop = new Properties();
	public static WebDriver driver;

/*	public BaseSetup(){
		try{
			String FilePath = "config.properties";
			FileInputStream file = new FileInputStream(FilePath);
			prop.load(file);
			System.out.println("Base setup");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception is :- "+e.getMessage());
		}
	}
*/
	public static void intialize(){
		System.out.println("In this initailze !!!!");
		try{
			String FilePath = "config.properties";
			FileInputStream file = new FileInputStream(FilePath);
			prop.load(file);
			System.out.println("Base setup");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception is :- "+e.getMessage());
		}
		String BrowserName = prop.getProperty("browserName");
		if(driver == null){
			if(BrowserName.equalsIgnoreCase("Chrome")){
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\gur40062\\Desktop\\chromedriver_win32\\chrome_new32\\chromedriver.exe");
				driver = new ChromeDriver();
				driver.get(prop.getProperty("URL"));
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//not able to intialize the webElements
			//PageFactory.initElements(driver, HomePage.class);
			//PageFactory.initElements(driver, LoginPage.class);
		}
	}

	@Parameters({"browser"})
	@BeforeClass
	public static void Testintialize(String BrowserName){
		System.out.println("In this initailze !!!!");
		try{
			String FilePath = "config.properties";
			FileInputStream file = new FileInputStream(FilePath);
			prop.load(file);
			System.out.println("Base setup");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception is :- "+e.getMessage());
		}
		System.out.println("In intialize Method Browser is :- "+BrowserName);
		try{
		if(driver == null){
			if(BrowserName.equalsIgnoreCase("Chrome")){
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\gur40062\\Desktop\\chromedriver_win32\\chrome_new32\\chromedriver.exe");
				driver = new ChromeDriver();
				driver.get(prop.getProperty("URL"));
				driver.manage().window().maximize();
				driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			}
			//not able to intialize the webElements
			//PageFactory.initElements(driver, HomePage.class);
			//PageFactory.initElements(driver, LoginPage.class);
		else if(BrowserName.equalsIgnoreCase("IE")){
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setJavascriptEnabled(true);
			capabilities.setBrowserName("internet explorer");
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability("ignoreZoomSetting", true);
			capabilities.setCapability("ignoreProtectedModeSettings", true);
			System.setProperty("webdriver.ie.driver", "D:\\old user data\\SeleniumFiles\\IEDriverServer.exe");
			driver = new InternetExplorerDriver(capabilities);
			driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, "0"));

			driver.navigate().to(prop.getProperty("URL"));
			Thread.sleep(15000);
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);


		}

		}
			}catch(Exception e){
			e.printStackTrace();System.out.println("Exception is :- "+e.getMessage());
		}
	}

	@AfterTest
	public void tearDown(){
		System.out.println("Inside tear Down !!!!");
		if(driver != null){
			driver.quit();
			driver = null;
		}
	}


}
