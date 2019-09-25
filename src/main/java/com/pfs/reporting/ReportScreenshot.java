package com.pfs.reporting;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;

import com.pfs.utility.DateTimeHelper;
import com.relevantcodes.extentreports.LogStatus;

public class ReportScreenshot extends ExtentManager {

	public static void onTestFailure(WebDriver driver,ITestResult result) {
		ExecutionLog.log("*** Test execution " + result.getMethod().getMethodName() + " failed...");
		ExecutionLog.log((result.getMethod().getMethodName() + " failed!"));
		String targetLocation = null;
		String testClassName = result.getInstanceName().trim();
		String timeStamp = DateTimeHelper.getCurrentDateTime();
		String testMethodName = result.getName().toString().trim();
		String screenShotName = testMethodName + timeStamp + ".png";

		try {
			File file = new File("TestReport" + fileSeperator + "screenshots"); 																				
			if (!file.exists()) {
				ExecutionLog.log("Screenshots directory created " + file);
				file.mkdir();
			}

			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			targetLocation = reportFilepath + fileSeperator + testClassName + fileSeperator + screenShotName;
																												
			File targetFile = new File(targetLocation);
			FileUtils.copyFile(screenshotFile, targetFile);

		} catch (Exception e) {
			ExecutionLog.log("An exception occurred while taking screenshot " + e.getCause());
		}
		
		try {
			ExtentTestManager.getTest().log(LogStatus.FAIL, screenShotName + "'s Snapshot below: " + ExtentTestManager.getTest().addScreenCapture("./Screenshots/"+screenShotName));
		} catch (Exception e) {

			ExecutionLog.log("An exception occured while taking screenshot " + e.getCause());
		}
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed");
	}

	public static void captureAndDisplayScreenShots(WebDriver driver) {
		String targetLocation = null;
		String timeStamp = DateTimeHelper.getCurrentDateTime();
		String screenShotName = "screenshot_" + timeStamp + ".png";

		try {
			File file = new File("TestReport" + fileSeperator + "screenshots"); 																															
			if (!file.exists()) {
				ExecutionLog.log("Screenshots directory created " + file);
				file.mkdir();
			}
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			targetLocation = reportFilepath + fileSeperator + "screenshots" + fileSeperator + screenShotName;																											
			File targetFile = new File(targetLocation);
			FileHandler.copy(screenshotFile, targetFile);
			try {
				ExtentTestManager.getTest().log(LogStatus.INFO, screenShotName + "'s Snapshot below: " + ExtentTestManager.getTest().addScreenCapture("./Screenshots/"+screenShotName));
			} catch (Exception e) {

				ExecutionLog.log("An exception occured while taking screenshot " + e.getCause());
			}
			
		} catch (Exception e) {
			ExecutionLog.log("driver:"+driver);
			if ((driver == null)) {
				ExecutionLog.log("An exception occurred while taking screenshot " + e.getCause());
			}
		}
	}

}
