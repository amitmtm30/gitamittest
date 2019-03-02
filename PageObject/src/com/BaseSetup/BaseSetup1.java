package com.BaseSetup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseSetup1 {

	//public static ThreadLocal<RemoteWebDriver> ThreadDriver = new ThreadLocal<RemoteWebDriver>();
	public static ThreadLocal<WebDriver> ThreadDriver = new ThreadLocal<WebDriver>();
	public static Properties prop = new Properties();

	//@Parameters({"browser"})
	public void Intialize() throws InterruptedException, MalformedURLException{
		String BrowserName = "Chrome";
		RemoteWebDriver driver = null;

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

		if(BrowserName.equalsIgnoreCase("Chrome")){
			String chromeDriverPath = Paths.get(System.getProperty("user.dir"),"driver", "chromedriver.exe").toString();
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities = DesiredCapabilities.chrome();
			capabilities.setBrowserName("chrome");
			capabilities.setPlatform(Platform.VISTA);
			//driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
			driver = new ChromeDriver();
		}
		else if(BrowserName.equalsIgnoreCase("IE")){
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setJavascriptEnabled(true);
			capabilities.setBrowserName("internet explorer");
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setPlatform(Platform.VISTA);
			capabilities.setCapability("ignoreZoomSetting", true);
			capabilities.setCapability("ignoreProtectedModeSettings", true);
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
			driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, "0"));

		}
		setDriver(driver);
		getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        getDriver().get(prop.getProperty("URL"));
		Thread.sleep(3000);

	}

	public WebDriver getDriver(){
		return ThreadDriver.get();
	}

	public void setDriver(RemoteWebDriver driver){
		ThreadDriver.set(driver);
	}
	
	protected synchronized void writeIntoExcel(String ExcelPath, String sheetName, String variableName,
			String variableValue, int cellNo) throws IOException {
		XSSFWorkbook xwb;
		XSSFSheet xsheet;
		FileOutputStream fos;
		FileInputStream fis;
		File file;
		try {
			file = new File(ExcelPath);
			fis = new FileInputStream(file);
			xwb = new XSSFWorkbook(fis);
			XSSFRow rowindex = null;
			XSSFCell cell = null;
			String data;
			xsheet = xwb.getSheet(sheetName);
			int totalRows = xsheet.getLastRowNum();
			for (int i = 0; i <= totalRows; i++) {
				rowindex = xsheet.getRow(i);
				cell = rowindex.getCell(0);
				data = cell.getStringCellValue();
				if (data.equalsIgnoreCase(variableName)) {
					cell = rowindex.getCell(cellNo);
					if (cell == null || cell.getCellType() == CellType.BLANK) {
						cell = rowindex.createCell(cellNo);
					}
					cell.setCellType(CellType.STRING);
					cell.setCellValue(variableValue);
					break;
				}
			}
			fos = new FileOutputStream(file);
			xwb.write(fos);
			fos.flush();
			fos.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception: " + e.getMessage());
		}
	}

}
