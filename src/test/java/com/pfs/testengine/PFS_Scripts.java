package com.pfs.testengine;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pfs.excel.operations.ReadExcel;
import com.pfs.pages.AddEditAndRemovePaymentTypePageActions;
import com.pfs.pages.FutureTransactionsPageActions;
import com.pfs.pages.LoginLogoutPageActions;
import com.pfs.pages.MakeAPaymentPageActions;

import com.pfs.pages.RegisteredPaymentsAndAccountsPageActions;
import com.pfs.reporting.ExecutionLog;
import com.pfs.reporting.ExtentTestManager;
import com.pfs.reporting.ReportScreenshot;
import com.pfs.test.base.TestBase;
import com.pfs.utility.CommonMethods;
import com.relevantcodes.extentreports.LogStatus;

import static com.pfs.pages.AddEditAndRemovePaymentTypePageActions.*;
import static com.pfs.pages.FutureTransactionsPageActions.*;
import static com.pfs.pages.MakeAPaymentPageActions.*;

public class PFS_Scripts extends CommonMethods {

	RegisteredPaymentsAndAccountsPageActions registeredPaymentsAndAccounts;

	@DataProvider(name = "PFSTestData")
	public Object[][] PFSTestData() throws Exception {
			initSession();
			ReadExcel.setFileConfig(filePath, sheetName);
			int totalRows = ReadExcel.getRowCount(sheetName);
			int totalCols = ReadExcel.getColumnCount(sheetName, 0);
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
	
	public static void initialize() throws Exception {
		driver = TestBase.setDriver(browser, var_appURL);
		LoginLogoutPageActions login = new LoginLogoutPageActions();
		if(var_adminUserName.contains("-")) {
			var_userType = "multiAuthUser";
		}
		login.getLogin(var_adminUserName, var_adminPass);
	}
	
	public static void initialize_User2() throws Exception {
		driver = TestBase.setDriver(browser, var_appURL);
		LoginLogoutPageActions login = new LoginLogoutPageActions();
		if(var_adminUserName.contains("-")) {
			var_userType = "multiAuthUser";
		}
		login.getLogin(var_adminUserName2, var_adminPass);
	}
	
	@Test(dataProvider= "PFSTestData", groups = "Regression", enabled = true, description="PFS_TestFunctionalities")
	public void PFS_TestFunctionalities(String[] args) throws Exception {
	//public void PFS_TestFunctionalities( String paymentType, String paymentTypeSpace, String appURL,  String userName,  String pass,  String accountNumber,  String editAccountNumber, String paymentDate, String endDate) throws Exception {
		 var_paymentType 		= args[0];
		 if(var_appURL.contains("pfs")) {
			 // In PFS the name of payment type is same at Add payment page and at Registered Payment and Account page
			 // WehereAS on UAT there is space issue
			 var_paymentTypeSpace 	= var_paymentType;
		 } else  var_paymentTypeSpace 	= args[1];
		 var_appURL 			= args[2];
		 var_adminUserName 		= args[3];
		 var_adminPass 			= args[4];
		 var_accountNumber 	   	= args[5];
		 var_editAccountNumber 	= args[6];	
		 var_nickName			= args[17];	
		 var_editnickName		= args[18];	
		 var_paymentTypeSearch 	= args[7];
		 var_status				= args[8]; 
		 var_paymentDate 		= args[9];	
		 var_endDate 			= args[10];
		 var_PeriodEnding		= args[11];
		 var_ReportingFromDate 	= args[12];
		 var_ReportingToDate	= args[13];
		 var_DateYearToEmployees= args[14];
		 var_DateMonthNoToEmployees	= args[15];
		 var_NumberEmployees	= args[16];
		 var_DueDate			= args[20];
		 var_GSTFrom			= args[21];
		 var_GSTTo				= args[22];
		 var_QSTFrom			= args[23];
		 var_QSTTo_DatePaymentMadeTtoEmployees	= args[24];
		 var_adminUserName2		= "33011424-10000";//args[22];
		 var_City				= args[25];
		 var_Address			= args[26];
		 var_Province			= args[27];
		 var_postalCode			= args[28];
		 var_SiteNumber			= args[29];
		 var_SiteNumber_Edit	= args[30];
		 var_CustomerNumber		= args[31];
		 var_TaxationYear		= args[32];
		 
		 
		
		
		 // Initialize Objects
		 new AddEditAndRemovePaymentTypePageActions();
		 new RegisteredPaymentsAndAccountsPageActions();
		 new MakeAPaymentPageActions();
		 new FutureTransactionsPageActions();
		 try {
			 initialize();
//			 addAccount();
			 
			try {
				 makeAPayment();
				 searchTransaction();
				 cancelPayment();
				 searchTransactionNotExist();
				 TransactionHistory("Cancelled", var_confirmationNo_1);
				 TransactionHistory("Cancellation Request", var_confirmationNo_2);
				 
				 //driver.quit();
			 } catch (Exception e) {
				 ExecutionLog.log(ExecutionLog.color("red", "BUG The Automation Script failed while doing payment. Please Analyze the executon report for further processing"));
				 ReportScreenshot.captureAndDisplayScreenShots(driver);
				 ExtentTestManager.getTest().log(LogStatus.INFO, org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace(e));
				 e.printStackTrace();
			 }
			 editAccount();
			 removeAccount();
			driver.quit();
			 
		} catch (Exception e) {
			//driver.close();
			e.printStackTrace();
			throw e;
		}
		
	}

	
}
