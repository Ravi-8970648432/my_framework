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
import com.vtiger.comcast.pomrepositoryLib.CreateNewOrganizationPage;
import com.vtiger.comcast.pomrepositoryLib.HomePage;
import com.vtiger.comcast.pomrepositoryLib.LoginPage;
import com.vtiger.comcast.pomrepositoryLib.OrganizationInfoPage;
import com.vtiger.comcast.pomrepositoryLib.OrganizationsPage;

public class CreateContactWithOrgName {

	@Test
	public void createContactWithOrgName() throws Throwable {
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
		String orgName = eLib.getDataFromExcel("Sheet1", 1, 2) + randomInt;
		String lastName = eLib.getDataFromExcel("Sheet1", 4, 2);


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

		//click Organization link
		HomePage home=new HomePage(driver);
		home.getOrgLink().click();

		//Click plus button
		OrganizationsPage org=new OrganizationsPage(driver);
		org.getCreateOrg().click();

		// organization name
		CreateNewOrganizationPage orgname=new CreateNewOrganizationPage(driver);
		orgname.sendOrgName(orgName);

		//save
		orgname.getSaveBtn().click();

		//ExplicitlyWait
		OrganizationInfoPage ewait=new OrganizationInfoPage(driver);
		wLib.waitForElementVisibility(driver, ewait.getOrgHedder());

		//navigate to contact page
		home.getContactsLink().click();

		//Click Contacts plus button
		ContactsPage cp=new ContactsPage(driver);
		cp.getCreateContacts().click();

		// Create contacts name
		CreateNewContactPage cc=new CreateNewContactPage(driver);
		cc.contactLastName(lastName, orgName);

		
		//Contact Header verification
		ContacstInfoPage ci=new ContacstInfoPage(driver);
		String contactHeadder=ci.getContactHedder().getText();
		if(contactHeadder.contains(lastName))
		{
			System.out.println("Contacts created with Organization succesfully==> PASS");
		}
		else
		{
			System.out.println("Contacts  not created with Organization succesfully==> FAIL");
		}

		//logout
		home.logOut();

		// close browser
		driver.close();

	}

}
