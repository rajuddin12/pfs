package com.care.testengine;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.care.pages.action.AcDashboard;
import com.care.pages.action.AcLogin;
import com.care.pages.action.AcLogout;
import com.care.pages.action.PgDashboard;
import com.care.pages.action.PgLogin;
import com.care.pages.action.PgLogout;
import com.care.reporting.ExtentTestManager;
import com.care.reporting.ReportScreenshot;
import com.care.test.base.TestBase;

public class TcTVerifyForecastSteps extends TestBase {

	/*
	 * Verify Left Navigate of Dashboard Verify All steps of Forecast
	 */

	WebDriver driver = null;

	@Test
	public void verifyForecastSteps() {
		driver = TestBase.setDriver(browser, appURL);
		// Start extent report instance
		ExtentTestManager.startTest("Verify Forecast Navigation steps");
		try {
			// Enter the username
			PgLogin.enterUserName(driver, "schauh241");
			Thread.sleep(5000);

			// Enter the password
			PgLogin.enterPassword(driver, "p@wd6Share");
			Thread.sleep(5000);

			// Verify Login Button
			AcLogin.verifyloginBtn(driver);

			// Click on Login button
			PgLogin.clickloginBtn(driver);
			Thread.sleep(5000);

			// Verify Dashboard button
			AcDashboard.verifyDashBoard(driver);
			Thread.sleep(5000);

			// Verify Create Forecast Step
			AcDashboard.verifyCreateForecastDropDown(driver);
			Thread.sleep(5000);
			Thread.sleep(5000);

			// Click on sort down of Forecast
			PgDashboard.clickSortDown(driver);
			Thread.sleep(5000);

			// Verify Forecast Steps
			AcDashboard.verifyCreateForecastDropDownList(driver,
					"Predictive Variables|Historical Period|Day of Week|Multiplier|Holiday Factor|Contact Forecast Review|Capacity Planning");

			// Click on Capacity Planning sort down
			PgDashboard.clickCpacityPlanningDown(driver);

			// Verify Cpacity Planning Steps
			AcDashboard.verifyCapacityPlanningMenu(driver,
					"Allocation Objective & AHT Overrides|OPA|New Hiring Distribution|New Hire Override|Capacity Forecast Review");
			Thread.sleep(5000);

			// Verify Logout button
			AcLogout.verifyLogoutBtn(driver);

			// Click on Logout button
			PgLogout.clickLogoutbtn(driver);
			Thread.sleep(5000);

			// End report instance and closed
			ExtentTestManager.endTest();

			// Close the instance of driver
			driver.close();
		} catch (Exception e) {
			e.printStackTrace();

			// Failed due to exception
			ExtentTestManager.getTest().log(Status.FAIL, e);

			// Capturing the screenshot when failed the script
			ReportScreenshot.captureAndDisplayScreenShots(driver);
			Assert.fail();

		} finally {

			// End report instance and closed
			ExtentTestManager.endTest();

			// Close the all instance of driver
			driver.quit();
		}
	}
}
