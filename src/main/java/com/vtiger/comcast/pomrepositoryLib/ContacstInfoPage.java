package com.vtiger.comcast.pomrepositoryLib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContacstInfoPage {
	
	public ContacstInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//object repository
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement contactHedder;

	public WebElement getContactHedder() {
		return contactHedder;
	}

}
