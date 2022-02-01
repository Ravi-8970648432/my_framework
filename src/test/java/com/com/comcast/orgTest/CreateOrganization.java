package com.com.comcast.orgTest;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateOrganization {

	public static void main(String[] args) throws IOException, AWTException {
		WebDriver driver=null;
		try {
		//read common data from property file
		FileInputStream fis=new FileInputStream("./Properties/commonData.properties");

		//create property object for Properties class
		Properties prop=new Properties();
		prop.load(fis);
		String browser=prop.getProperty("Browser");//Browser name
		String url=prop.getProperty("Url");//Url
		String user=prop.getProperty("Username");//Username
		String pwd=prop.getProperty("Password");//Password


		/*int rowCount=sheet.getLastRowNum();
		String columns=null;
		for(int i=1; i<=rowCount; i++)
		{
			Row row = sheet.getRow(i);
			int celCount = row.getLastCellNum();
			for(int j=0; j<celCount; j++)
			{
				Cell cell = row.getCell(j);
				columns = cell.getStringCellValue();
				System.out.println(columns);

			}

		}*/

		//WebDriver driver=null;
		if(browser.toLowerCase().equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(browser.toLowerCase().equals("firefox"))	
		{
			driver=new FirefoxDriver();
		}
		else if(browser.toLowerCase().equals("ie"))	
		{
			driver=new InternetExplorerDriver();
		}
		else if(browser.toLowerCase().equals("firefox"))	
		{
			driver=new FirefoxDriver();
		}
		else if(browser.toLowerCase().equals("edge"))	
		{
			driver=new EdgeDriver();
		}

		//open the browser
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		//login

		WebElement username = driver.findElement(By.name("user_name"));
		username.sendKeys(user);

		WebElement password = driver.findElement(By.name("user_password"));
		password.sendKeys(pwd);

		driver.findElement(By.id("submitButton")).click();

		String homePage_Title = driver.getTitle();
		String actual_Title="Administrator - Home - vtiger CRM 5 - Commercial Open Source CRM";

		if(actual_Title.equals(homePage_Title))
		{
			System.out.println("Home page is displyed");
		}
		else
		{
			WebElement errMsg = driver.findElement(By.className("errorMessage"));
			String error_Message = errMsg.getText();
			System.err.println(error_Message);
		}

		//click on contacts
		WebElement Contacts = driver.findElement(By.linkText("Contacts"));
		Contacts.click();
		//click on plus button
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/btnL3Add.gif\"]")).click();

		//contact headder
		WebElement contact_Header = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		String cHeader = contact_Header.getText();

		System.out.println(cHeader);
		
		

		//read the data from excel
		FileInputStream fis_e=new FileInputStream("./Properties/contact.xlsx");
		Workbook wb = WorkbookFactory.create(fis_e);
		Sheet sheet = wb.getSheet("Sheet1");

		
		String first_name=null;
		String last_name=null;
		String title=null;
		String department=null;
		String email=null;
		int rowCount=sheet.getLastRowNum();
		for(int i=1; i<=rowCount; i++)
		{
			Row row = sheet.getRow(i);
			first_name=row.getCell(0).getStringCellValue();
			last_name=row.getCell(1).getStringCellValue();
			title = row.getCell(2).getStringCellValue();
			department = row.getCell(3).getStringCellValue();
			email = row.getCell(4).getStringCellValue();
			
			//contact information

			WebElement dropList = driver.findElement(By.xpath("//select[@name='salutationtype']"));

			Select select=new Select(dropList);
			select.selectByValue("Mr.");

			//firstname
			WebElement firstname = driver.findElement(By.name("firstname"));
			firstname.sendKeys(first_name);

			//lastname
			WebElement lastname = driver.findElement(By.name("lastname"));
			lastname.sendKeys(last_name);
			//Title
			driver.findElement(By.xpath("//input[@id='title']")).sendKeys(title);
			//department
			driver.findElement(By.xpath("//input[@id='department']")).sendKeys(department);
			//email
			driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
			//click plus icon for two windows
			driver.findElement(By.xpath("(//img[@title='Select'])[1]")).click();

			String parentWindow = driver.getWindowHandle();
			Set<String> childWindows = driver.getWindowHandles();
			//use iterator for set
			Iterator<String> child_Id = childWindows.iterator();

			while(child_Id.hasNext())
			{
				String child=child_Id.next();
				if(!child.equals(parentWindow))
				{
					driver.switchTo().window(child);
					driver.findElement(By.xpath("//a[text()='gooduivtiger']")).click();
					//driver.close();
				}
			}
			driver.switchTo().window(parentWindow);
			//leads dropdown

			WebElement leadDrop = driver.findElement(By.xpath("//select[@name='leadsource']"));
			Select select1=new Select(leadDrop);
			select1.selectByValue("Existing Customer");

			//reports plus icon

			driver.findElement(By.xpath("(//img[@title='Select'])[2]")).click();

			//switch to on child windows

			String parent1 = driver.getWindowHandle();
			Set<String> child1 = driver.getWindowHandles();
			//use iterator
			Iterator<String> child2 = child1.iterator();
			while(child2.hasNext())
			{
				String child3 = child2.next();
				if(!child3.equals(parent1))
				{
					driver.switchTo().window(child3);
					driver.findElement(By.xpath("//a[text()='Patricia Johnson']")).click();
					//driver.close();
				}
			}
			//switch to parent window
			driver.switchTo().window(parent1);

			driver.findElement(By.xpath("//input[@name='donotcall']")).click();
			JavascriptExecutor js=(JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,700)");
			
			driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
			
			//click on plus button
			driver.findElement(By.xpath("//img[@src=\"themes/softed/images/btnL3Add.gif\"]")).click();
			
		}
	
		}catch(Exception e)
		{
			
		}finally {
			
			driver.close();
		}

		

		//driver.findElement(By.xpath("//input[@type='file']")).click();
		//upload image
		/*StringSelection ss=new StringSelection("‪C:\\Users\\hp\\Pictures\\food.JPG");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		driver.findElement(By.xpath("//input[@type='file']")).click();
		//imitate mouse events like CONTROL + C, CONTROL + V
		Robot robot=new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyPress(KeyEvent.VK_ENTER);*/
	}

}
