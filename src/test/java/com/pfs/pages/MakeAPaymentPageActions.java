package com.pfs.pages;

import static com.pfs.pages.RegisteredPaymentsAndAccountsPageActions.radio_paymentType;
import static com.pfs.utility.CommonMethods.clickOn;
import static com.pfs.utility.CommonMethods.sendKeys;

import org.openqa.selenium.JavascriptExecutor;
import com.pfs.reporting.ExecutionLog;
import com.pfs.test.base.TestBase;
import static com.pfs.testengine.PFS_Scripts.*;
import static com.pfs.utility.CommonMethods.*;
/**
 * This class contains all possible UI elements (and its actions) of RegisteredPaymentsAndAccounts Page
 *
 */
public class MakeAPaymentPageActions extends TestBase {

	public static String  loc_reportingPeriodFrom;
	public static String loc_reportingPeriodTo ;
	public static String loc_paymentDate ;
	//public static String loc_endDate ;
	public static String loc_enterAmountField;
	public static String loc_btnPay;
	public static String loc_btnSubmit;
	public static String loc_btnOK;
	public static String loc_confMsgInfo;
	public static String loc_periodEnding;
	public static String loc_EndDate;
	public static String loc_DueDate;
	public static String loc_QSTFrom;
	public static String loc_QSTTo;
	public static String loc_GSTFrom;
	public static String loc_GSTTo;
	public static String loc_pendingApproval;
	public static String loc_ApprovalButton;
	public static String loc_OK_Approval;
	public static String loc_DateYearToEmployees;
	public static String loc_DateMonthNoToEmployees;
	public static String loc_NumberOfEmployee ;
	
	
	
	public static String Validation_Msg_TransactionApproval = "The transaction is now in Pending Approval status and requires 1 approver(s).";



	public MakeAPaymentPageActions() {

		loc_reportingPeriodFrom = "//input[@id='detailForm:detailViewId:reportingPeriodFromId_input']";
		loc_reportingPeriodTo 	= "//input[@id='detailForm:detailViewId:reportingPeriodToId_input']";											
		loc_paymentDate 		= "//input[@id='detailForm:detailViewId:paymentDateId_input'] | //input[contains(@id,'pmtDate_input')]";
												
		loc_enterAmountField 	="//input[@id='detailForm:detailViewId:salesOther101RevenueId_input'] | //input[contains(@id,'amt1_input')] | //input[contains(@id,'amt12_input')] | //input[contains(@id,'amt8_input')]";
		loc_btnPay    			="//button[@id='mainForm:payBt']";
		loc_btnSubmit    		="//button[@id='detailForm:submitButton']";
		loc_btnOK				="//button[@id='detailForm:detailViewId:pmtDtAfterToDtToDialogOkBtnId']";	
		loc_confMsgInfo			="//span[@class='ui-messages-info-summary']";
		loc_periodEnding		="//div[contains(@id,'rptThruDate')]";
		loc_EndDate				="//div[contains(@id,'rptThruDate')]  | //input[contains(@id,'rptThruDate')]";
		loc_DueDate				="//input[contains(@id,'dueDate_input')]";
		loc_QSTFrom				="//input[contains(@id,'pmtExt_extDate1_8_input')]";		
		loc_QSTTo				="//input[contains(@id,'rptThruDate_input')]";
		loc_GSTFrom				="//input[contains(@id,'pmtExt_extDate35_42_input')]";
		loc_GSTTo				="//input[contains(@id,'pmtExt_extDate43_50_input')]";
		
		loc_pendingApproval		="//*[@id='mainForm:pending_data']/tr/td[contains(text(),'"+ var_accountNumber +"')]/../td[contains(text(),'Pending approval')]";
		loc_ApprovalButton 		= "//button[@id='ptranDetailForm:approveBtn']";
		loc_OK_Approval		="//button[@id='ptranDetailForm:approveDialogOkBtnId']";
		
		
		loc_DateYearToEmployees = "//label[contains(@id,'periodYear_label')]";
		
		loc_DateMonthNoToEmployees ="//label[contains(@id,'periodMonth_label')]";
		loc_NumberOfEmployee =		"//input[contains(@id,'amt3_input')]";
		
	}


	public static void makeAPayment() throws Exception {	

		ExecutionLog.log(ExecutionLog.color("blue", "======Functionality: Make A Payment======="));
		/*driver = TestBase.setDriver(browser, var_appURL);
		LoginLogoutPageActions login = new LoginLogoutPageActions();
		login.getLogin(var_adminUserName, var_adminPass);*/
		maekAPayment_2();
	
		
		ExecutionLog.log(ExecutionLog.color("blue", "===================================================="));
		ExecutionLog.log("");
//		driver.close();

	}


