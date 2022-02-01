package com.vtiger.comcast.organizationTest;

import static org.junit.Assume.assumeNoException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import com.vitiger.comcast.genericUtility.ExcelUtility;
import com.vitiger.comcast.genericUtility.FileUtility;
import com.vitiger.comcast.genericUtility.JavaUtility;
import com.vitiger.comcast.genericUtility.WebDriverUtility;
import com.vtiger.comcast.pomrepositoryLib.CreateNewOrganizationPage;
import com.vtiger.comcast.pomrepositoryLib.HomePage;
import com.vtiger.comcast.pomrepositoryLib.LoginPage;
import com.vtiger.comcast.pomrepositoryLib.OrganizationInfoPage;
import com.vtiger.comcast.pomrepositoryLib.OrganizationsPage;

public class CreateOrgNameWithIndustryTypeEducation {

		@Test
		public void createOrgNameWithIndustryTypeEducation() throws Throwable {
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
			String industryType = eLib.getDataFromExcel("Sheet1", 7, 3);

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
			
			//Industry type
			orgname.industryType(industryType);
			
			//save
			orgname.getSaveBtn().click();
			
			//Org Header verification
			OrganizationInfoPage oif=new OrganizationInfoPage(driver);
			String OrgHeadder=oif.getOrgHedder().getText();
			if(OrgHeadder.contains(orgName))
			{
				System.out.println("Organization created succesfully==> PASS");
			}
			else
			{
				System.out.println("Organization is not created succesfully==> FAIL");
			}
			
			String industryText=oif.getIndustryText().getText();
			if(industryText.equals(industryType))
			{
				System.out.println("Organization created Successfully with Industry Type "+industryText);
			}
			else
			{
				System.out.println("Organization not created Successfully with Industry Type "+industryText);
			}
			
			//logout
			home.logOut();
			
			// close browser
			driver.close();
	}

}
