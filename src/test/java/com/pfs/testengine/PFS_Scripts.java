package com.pfs.testengine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pfs.excel.operations.ReadExcel;
import com.pfs.pages.AddPaymentTypePageActions;
import com.pfs.pages.LoginLogoutPageActions;
import com.pfs.pages.RegisteredPaymentsAndAccountsPageActions;
import com.pfs.reporting.ExecutionLog;
import com.pfs.test.base.TestBase;
import com.pfs.utility.CommonMethods;

public class PFS_Scripts extends TestBase implements ITest  {
	private ThreadLocal<String> testName = new ThreadLocal<>();
	RegisteredPaymentsAndAccountsPageActions registeredPaymentsAndAccounts;

	  @BeforeMethod
	   public void BeforeMethod(Method method, Object[] testData){
	       testName.set(method.getName() + "_" + testData[0]);
	   }
	 
	   @Override
	   public String getTestName() {
	       return testName.get();
	   }

	@DataProvider(name = "PFSTestData")
	public Object[][] PFSTestData() throws Exception {
			initSession();
			ReadExcel.setFileConfig(filePath, sheetName);
			int totalRows = ReadExcel.getRowCount(sheetName);
			int totalCols = 6;
			Object[][] tabArray = new Object[totalRows-1][totalCols];			
			try {
			int startCol = 0;
			int ci, cj;
			ci = 0;
			for (int i = 1; i <= totalRows-1; i++, ci++) {
				cj = 0;
				for (int j = startCol; j <= totalCols-1; j++, cj++) {
					tabArray[ci][cj] = ReadExcel.getCellData(i, j, sheetName, filePath);
				}
			}
			return tabArray;
		} catch (FileNotFoundException e){
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		} catch (IOException e){
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return(tabArray);

	}
	
	
	@Test(dataProvider= "PFSTestData", groups = "Regression", enabled = true)
	public void TC1_AddAccount(String paymentType, String appURL, String userName, String pass, String accountNumber, String editAccountNumber) throws Exception {
		//initSession();
		driver = TestBase.setDriver(browser, appURL);
		LoginLogoutPageActions login = new LoginLogoutPageActions();
		login.getLogin(userName, pass);

		ExecutionLog.log("****************************************");
		ExecutionLog.log("*paymentType:	" + paymentType);
		ExecutionLog.log("****************************************");

		RegisteredPaymentsAndAccountsPageActions registeredPaymentsAndAccounts = new RegisteredPaymentsAndAccountsPageActions();
		registeredPaymentsAndAccounts.addPaymentType.click();
		Thread.sleep(5000);

		AddPaymentTypePageActions addPaymentTypePage = new AddPaymentTypePageActions();			
		CommonMethods.selectValueFromDropDown(addPaymentTypePage.rowSelectorForPaymentType, "100", "rowSelectorForPaymentType");
		Thread.sleep(10000);
		CommonMethods.getElement(addPaymentTypePage.loc_paymentType.replace("@paymentType", paymentType)).click();
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
		
		CommonMethods.verifyTextOf(CommonMethods.getElement(registeredPaymentsAndAccounts.loc_accountNumber.replace("@paymentType", paymentType).replace("@accountNumber", accountNumber)), accountNumber, "Account Number");
		ExecutionLog.log("Verified that account number '" + accountNumber + "' has been created successfully");
	}




}
