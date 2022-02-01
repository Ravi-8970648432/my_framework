package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class PropertyFile {

	public static void main(String[] args) throws IOException {
		WebDriver driver=null;
		FileInputStream fis=new FileInputStream("./Properties/commonData.properties"); 
		Properties prop=new Properties();
		prop.load(fis);
		
		String Browser=prop.getProperty("browser");
	
		String Url=prop.getProperty("url");
	
		String userName=prop.getProperty("username");

		String Password=prop.getProperty("password");
		
		
		
		
		if(Browser.toLowerCase().equals("chrome"))
		{
			driver= new ChromeDriver();
		}
		else if(Browser.toLowerCase().equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(Browser.toLowerCase().equals("edge"))
		{
			driver=new EdgeDriver();
		}
		else if(Browser.toLowerCase().equals("ib"))
		{
			driver=new InternetExplorerDriver();
		}
		driver.get(Url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		String Logtitle=driver.getTitle();
		System.out.println(Logtitle);
		WebElement username = driver.findElement(By.name("user_name"));
		username.sendKeys(userName);
		
		WebElement pasword = driver.findElement(By.name("user_password"));
		pasword.sendKeys(Password);
		
		driver.findElement(By.id("submitButton")).click();
		String hometitle=driver.getTitle();
		System.out.println(hometitle);
		
		driver.close();

	}

}
