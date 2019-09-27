package com.pfs.test.base;

import java.io.File;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pfs.excel.operations.ReadExcel;
import com.pfs.reporting.ExecutionLog;
import com.pfs.reporting.ExtentManager;
import com.pfs.reporting.ExtentTestManager;
import com.pfs.reporting.ReportScreenshot;
import com.pfs.utility.DateTimeHelper;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase  {

	public static WebDriver driver = null;
	public static String currentDir = System.getProperty("user.dir");
	private static String driverPath = currentDir + File.separator + "Drivers" + File.separator;
	
	
	public static String var_appURL = "";
	public static String var_paymentType ;
	public static String var_adminUserName ;
	public static String var_adminPass ;
	public static String var_accountNumber ;
	public static String var_editAccountNumber ;
	public static int pamentTyperow = 1;
	
	public static String filePath;
	
	public static String browser = "chrome"; // Need to update in config.properties file
	public static String path;
	public static String sheetName; 	
	public static String curentPageID; 	
	public static Boolean TestClassStatus; 	
	public static Boolean TestCaseStatus;
	public static ExtentReports report = new ExtentReports(currentDir + File.separator +"TestReport" + File.separator + "pfs-Automaton-Report_" + DateTimeHelper.getCurrentDateTime() + ".html");;


	public static WebDriver getDriver() {
		return driver;
	}
	
	@BeforeClass(alwaysRun=true)
	public void TestClassStatusController() {
		TestClassStatus = true;// Controlled through TestClassStatus() method of each @Test Class
	}
	
	@BeforeMethod(alwaysRun=true)
	public void TestCaseStatusController() {
		TestCaseStatus  = true;// Controlled through ExecutionLog
	}

	// Initialize the credentials and URLs
	public static void initSession(){
		try {
			
			filePath = System.getProperty("user.dir") + File.separator + "\\PFSTestData.xlsx";
			sheetName  = "ActiveData_SingleUser";
			/*var_paymentType			= ReadExcel.getCellData(1, 0, sheetName, filePath);
			var_appURL				= ReadExcel.getCellData(1, 1, sheetName, filePath);
			var_adminUserName 		= ReadExcel.getCellData(1, 2, sheetName, filePath);
			var_adminPass 			= ReadExcel.getCellData(1, 3, sheetName, filePath);
			var_accountNumber 		= ReadExcel.getCellData(1, 4, sheetName, filePath);
			var_editAccountNumber 	= ReadExcel.getCellData(1, 5, sheetName, filePath);		*/	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	/**
	 * Launch browser along with application URL as defined in test data file
	 * i.e. launch with default values
	 * @return Web-driver instance 
	 */
	public static WebDriver setDriver() {
		return setDriver(browser, var_appURL);
		
	}
	
	/**
	 * Launch browser along with following provided parameters 
	 * @param browserType (if empty initialize as defined in test data file)
	 * @param appURL (if empty initialize as defined in test data file)
	 * @return web-driver instance
	 */
	public static WebDriver setDriver(String browserType, String appURL) {
		initSession();
		
		if(browserType.equals(""))
			browserType = browser;

		if(appURL.equals(""))
			appURL = TestBase.var_appURL;
		
		switch (browserType.toLowerCase()) {
		case "chrome":
			driver = initChromeDriver(appURL);
			break;
		case "firefox":
			driver = initFirefoxDriver(appURL);
			break;
		default:
			driver = initChromeDriver(appURL);
		}
		return driver;
	}

	private static WebDriver initChromeDriver(String appURL) {
		ExecutionLog.log("[INFO] Launching Google Chrome with "+ appURL);
		System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(appURL);
		return driver;
	}

	private static WebDriver initFirefoxDriver(String appURL) {
		ExecutionLog.log("[INFO] Launching Firefox with "+ appURL);
		System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	@BeforeMethod(alwaysRun=true)
	public void beforeEveryTest(Method method) throws Exception {
		Test test = method.getAnnotation(Test.class);
//		ExtentTestManager.startTest(test.description());
		var_paymentType			= ReadExcel.getCellData(pamentTyperow, 0, sheetName, filePath);
		ExtentTestManager.startTest(var_paymentType);
		pamentTyperow++;
	}
		
	@AfterMethod(alwaysRun=true)
	protected void afterMethod(ITestResult result) throws Exception {		
		//LogStatus status = ExtentTestManager.getTest().getRunStatus();
		if (result.getStatus() == ITestResult.FAILURE) {
			TestBase.TestClassStatus = false;
			ReportScreenshot.captureAndDisplayScreenShots(driver);
			ExtentTestManager.getTest().log(LogStatus.INFO, org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace(result.getThrowable()));
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Case Failed" + result.getThrowable() );
			ExecutionLog.log(result.getThrowable().getStackTrace() + "");
		} else if (result.getStatus() == ITestResult.SKIP) {
			ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Case Skipped" + result.getThrowable());
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Test Case Passed");
		}
		
		ExtentManager.getReporter().endTest(ExtentTestManager.getTest());        
		ExtentManager.getReporter().flush();		
		report.flush();
	}

	
/*
	@AfterMethod(alwaysRun=true)
	protected void afterMethod(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.SKIP) {
				ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Case Skipped" + result.getThrowable());
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Test Case Passed");
		}

		ExtentManager.getReporter().endTest(ExtentTestManager.getTest());        
		ExtentManager.getReporter().flush();
		report.flush();
	}*/
}
