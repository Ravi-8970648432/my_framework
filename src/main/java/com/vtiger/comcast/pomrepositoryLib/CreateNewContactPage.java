package com.vtiger.comcast.pomrepositoryLib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vitiger.comcast.genericUtility.WebDriverUtility;

public class CreateNewContactPage extends WebDriverUtility {
	WebDriver driver;
	
	public CreateNewContactPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name="lastname")
	private WebElement contactLastname;
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	private WebElement OrgNamePlus;
	
	public WebElement getOrgNamePlus() {
		return OrgNamePlus;
	}

	@FindBy(xpath="//input[@title=\"Save [Alt+S]\"]")
	private WebElement saveBtn;


	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getContactLastname() {
		return contactLastname;
	}
	/**
	 * 
	 * @param lastName
	 */
	public void contactLastName(String lastName)
	{
		contactLastname.sendKeys(lastName);
		saveBtn.click();
	}
	/**
	 * 
	 * @param lastName
	 * @param orgName
	 */
	public void contactLastName(String lastName, String orgName)
	{
		contactLastname.sendKeys(lastName);
		OrgNamePlus.click();
		switchToWindow(driver, "Accounts&action");
		OrganizationsPage ogn=new OrganizationsPage(driver);
		ogn.getSearchBox().sendKeys(orgName);
		ogn.getSearchBtn().click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		switchToWindow(driver, "Contacts&action");
		saveBtn.click();
		
	}
}
