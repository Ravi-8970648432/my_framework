package com.vtiger.comcast.organizationTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.vitiger.comcast.genericUtility.BaseClass;
import com.vtiger.comcast.pomrepositoryLib.CreateNewOrganizationPage;
import com.vtiger.comcast.pomrepositoryLib.HomePage;
import com.vtiger.comcast.pomrepositoryLib.OrganizationInfoPage;
import com.vtiger.comcast.pomrepositoryLib.OrganizationsPage;



public class CreateOrgTest extends BaseClass {

	@Test (groups= {"SmokeTest", "RegressionTest"})
	public void createOrgTest() throws Throwable
	{
		int randomInt = jLib.getRandomNumber();

		/*test script Data*/
		String orgName = eLib.getDataFromExcel("Sheet1", 1, 2) + randomInt;

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

		//Org Header verification
		OrganizationInfoPage oif=new OrganizationInfoPage(driver);
		String OrgHeadder=oif.getOrgHedder().getText();
		//Hard Assert
		Assert.assertTrue(OrgHeadder.contains(orgName));
		
		
	}
	@Test (groups= {"SmokeTest", "RegressionTest"})
	public void createOrgIndustryTypeTest() throws Throwable
	{
		int randomInt = jLib.getRandomNumber();
		/*test script Data*/
		String orgName = eLib.getDataFromExcel("Sheet1", 1, 2) + randomInt;
		String industryType = eLib.getDataFromExcel("Sheet1", 7, 3);
		
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
		//Hard Assert
		Assert.assertTrue(OrgHeadder.contains(orgName));
		
		String industryText=oif.getIndustryText().getText();
		//soft assert
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(industryText, industryType, "IndustryText Not equal");
		soft.assertAll();
	}


}
