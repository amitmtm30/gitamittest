package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.BaseSetup.BaseSetup1;

public class LoginPage extends BaseSetup1{

	//PageFactory

/*	@FindBy(name ="USERID")
	WebElement UserName;

	@FindBy(name = "PASSWORD")
	WebElement Password;

	@FindBy(xpath = "//a[contains(text(),'Sign')]")
	WebElement LoginSubmit;

	public LoginPage(){
		PageFactory.initElements(getDriver(), this);
	}*/

	public String getTitle(){
		String pageTitle = getDriver().getTitle();
		return pageTitle;
	}

	public void LoginApp(String user , String pass){
		try{

		WebElement UserName = getDriver().findElement(By.name("email"));
		WebElement Password = getDriver().findElement(By.name("pass"));
	//	WebElement LoginSubmit = getDriver().findElement(By.xpath("//a[@value='Log In']"));
		UserName.sendKeys(user);
		Password.sendKeys(pass);
	//	LoginSubmit.click();

		WebDriverWait wait = new WebDriverWait(getDriver(), 10);

		Thread.sleep(5000);
		getDriver().quit();

		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception is :- "+e.getMessage());
		}
	}
}
