package com.pfs.pages;

import static com.pfs.pages.RegisteredPaymentsAndAccountsPageActions.btn_edit;
import static com.pfs.pages.RegisteredPaymentsAndAccountsPageActions.loc_accountNumber;
import static com.pfs.pages.RegisteredPaymentsAndAccountsPageActions.loc_addPaymentType;
import static com.pfs.pages.RegisteredPaymentsAndAccountsPageActions.radio_paymentType;
import static com.pfs.utility.CommonMethods.clickOn;
import static com.pfs.utility.CommonMethods.sendKeys;

import org.openqa.selenium.JavascriptExecutor;

import com.pfs.reporting.ExecutionLog;
import com.pfs.test.base.TestBase;
import static com.pfs.utility.CommonMethods.*;
/**
 * This class contains all possible UI elements (and its actions) of RegisteredPaymentsAndAccounts Page
 *
 */
public class MakeAPaymentPageActions extends TestBase {

	public static String  loc_reportingPeriodFrom;
	public static String loc_reportingPeriodTo ;
	public static String loc_paymentDate ;
	public static String loc_endDate ;
	public static String loc_enterAmountField;
	public static String loc_btnPay;
	public static String loc_btnSubmit;
	public static String loc_btnOK;
	public static String loc_confMsgInfo;


	public MakeAPaymentPageActions() {

		loc_reportingPeriodFrom = "//input[@id='detailForm:detailViewId:reportingPeriodFromId_input']";
		loc_reportingPeriodTo 	= "//input[@id='detailForm:detailViewId:reportingPeriodToId_input']";
		loc_paymentDate 		= "//input[@id='detailForm:detailViewId:paymentDateId_input'] | //input[@id='detailForm:detailViewId:if_pmtDate:pmtDate_input']";
												
		loc_endDate				= "";
		loc_enterAmountField 	= "//input[@id='detailForm:detailViewId:salesOther101RevenueId_input']";
		loc_btnPay    			="//button[@id='mainForm:payBt']";
		loc_btnSubmit    		="//button[@id='detailForm:submitButton']";
		loc_btnOK				= "//button[@id='detailForm:detailViewId:pmtDtAfterToDtToDialogOkBtnId']";	
		loc_confMsgInfo			=	"//span[@class='ui-messages-info-summary']";
	}


	public static void maekAPayment() throws Exception {	

		ExecutionLog.log(ExecutionLog.color("blue", "======Functionality: Make A Payment======="));
		driver = TestBase.setDriver(browser, var_appURL);
		LoginLogoutPageActions login = new LoginLogoutPageActions();
		login.getLogin(var_adminUserName, var_adminPass);

		maekAPayment_2();
		ExecutionLog.log(ExecutionLog.color("blue", "===================================================="));
		ExecutionLog.log("");
		driver.close();

	}


	public static void maekAPayment_2() throws Exception {	
		// select radio button of respective payment type

		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", getElement(radio_paymentType));
		clickOn(radio_paymentType, "radio button of payment type");
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", getElement(radio_paymentType));
		// click on Pay
		clickOn(loc_btnPay, "Pay Button");
		Thread.sleep(5000);
		if(var_ReportingFromDate.length()>1) {
			sendKeys(loc_reportingPeriodFrom, var_ReportingFromDate, "Reporting From");
			Thread.sleep(5000);
			//sendKeys(loc_reportingPeriodTo,	  var_ReportingToDate, "Reporting To");
			//Thread.sleep(5000);	

			sendKeys(loc_paymentDate, var_paymentDate, "Payment Date");
			Thread.sleep(5000);	
			sendKeys(loc_reportingPeriodTo,	  var_ReportingToDate, "Reporting To");
			clickOn(loc_paymentDate, "Help Click");			
		} else 
			{
			sendKeys(loc_paymentDate, var_endDate, "End Date");
			sendKeys(loc_paymentDate, var_paymentDate, "Payment Date");
			}
		
		
		sendKeys(loc_enterAmountField, "50", "Payment Date");

		// enter the dates and amount
		//Next
		clickOn(AddEditAndRemovePaymentTypePageActions.loc_next, "Next Button");
		Thread.sleep(10000);
		clickOn(loc_btnSubmit, "Submit Button");
		Thread.sleep(5000);
		var_confirmationNo_1  = getElement(loc_confMsgInfo).getText().split(":")[1].trim();
		System.out.println(var_confirmationNo_1);
		
	}

}
