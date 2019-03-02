package test.testng_concept1;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Test2 extends Test1{


	@BeforeTest
	public void BeforeTest3(){
		System.out.println("Test2 ================Before Test2");
	}

	@Override
	@BeforeClass
	public void BeforeClass1(){
		System.out.println("Test2==================Before Class2");
	}

	@Test
	public void test2(){
		System.out.println("Test2=======================Test Method 2");
	}

	@Override
	@AfterClass
	public void AfterClass1(){
		System.out.println("Test2=========================After Class2");
	}


	@AfterTest
	public void AfterTest3(){
		System.out.println("Test2=========================After Test2");
	}



}
