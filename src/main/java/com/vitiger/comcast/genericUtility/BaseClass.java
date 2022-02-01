package com.vitiger.comcast.genericUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.vtiger.comcast.pomrepositoryLib.HomePage;
import com.vtiger.comcast.pomrepositoryLib.LoginPage;

import java_cup.runtime.Symbol;

public class BaseClass {
	public WebDriver driver;
	public static WebDriver sdriver;
	/*Object Creation for Lib*/
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	public FileUtility fLib = new FileUtility();
	public ExcelUtility eLib = new ExcelUtility();
	

	@BeforeSuite (groups= {"SmokeTest", "RegressionTest"})
	public void configBS()
	{
		System.out.println("=========Connect to Db===================");
	}
	//@Parameters("browser")
	@BeforeClass (groups= {"SmokeTest", "RegressionTest"})
	public void configBC() throws Throwable
	{
		System.out.println("==========launch the browser===========");
		String URL = fLib.getPropertyKeyValue("url");
		String BROWSER = fLib.getPropertyKeyValue("browser");

		if(BROWSER.toLowerCase().equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.toLowerCase().equals("firefox"))	
		{
			driver=new FirefoxDriver();
		}
		else if(BROWSER.toLowerCase().equals("ie"))	
		{
			driver=new InternetExplorerDriver();
		}
		else if(BROWSER.toLowerCase().equals("edge"))	
		{
			driver=new EdgeDriver();
		}

		wLib.waitUntilPageLoad(driver);
		driver.get(URL);
		driver.manage().window().maximize();
		sdriver=driver;
	}
	@BeforeMethod (groups= {"SmokeTest", "RegressionTest"})
	public void configBM() throws Throwable
	{
		/*common Data*/
		String USERNAME = fLib.getPropertyKeyValue("username");
		String PASSWORD = fLib.getPropertyKeyValue("password");
		//login
		System.out.println("========login==========");
		LoginPage login=new LoginPage(driver);
		login.loginPage(USERNAME, PASSWORD);

	}
	@AfterMethod (groups= {"SmokeTest", "RegressionTest"})
	public void configAM()
	{
		//logout
		HomePage home=new HomePage(driver);
		home.logOut();

	}
	@AfterClass (groups= {"SmokeTest", "RegressionTest"})
	public void configAC()
	{
		System.out.println("========close the browser==========");
		driver.close();
	}
	@AfterSuite (groups= {"SmokeTest", "RegressionTest"})
	public void configAS()
	{
		System.out.println("========close the Db Connection==========");
	}

}
