package com.vtiger.comcast.contactsTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.vitiger.comcast.genericUtility.BaseClass;
import com.vtiger.comcast.pomrepositoryLib.ContacstInfoPage;
import com.vtiger.comcast.pomrepositoryLib.ContactsPage;
import com.vtiger.comcast.pomrepositoryLib.CreateNewContactPage;
import com.vtiger.comcast.pomrepositoryLib.CreateNewOrganizationPage;
import com.vtiger.comcast.pomrepositoryLib.HomePage;
import com.vtiger.comcast.pomrepositoryLib.OrganizationInfoPage;
import com.vtiger.comcast.pomrepositoryLib.OrganizationsPage;



public class CreateContactTest extends BaseClass {

	@Test (groups= {"SmokeTest", "RegressionTest"})
	public void createContactTest() throws Throwable
	{
		int randomInt = jLib.getRandomNumber();
		/*test script Data*/
		String lastName = eLib.getDataFromExcel("Sheet1", 4, 2) + randomInt;

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
		//Hard Assert
		Assert.assertTrue(contactHeadder.contains(lastName));

	}
	@Test (groups= {"SmokeTest", "RegressionTest"})
	public void createContactWithOrgNameTest() throws Throwable
	{
		int randomInt = jLib.getRandomNumber();
		/*test script Data*/
		String orgName = eLib.getDataFromExcel("Sheet1", 1, 2) + randomInt;
		String lastName = eLib.getDataFromExcel("Sheet1", 4, 2);

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
		//Hard Assert
		Assert.assertTrue(contactHeadder.contains(lastName));
	}
}
