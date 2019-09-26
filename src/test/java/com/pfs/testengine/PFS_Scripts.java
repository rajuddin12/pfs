package com.pfs.testengine;

import org.testng.annotations.Test;

import com.pfs.pages.AddPaymentTypePageActions;
import com.pfs.pages.LoginLogoutPageActions;
import com.pfs.pages.RegisteredPaymentsAndAccountsPageActions;
import com.pfs.reporting.ExecutionLog;
import com.pfs.test.base.TestBase;
import com.pfs.utility.CommonMethods;

public class PFS_Scripts extends TestBase {

	RegisteredPaymentsAndAccountsPageActions registeredPaymentsAndAccounts;

	@Test(description = "Add Account", groups = "Regression", enabled = true)
	public void TC1_AddAccount() throws Exception {
		initSession();
		driver = TestBase.setDriver(browser, appURL);
		LoginLogoutPageActions login = new LoginLogoutPageActions();
		ExecutionLog.log(adminUserName + "adminUserName");
		ExecutionLog.log(adminPass + "adminPass");
		login.getLogin(adminUserName, adminPass);

		ExecutionLog.log("****************************************");
		ExecutionLog.log("*URL:	" + appURL);
		ExecutionLog.log("*Logged-In with Admin user: " + adminUserName);
		ExecutionLog.log("****************************************");

		RegisteredPaymentsAndAccountsPageActions registeredPaymentsAndAccounts = new RegisteredPaymentsAndAccountsPageActions();
		registeredPaymentsAndAccounts.addPaymentType.click();
		Thread.sleep(5000);

		AddPaymentTypePageActions addPaymentTypePage = new AddPaymentTypePageActions();			
		CommonMethods.selectValueFromDropDown(addPaymentTypePage.rowSelectorForPaymentType, "100", "rowSelectorForPaymentType");
		Thread.sleep(10000);
		CommonMethods.getElement(addPaymentTypePage.loc_accountType).click();
		Thread.sleep(10000);
		addPaymentTypePage.next.click();
		Thread.sleep(5000);
		addPaymentTypePage = new AddPaymentTypePageActions();
		addPaymentTypePage.accountNumberField.sendKeys(accountNumber);
		Thread.sleep(5000);
		addPaymentTypePage.next.click();
		Thread.sleep(5000);
		addPaymentTypePage.done.click();

		Thread.sleep(20000);
		
		CommonMethods.verifyTextOf(CommonMethods.getElement(registeredPaymentsAndAccounts.loc_accountNumber), accountNumber, "Account Number");
		ExecutionLog.log("Verified that account number '" + accountNumber + "' has been created successfully");
	}

}
