package com.vtiger.comcast.contactsTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import com.vitiger.comcast.genericUtility.ExcelUtility;
import com.vitiger.comcast.genericUtility.FileUtility;
import com.vitiger.comcast.genericUtility.JavaUtility;
import com.vitiger.comcast.genericUtility.WebDriverUtility;

import com.vtiger.comcast.pomrepositoryLib.ContacstInfoPage;
import com.vtiger.comcast.pomrepositoryLib.ContactsPage;
import com.vtiger.comcast.pomrepositoryLib.CreateNewContactPage;
import com.vtiger.comcast.pomrepositoryLib.HomePage;
import com.vtiger.comcast.pomrepositoryLib.LoginPage;


public class CreateContacts {

	@Test
	public void createContacts() throws Throwable {
		/*Object Creation for Lib*/
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();

		int randomInt = jLib.getRandomNumber();

		/*common Data*/
		String USERNAME = fLib.getPropertyKeyValue("username");
		String PASSWORD = fLib.getPropertyKeyValue("password");
		String URL = fLib.getPropertyKeyValue("url");
		String BROWSER = fLib.getPropertyKeyValue("browser");

		/*test script Data*/
		String lastName = eLib.getDataFromExcel("Sheet1", 4, 2) + randomInt;


		/* Navigate to app*/
		WebDriver driver = null;
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
		else if(BROWSER.toLowerCase().equals("firefox"))	
		{
			driver=new FirefoxDriver();
		}
		else if(BROWSER.toLowerCase().equals("edge"))	
		{
			driver=new EdgeDriver();
		}

		wLib.waitUntilPageLoad(driver);
		driver.get(URL);
		driver.manage().window().maximize();

		//login

		LoginPage login=new LoginPage(driver);
		login.loginPage(USERNAME, PASSWORD);

		//click Contacts link
		HomePage home=new HomePage(driver);
		home.getContactsLink().click();

		//Click Contacts plus button
		ContactsPage cp=new ContactsPage(driver);
		cp.getCreateContacts().click();

		// Create contacts name
		CreateNewContactPage cc=new CreateNewContactPage(driver);
		cc.contactLastName(lastName);

		//Contact Header verification
		ContacstInfoPage ci=new ContacstInfoPage(driver);
		String contactHeadder=ci.getContactHedder().getText();
		if(contactHeadder.contains(lastName))
		{
			System.out.println("Contacts created succesfully==> PASS");
		}
		else
		{
			System.out.println("Contacts is not created succesfully==> FAIL");
		}

		//logout
		home.logOut();

		// close browser
		driver.close();

	}

}
