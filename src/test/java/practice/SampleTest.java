package practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SampleTest {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.get("http://localhost:8888/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		String Logtitle=driver.getTitle();
		System.out.println(Logtitle);
		WebElement username = driver.findElement(By.name("user_name"));
		username.sendKeys("admin");
		
		WebElement pasword = driver.findElement(By.name("user_password"));
		pasword.sendKeys("admin");
		
		driver.findElement(By.id("submitButton")).click();
		String hometitle=driver.getTitle();
		System.out.println(hometitle);
		
		
		
		driver.close();


	}

}
