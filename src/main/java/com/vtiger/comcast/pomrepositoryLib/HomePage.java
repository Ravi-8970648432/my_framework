package com.vtiger.comcast.pomrepositoryLib;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vitiger.comcast.genericUtility.WebDriverUtility;

public class HomePage extends WebDriverUtility {
	
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//page object repository
	
	//Organization link
	@FindBy(linkText="Organizations")
	private WebElement orgLink;
	
	//Contacts link 
	@FindBy(linkText="Contacts")
	private WebElement contactsLink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	@FindBy(linkText="Sign Out")
	private WebElement sinOut;

	public WebElement getContactsLink() {
		return contactsLink;
	}

	public WebElement getOrgLink() {
		return orgLink;
	}
	
	public WebElement getAdminImg() {
		return adminImg;
	}

	public WebElement getSinOut() {
		return sinOut;
	}

	public void logOut()
	{
		WebDriverUtility wLib = new WebDriverUtility();
		wLib.mouseOver(driver, adminImg);
		sinOut.click();
	}
	
	
	

}
