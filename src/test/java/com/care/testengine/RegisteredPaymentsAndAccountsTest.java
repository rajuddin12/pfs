package com.care.testengine;

import org.testng.annotations.Test;

import com.care.pages.RegisteredPaymentsAndAccounts;
import com.care.pages.AddPaymentTypePage;
import com.care.pages.LoginScreen;
import com.pfs.reporting.ExecutionLog;
import com.pfs.test.base.TestBase;
import com.pfs.utility.CommonMethods;

public class RegisteredPaymentsAndAccountsTest extends TestBase {

	RegisteredPaymentsAndAccounts registeredPaymentsAndAccounts;

	// @Test(priority=1, groups="Regression", enabled = true,
	// description="TC1_To_TC4 Verify Allocation Screen Header")
	public void a() throws Exception {

		driver = TestBase.setDriver(browser, appURL);
		LoginScreen login = new LoginScreen();
		login.getLogin(adminUserName, adminPass);

		ExecutionLog.log("****************************************");
		ExecutionLog.log("*URL:	" + appURL);
		ExecutionLog.log("*Logged-In with Admin user: " + adminUserName);
		ExecutionLog.log("****************************************");

		// CreateForecastScreen createForecast = new CreateForecastScreen();
		// createForecast.createForecast(forecast_CreatedBy_adminUserName);


		// TC1_To_TC4_VerifyAllocationScreenHeader();

	}

	@Test(description = "Add Account", groups = "Regression", enabled = true)
	public void TC1_AddAccount() throws Exception {
		//try {

			//initSession();
			driver = TestBase.setDriver(browser, appURL);
			LoginScreen login = new LoginScreen();
			ExecutionLog.log(adminUserName + "adminUserName");
			ExecutionLog.log(adminPass + "adminPass");
			login.getLogin(adminUserName, adminPass);

			ExecutionLog.log("****************************************");
			ExecutionLog.log("*URL:	" + appURL);
			ExecutionLog.log("*Logged-In with Admin user: " + adminUserName);
			ExecutionLog.log("****************************************");

			
			
			RegisteredPaymentsAndAccounts registeredPaymentsAndAccounts = new RegisteredPaymentsAndAccounts();
			registeredPaymentsAndAccounts.addPaymentType.click();
			Thread.sleep(5000);
			AddPaymentTypePage addPaymentTypePage = new AddPaymentTypePage();
			
			
			CommonMethods.selectValueFromDropDown(addPaymentTypePage.rowSelectorForPaymentType, "100", "rowSelectorForPaymentType");
			Thread.sleep(10000);
			
			CommonMethods.getElement(addPaymentTypePage.loc_accountType).click();
			Thread.sleep(10000);
			addPaymentTypePage.next.click();
			Thread.sleep(5000);
			addPaymentTypePage = new AddPaymentTypePage();
			addPaymentTypePage.accountNumberField.sendKeys(accountNumber);
			Thread.sleep(5000);
			addPaymentTypePage.next.click();
			Thread.sleep(5000);
			addPaymentTypePage.done.click();
			
			Thread.sleep(20000);
			
		/*	
		} catch (Exception e) {
			e.printStackTrace();
			ExecutionLog.log("test");
		}*/

	}

}
