package com.vtiger.comcast.pomrepositoryLib;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	
	public OrganizationInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//object repository
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement orgHedder;
	
	@FindBy(xpath="//span[@id='dtlview_Industry']")
	private WebElement industryText;

	public WebElement getOrgHedder() {
		return orgHedder;
	}

	public WebElement getIndustryText() {
		return industryText;
	}
	
	
}
