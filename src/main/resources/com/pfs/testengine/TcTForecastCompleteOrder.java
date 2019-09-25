package com.care.testengine;

import com.care.test.base.TestBase;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.care.excel.operations.ExcelWriter;
import com.care.excel.operations.ReadExcel;
import com.care.pages.action.AcContactForecastReview;
import com.care.pages.action.AcForecast;
import com.care.pages.action.AcLogin;
import com.care.pages.action.AcPredictiveVariables;
import com.care.pages.action.PgDashboard;
import com.care.pages.action.PgDayOfWeek;
import com.care.pages.action.PgForecast;
import com.care.pages.action.PgForecastRegion;
import com.care.pages.action.PgHistoricalPeriod;
import com.care.pages.action.PgHolidayFactor;
import com.care.pages.action.PgLogin;
import com.care.pages.action.PgMultiplier;
import com.care.pages.action.PgPredictiveVariables;
import com.care.reporting.ExtentTestManager;
import com.care.reporting.ReportScreenshot;

public class TcTForecastCompleteOrder extends TestBase {
	static WebDriver driver = null;

	// Data provider where fetching the data from excel
	@DataProvider(name = "getDataForCompletOrder", parallel = false)
	public Object[][] getDataForDMZ() {

		// Get Excel data
		Object[][] arrayObject = ReadExcel.getExcelData(path, sheetName);
		return arrayObject;
	}

	/*
	 * Complete the order for new Forecast Data are fetching from excel sheet
	 */

	@Test(dataProvider = "getDataForCompletOrder")
	public void completeOrder(String tcName, String env, String result, String usn, String password,
			String forecastName1, String forecastType, String forecastDivisionName, String forecastRegionName,
			String forecastLineOfWork, String startMonth, String startYear, String endMonth, String endYear,
			String forecastAssociateWith, String forecastCallType, String forecastHistoryPeriod,
			String predictiveVariablesSubscriber, String predictiveVariablesConnect,
			String predictiveVariablesDisconnect, String TimePeriodMonth, String testInstance)
			throws InterruptedException {

		// Created driver instance
		driver = TestBase.setDriver(browser, appURL);
		try {
			// Generate random number for Unique forecastName
			Random r = new Random();
			String forecastName = forecastName1 + r.nextInt(100);

			// Start Extent report
			ExtentTestManager.startTest(
					"Login Test : " + tcName + " :- " + forecastName + " - " + (Integer.parseInt(testInstance)));

			// Enter Username
			PgLogin.enterUserName(driver, usn);

			// Enter Password
			PgLogin.enterPassword(driver, password);
			Thread.sleep(5000);

			// Verify login button is enabled
			AcLogin.verifyloginBtn(driver);

			// Click on login button
			PgLogin.clickloginBtn(driver);
			Thread.sleep(5000);

			// Click on forecast for Create new Forecast
			PgDashboard.clickCreateForecast(driver);

			// Verify new forecast title
			AcForecast.verifyNewForcastTitle(driver);
			Thread.sleep(1000);

			// Select forecast type
			PgForecast.selectForecastType(driver, forecastType);
			Thread.sleep(1000);

			// Enter forecast name
			PgForecast.enterForecastName(driver, forecastName);
			Thread.sleep(1000);

			// Select division name from drop down
			PgForecast.selectDivisionName(driver, forecastDivisionName);
			Thread.sleep(1000);

			// Select region name from drop down
			PgForecast.selectRegionName(driver, forecastRegionName);
			Thread.sleep(1000);

			// Select line of work from drop down
			PgForecast.selectLineOfWork(driver, forecastLineOfWork);
			Thread.sleep(1000);

			// Select start month from drop down
			PgForecast.selectStartMonth(driver, startMonth);
			Thread.sleep(1000);

			// Select start year from drop down
			PgForecast.selectStartYear(driver, startYear);
			Thread.sleep(1000);

			// Select end month from drop down
			PgForecast.selectEndMonth(driver, endMonth);
			Thread.sleep(1000);

			// Select end year from drop down
			PgForecast.selectEndYear(driver, endYear);
			Thread.sleep(1000);

			// Choose group
			PgForecast.selectAssociateWith(driver, forecastAssociateWith);
			Thread.sleep(1000);

			// Click on Next button on forecast page
			PgForecast.clickNext(driver);
			Thread.sleep(5000);

			// Select call type from drop down
			PgForecastRegion.selectCallType(driver, forecastCallType);
			Thread.sleep(1000);

			// Select history period
			PgForecastRegion.selectHistoryPeriod(driver, forecastHistoryPeriod);
			Thread.sleep(1000);

			// Click on Next button on Forecast Region Page
			PgForecastRegion.clickNext(driver);

			// Verify top section on Predictive Variables Page
			AcPredictiveVariables.verfyForecastName(driver, forecastName + "1");
			AcPredictiveVariables.verfyForecastPeriod(driver, startMonth, startYear, endMonth, endYear);
			AcPredictiveVariables.verfyDivisionName(driver, forecastDivisionName);
			AcPredictiveVariables.verfyRegionName(driver, forecastRegionName);
			AcPredictiveVariables.verfyLineOfWork(driver, forecastLineOfWork);
			AcPredictiveVariables.verfyCallType(driver, forecastCallType);
			Thread.sleep(5000);

			// Click on Next button on Predictive Variables Page
			PgPredictiveVariables.clickPredectiveVariableNext(driver);
			Thread.sleep(5000);

			// Click on Next button on Historical Period Page
			PgHistoricalPeriod.clickHistoricalPeriodNext(driver);
			Thread.sleep(5000);

			// Click on Next button on Day Of Week Page
			PgDayOfWeek.clickDayOfWeekNext(driver);
			Thread.sleep(5000);

			// Click on Next button on Multiplier Page
			PgMultiplier.clickMultiplierNext(driver);
			Thread.sleep(5000);

			// Click on Review Forecast button on Holiday Factor Page
			PgHolidayFactor.clickReviewForecastButton(driver);
			Thread.sleep(5000);

			// Verify top section on Contact Forecast Review Page
			AcContactForecastReview.verfyForecastName(driver, forecastName);
			AcContactForecastReview.verfyDivisionName(driver, forecastDivisionName);
			AcContactForecastReview.verfyRegionName(driver, forecastRegionName);
			AcContactForecastReview.verfyLineOfWork(driver, forecastLineOfWork);
			AcContactForecastReview.verfyCallType(driver, forecastCallType);
			AcContactForecastReview.verfyForecastPeriod(driver, startMonth, startYear, endMonth, endYear);
			Thread.sleep(5000);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertAll();

			// Write the result in excel sheet as "Passed"
			ExcelWriter.setCellData("Passed", Integer.parseInt(testInstance), 2, sheetName, path);

		} catch (Exception e) {
			e.printStackTrace();

			// Failed in extent report if exception occurred
			ExtentTestManager.getTest().log(Status.FAIL, e);
			ReportScreenshot.captureAndDisplayScreenShots(driver);
			ExcelWriter.setCellData("Failed", Integer.parseInt(testInstance), 2, sheetName, path);
			Assert.fail();

		} finally {

			// End the extent report
			ExtentTestManager.endTest();

			// Close the session
			driver.quit();
		}
	}

	

}
