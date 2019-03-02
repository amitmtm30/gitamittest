package test.testng_concept;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.BaseSetup.BaseSetup1;
import com.ExcelUtility.ReadExcel;
import com.pages.LoginPage;

public class Test1 extends BaseSetup1 {

	@DataProvider(name="credentials", parallel=true)
	public Object[][] getData() throws IOException{
		ReadExcel re = new ReadExcel(Paths.get(System.getProperty("user.dir"),"TestData.xlsx").toString());
		int rownum = re.rowGetCount("Sheet1");
		System.out.println(rownum);
		Object[][] data = new Object[rownum-1][3];
		for(int row = 1; row <= rownum-1; row++) {
			for(int col = 0; col <3; col++) {
				data[row-1][col] = re.getData("Sheet1", row, col);
			}
		}
		re.close();
		return data;
	}
	
	@BeforeMethod
	public void beforemethod() throws MalformedURLException, InterruptedException{
		System.out.println("Thread id before method : "+Thread.currentThread().getId());
		Intialize();
	}
	
	@Test(dataProvider="credentials")
	public void TestMethod1(String username, String password, String status) throws IOException{
		System.out.println("username : "+username+"==status--"+ status);
		System.out.println("Thread id : "+Thread.currentThread().getId());
		LoginPage loginPage = new LoginPage();
		loginPage.LoginApp(username, password);
		writeIntoExcel(Paths.get(System.getProperty("user.dir"),"TestData.xlsx").toString(), "Sheet1", username, username+Thread.currentThread().getId(),3);
	}
}
