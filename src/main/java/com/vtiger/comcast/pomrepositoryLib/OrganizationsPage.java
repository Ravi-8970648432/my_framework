package com.vtiger.comcast.pomrepositoryLib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage { 

	public OrganizationsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//img[@title='Create Organization...']")
	private WebElement createOrg;

	//search box
	@FindBy(name="search_text")
	private WebElement SearchBox;

	//searchbutton
	@FindBy(name="search")
	private WebElement SearchBtn;

	public WebElement getSearchBtn() {
		return SearchBtn;
	}
	public WebElement getSearchBox() {
		return SearchBox;
	}
	public WebElement getCreateOrg() {
		return createOrg;
	}


}
