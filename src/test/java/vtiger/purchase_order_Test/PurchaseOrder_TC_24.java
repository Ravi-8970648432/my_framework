package vtiger.purchase_order_Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class PurchaseOrder_TC_24 {

	public static void main(String[] args) throws IOException {

		System.setProperty("webdriver.edge.driver", "D:\\SDET29\\comcast_selenium_framework\\msedgedriver.exe");

		//Read common data from properties file
		FileInputStream fis=new FileInputStream("./Properties/commonData.properties");

		//create property object
		Properties prop=new Properties();
		prop.load(fis);
		String browser=prop.getProperty("Browser");
		String url=prop.getProperty("Url");
		String userName=prop.getProperty("Username");
		String password=prop.getProperty("Password");

		WebDriver driver=null;
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
		else if(browser.toLowerCase().equals("edge"))
		{
			driver=new EdgeDriver();
		}
		//open the browser with url
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);
		// login page
		//username
		WebElement User = driver.findElement(By.name("user_name"));
		User.sendKeys(userName);
		//password
		WebElement pwd = driver.findElement(By.name("user_password"));
		pwd.sendKeys(password);

		//login Button
		driver.findElement(By.id("submitButton")).click();

		//Homepage
		//mouse over on more link

		WebElement more = driver.findElement(By.xpath("//td[@class='tabUnSelected']//a[contains(text(),'More')]"));

		//using action class for mouse over
		Actions action=new Actions(driver);
		action.moveToElement(more).perform();

		//click on purchase order
		driver.findElement(By.xpath("//a[text()='Purchase Order']")).click();

		//Click on Plus button to create purchase order
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		//purchase order information
		driver.findElement(By.name("subject")).sendKeys("FirstOrder");
		driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]")).click();

		String parentWindow=driver.getWindowHandle();
		Set<String> ChildWindows=driver.getWindowHandles();

		//using itertor
		Iterator<String> it=ChildWindows.iterator();
		while(it.hasNext())
		{
			String child=it.next();

			if(!parentWindow.equals(child))
			{
				driver.switchTo().window(child);
				driver.findElement(By.xpath("//a[text()='Linda']")).click();
				//driver.close();
			}
		}
		//switch to parent window

		driver.switchTo().window(parentWindow);
		//selct drop down
		WebElement list = driver.findElement(By.xpath("(//select[@class='small'])[3]"));
		Select select=new Select(list);
		select.selectByVisibleText("Approved");

		//address area1
		WebElement address1 = driver.findElement(By.xpath("(//textarea[@rows='2'])[1]"));
		address1.clear();
		address1.sendKeys("Katriguppe 2nd stage Bangalore");

		//select radio button
		driver.findElement(By.xpath("//input[@name='assigntype' and @value='T']")).click();

		//Select dropdown
		/*WebElement list1 = driver.findElement(By.xpath("(//select[@class='small'])[3]"));
		Select select1=new Select(list1);
		select1.selectByVisibleText("Support Group");*/
		driver.findElement(By.xpath("(//select[@class='small'])[3]")).click();

		List<WebElement>list1=driver.findElements(By.xpath("//select[@name='assigned_group_id']//option"));
		for (WebElement webElement : list1) {
			String lists=webElement.getText();
			//System.out.println(lists);
			if(lists.equals("Support Group"))
			{
				webElement.click();
			}

		}

		//address area1
		WebElement address2 = driver.findElement(By.xpath("(//textarea[@rows='2'])[2]"));
		address2.clear();
		address2.sendKeys("Katriguppe 3rd stage Bangalore");



		//click on item icon
		driver.findElement(By.xpath("//img[@id='searchIcon1']")).click();

		//item window

		String parentWindow1=driver.getWindowHandle();
		Set<String> ChildWindows1=driver.getWindowHandles();



		//using itertor
		Iterator<String> itw=ChildWindows1.iterator();
		while(itw.hasNext())
		{
			String child1=itw.next();

			if(!parentWindow1.equals(child1))
			{
				driver.switchTo().window(child1);
				driver.findElement(By.xpath("//input[@value='59']")).click();
				driver.findElement(By.xpath("//input[@value='Select Products']")).click();

				//driver.close();
			}
		}
		//switch to parent window

		driver.switchTo().window(parentWindow1);
		//enter quantity
		driver.findElement(By.xpath("//input[@id='qty1']")).sendKeys("20");

		//click save button
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();


		//purchase order confirmation
		WebElement headerText = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		String pur_Order_Text = headerText.getText();

		System.out.println("Purchase order Succesfully Created: "+pur_Order_Text);

		WebElement text = driver.findElement(By.xpath("//a[text()='Support Group']"));
		String txt=text.getText();
		String expectedText="Support Group";
		if(txt.equals(expectedText))
		{
			System.out.println("order assigned to :"+txt);
		}
		else
		{
			System.out.println("order  not assigned to Support Group");
		}


	}

}