	public static void maekAPayment_2() throws Exception {
		// select radio button of respective payment type
		//clickOn("//span[text()='Registered payments and accounts']", "Registered payments and accounts");
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", getElement(radio_paymentType));
		clickOn(radio_paymentType, "radio button of payment type");
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", getElement(radio_paymentType));
		
		// click on Pay
		Thread.sleep(2000);
		clickOn(loc_btnPay, "Pay Button");
		Thread.sleep(5000);

		// Select Account To Pay DropDown
		if(var_appURL.contains("uat") &&  isElementPresent("//label[@class='ui-selectonemenu-label ui-inputfield ui-corner-all' and contains(text(),'select account')]")) {
			clickOn("//label[@class='ui-selectonemenu-label ui-inputfield ui-corner-all' and contains(text(),'select account')]", "Select Accunt");
			Thread.sleep(2000);
			clickOn("//li[contains(text(),'" + var_accountNumber + "')]", "Select Account");
		}
	

		if(var_ReportingFromDate.length()>1) {
			sendKeys(loc_reportingPeriodFrom,   var_ReportingFromDate, "Reporting From");
			Thread.sleep(5000);
			selectDate(loc_paymentDate, 		var_paymentDate, 		"Payment Date");
			Thread.sleep(5000);	
			selectDate(loc_reportingPeriodTo,	var_ReportingToDate, 	"Reporting To");
			clickOn(loc_paymentDate, 	"Help Click");			
		} else  if(var_DateYearToEmployees.length()>1) {
			clickOn(loc_DateYearToEmployees, "Year Selector");		
			clickOn("//li[text()='" + var_DateYearToEmployees+"']", "Selected year "  + var_DateYearToEmployees);		
			Thread.sleep(2000);
			clickOn(loc_DateMonthNoToEmployees, "Month Selector");		
			clickOn("//li[text()='" + var_DateMonthNoToEmployees+"']","Selected Month "  + var_DateMonthNoToEmployees);
			Thread.sleep(2000);
		} else	{
			selectDate(loc_QSTFrom, var_QSTFrom, "QSTFrom");
			Thread.sleep(2000);
			selectDate(loc_QSTTo, 	var_QSTTo_DatePaymentMadeTtoEmployees, 	 "QSTTo");
			Thread.sleep(2000);
			selectDate(loc_GSTFrom, var_GSTFrom, "GSTFrom");
			Thread.sleep(2000);
			selectDate(loc_GSTTo, 	var_GSTTo, 	 "GSTTo");
			Thread.sleep(2000);
			selectDate(loc_EndDate, var_endDate, "End Date");
			Thread.sleep(2000);
			selectDate(loc_DueDate, var_DueDate, "Due Date");
			Thread.sleep(2000);
			selectDate(loc_paymentDate, var_paymentDate, "Payment Date");
			Thread.sleep(2000);
		}
		
		sendKeys(loc_NumberOfEmployee, var_NumberEmployees, "NumberEmployees");
		clickOn(loc_periodEnding, var_PeriodEnding);
		clickOn("//li[contains(text(),'" + var_PeriodEnding + "')]", var_PeriodEnding);
		Thread.sleep(10000);
		sendKeys(loc_enterAmountField, "50", "Payment Date");

		//sendKeys(loc_paymentDate, var_paymentDate, "Payment Date");
//		selectDate(loc_paymentDate, var_paymentDate, "Payment Date"); already covered in above if-else loop
		keyboard_TAB();
		Thread.sleep(2000);
		clickOn(loc_periodEnding, var_PeriodEnding);// help click
		Thread.sleep(2000);
		clickOn(AddEditAndRemovePaymentTypePageActions.loc_next, "Next Button");
/*		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", getElement(radio_paymentType));
		"arguments[0].click();"*/
		Thread.sleep(10000);
		clickOn(loc_btnSubmit, "Submit Button");
		Thread.sleep(5000);
		
		
		if(var_userType.equals("multiAuthUser")) {
			verifyTextOf(loc_confMsgInfo, Validation_Msg_TransactionApproval, "Validation_Msg_TransactionApproval");
			ValidateAndApproveByUser_2();
		} else {
			var_confirmationNo_1  = getElement(loc_confMsgInfo).getText().split(":")[1].trim();
			System.out.println(var_confirmationNo_1);
		}
			

	}

	public static void ValidateAndApproveByUser_2() throws Exception {
		try {
			//close the browser/logout and Login with User-2
			driver.close();
			initialize_User2();
			// BUG ID mention(https://jira.tools.thcoe.ca/browse/APTWAM-395) >> Navigate to Transaction Approval tab
			ExecutionLog.log(ExecutionLog.color("red", "BUG ID: https://jira.tools.thcoe.ca/browse/APTWAM-395)"));
			clickOn(FutureTransactionsPageActions.loc_TransactionHistory, "TransactionHistory");
			Thread.sleep(5000);
			verifyPresenceOfElement(loc_pendingApproval, "Pending Approval status of " + var_accountNumber);
			
			
			//approve the transaction
			clickOn(loc_pendingApproval, "Pending Approval row ");
			Thread.sleep(5000);
			clickOn(loc_ApprovalButton, "Approve button");
			Thread.sleep(2000);
			clickOn(loc_OK_Approval, "OK Approve button");
			Thread.sleep(10000);
			var_confirmationNo_1  = getElement(loc_confMsgInfo).getText().split(":")[1].trim();
			System.out.println(var_confirmationNo_1);
			// logout
			// login with User-1
			driver.close();
			initialize();
		} catch (Exception e) {
			ExecutionLog.log(ExecutionLog.color("red", "The Automation Script failed to approve the transaction. Please Analyze the executon report for further processing"));
			e.printStackTrace();
			driver.close();
			initialize();
		}
		
		
	}
	
	
	public static void 	selectDate(String loc, String var_Date, String nameOfLocator) throws Exception {
		if(var_Date.length()>1) {
		System.out.println("------selecting Date in " + nameOfLocator + "------");	
		
		clickOn(loc, "Select date");
		Thread.sleep(5000);
		String splitDate[] = var_Date.split(" ");
		String year = splitDate[0];
		String month = splitDate[1];
		String day = splitDate[2].trim();
		

		if(isElementPresent("//select[@class='ui-datepicker-year']")) {
			selectValueFromDropDown("//select[@class='ui-datepicker-year']", year, "select year");
			selectValueFromDropDown("//select[@class='ui-datepicker-month']", month, "select year");
			clickOn("//a[text()='" + day+"']", "Select Day: " + day);
		} else {
			clickOn("//li[text()='" + var_Date+"']", "Select Day");
		}
		
	}
	}
}
