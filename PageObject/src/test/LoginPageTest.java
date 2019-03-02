package test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.BaseSetup.BaseSetup;
import com.pages.LoginPage;

public class LoginPageTest extends BaseSetup{

	LoginPage loginPage;
	public LoginPageTest(){
		super();
	}
	
	@DataProvider(name="credentials")
	public void getData(){
		
	}
	

	@BeforeTest
	public void setup(){
		new LoginPageTest();
		intialize();
	}

	@Test
	public void LoginFunction(){
		loginPage = new LoginPage();
		loginPage.LoginApp(prop.getProperty("userName"),prop.getProperty("password"));
	}


	@AfterTest
	public void close(){
		tearDown();
	}

}
