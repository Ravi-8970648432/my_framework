package com.vtiger.comcast.pomrepositoryLib;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vitiger.comcast.genericUtility.WebDriverUtility;

public class CreateNewOrganizationPage extends WebDriverUtility {


	public CreateNewOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	
	//object repository

		@FindBy(name="accountname")
		private WebElement orgName;
		
		@FindBy(name="industry") 
		private WebElement industryEle;
		
		public WebElement getIndustryEle() {
			return industryEle;
		}
		
		@FindBy(xpath="//input[@title=\"Save [Alt+S]\"]")
		private WebElement saveBtn;

		public WebElement getOrgName() {
			return orgName;
		}
		public WebElement getIndustryType() {
			return industryEle;
		}
		
		public WebElement getSaveBtn() {
			return saveBtn;
		}
		/**
		 * 
		 * @param OrgName
		 */

		public void sendOrgName(String OrgName)
		{
			orgName.sendKeys(OrgName);
		}
		/**
		 * 
		 * @param industryType
		 */
		public void industryType(String industryType)
		{
			select(industryEle, industryType);
		}
	
	

}
