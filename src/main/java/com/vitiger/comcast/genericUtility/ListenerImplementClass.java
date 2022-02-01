package com.vitiger.comcast.genericUtility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementClass implements ITestListener {
	ExtentReports report;
	ExtentTest test;

	public void onFinish(ITestContext result) {
		// TODO Auto-generated method stub
		//@AfterSuite
		report.flush();

	}

	public void onStart(ITestContext result) {
		// TODO Auto-generated method stub
		//@BeforeSuite
		ExtentSparkReporter spark=new ExtentSparkReporter("./extentreport.html");
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Vtiger automation");
		spark.config().setReportName("Execution report");

		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Window");
		report.setSystemInfo("Platform", "Windows 10");
		report.setSystemInfo("Reporter", "RaviKumar");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult result) {
		String failedtestName = result.getMethod().getMethodName();
		EventFiringWebDriver driver=new EventFiringWebDriver(BaseClass.sdriver);

		File src = driver.getScreenshotAs(OutputType.FILE);
		Date date=new Date();
		String systemDate=date.toString().replace(":", "_").replace(" ", "_");

		File dest = new File("./Screenshot/"+failedtestName+"_"+systemDate+".PNG");
		try {
			FileUtils.copyFile(src, dest);
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		test.log(Status.FAIL, result.getMethod().getMethodName()+" is failed");
		test.log(Status.FAIL, result.getThrowable());
		test.addScreenCaptureFromPath(dest.getAbsolutePath());

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.SKIP, result.getMethod().getMethodName()+" is skipped");
		test.log(Status.SKIP,result.getThrowable());

	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = report.createTest(result.getMethod().getMethodName());

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.PASS, result.getMethod().getMethodName()+" is passed");

	}

}
