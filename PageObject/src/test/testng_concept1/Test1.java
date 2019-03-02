package test.testng_concept1;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class Test1 {

	@BeforeTest
	public void BeforeTest1(){
		System.out.println("Test1 ================Before Test1");
	}

	@BeforeClass
	public void BeforeClass1(){
		System.out.println("Test1==================Before Class1");
	}

	/*@Test
	public void test(){
		System.out.println("Test1=======================Test Method");
	}
*/
	@AfterClass
	public void AfterClass1(){
		System.out.println("Test1=========================After Class1");
	}

	@AfterTest
	public void AfterTest1(){
		System.out.println("Test1=========================After Test1");
	}

}
