package practice;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

import com.google.protobuf.Method;
import com.vitiger.comcast.genericUtility.BaseClass;

import java_cup.runtime.Symbol;

public class Sample extends BaseClass {
	@Test
	private void verifyHomePage(Method mtd) throws IOException {
		System.out.println(mtd.getName());
		String currentTestName = mtd.getName();
		System.out.println("===========Test Start===============");
		EventFiringWebDriver efw=new EventFiringWebDriver(driver);
		File src=efw.getScreenshotAs(OutputType.FILE);
		File dest=new File("./Screenshot/"+currentTestName+" .png");
		FileUtils.copyFile(src, dest);
		System.out.println("==============Test End=============");

	}

}
