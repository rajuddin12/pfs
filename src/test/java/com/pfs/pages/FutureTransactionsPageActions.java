package com.pfs.pages;

import static com.pfs.pages.RegisteredPaymentsAndAccountsPageActions.radio_paymentType;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import com.pfs.reporting.ExecutionLog;
import com.pfs.test.base.TestBase;
import static com.pfs.utility.CommonMethods.*;
/**
 * This class contains all possible UI elements (and its actions) of RegisteredPaymentsAndAccounts Page
 *
 */
public class FutureTransactionsPageActions extends TestBase {

	public static String  loc_paymentDateFrom;
	public static String loc_paymentDateTo ;
	public static String loc_btnSearch ;
	public static String loc_FutureTransactionMenu ;
	public static String loc_Status ;
	public static String loc_paymentTypeSearch ;
	public static String loc_Confirmation;
	
	// Cancel payment Locators
	public static String loc_checkStatus;
	public static String loc_cancelTransaction;
	public static String loc_btnYes;
	public static String loc_message;
	
	//Transacton History
	public static String loc_TransactionHistory;
	public static String loc_AdvancedSearch;
	public static String loc_radioBtnConfNumber;
	public static String loc_confirationNumberField;
	public static String loc_paymentEndDateField;
	public static String loc_ConfirmationNo_2;
	
	
	public FutureTransactionsPageActions() {
		loc_paymentDateFrom 		="//input[@id='searchForm:dateStart_input']";
		loc_paymentDateTo 			="//input[@id='searchForm:dateEnd_input']";
		loc_btnSearch    			="//button[contains(@id,'searchForm:searchBt')]";//"//button[@id='searchForm:searchBt']";
		loc_FutureTransactionMenu 	="//span[text()='View/cancel future transactions']";
		loc_Status 					="//*[contains(text(),'"+ var_accountNumber +"')]/../td[6]";
		loc_paymentTypeSearch		="//*[contains(text(),'"+ var_accountNumber +"')]/../td[1]/span[2]";
		loc_Confirmation			="//*[contains(text(),'"+ var_accountNumber +"')]/../td[4]";
		// Cancel payment Locators
		loc_checkStatus 			= "//span[(contains(text(),'To be processed'))]";
		loc_cancelTransaction		=	"//span[contains(text(),'Cancel this transaction')]";
		loc_btnYes					="//span[contains(text(),'Yes')]";
		loc_message					="//span[contains(text(),'Cancellation request has been successful')]";
		loc_ConfirmationNo_2		="//span[(.='Confirmation number:')]/ancestor::tr/td[2]";
		
		//Transaction History
		loc_TransactionHistory 	="//span[text()='Transaction history']";
		loc_AdvancedSearch ="//a[text()='Advanced search']";
		loc_radioBtnConfNumber="(//span[@id='searchForm:confRadio']/following-sibling::table//span[contains(@class,'ui-radiobutton-icon')])[2] | (//span[@id='searchForm:confOption']/following-sibling::table//span[contains(@class,'ui-radiobutton-icon')])[2]";
		loc_confirationNumberField="//input[@id='searchForm:confNum_input']";
	
		loc_paymentEndDateField	="//input[@id='searchForm:date-end_input']";
	}


	public static void searchTransaction() throws Exception {	

		ExecutionLog.log(ExecutionLog.color("blue", "======Functionality: Search the Transaction exits after Payment======="));
		driver = TestBase.setDriver(browser, var_appURL);
		LoginLogoutPageActions login = new LoginLogoutPageActions();
		login.getLogin(var_adminUserName, var_adminPass);

		clickOn(loc_FutureTransactionMenu, "FutureTransactionMenu");
		sendKeys(loc_paymentDateTo, var_paymentDate, "Payment Date To");
		clickOn(loc_btnSearch, "Search Button");
		Thread.sleep(10000);
		verifyTextOf(getElement(loc_Status), var_status, "Status");
		verifyTextOf(getElement(loc_paymentTypeSearch), var_paymentTypeSearch, "Payment Date");
		verifyTextOf(getElement(loc_Confirmation), var_confirmationNo_1, "confirmationNo");
		ExecutionLog.log(ExecutionLog.color("blue", "===================================================="));
		ExecutionLog.log("");
		driver.close();

	}


	public static void cancelPayment() throws Exception {
		ExecutionLog.log(ExecutionLog.color("blue", "======Functionality: Cancel the Transaction======="));
		driver = TestBase.setDriver(browser, var_appURL);
		LoginLogoutPageActions login = new LoginLogoutPageActions();
		login.getLogin(var_adminUserName, var_adminPass);
		
		clickOn(loc_FutureTransactionMenu, "FutureTransactionMenu");
		sendKeys(loc_paymentDateTo, var_paymentDate, "Payment Date To");
		clickOn(loc_btnSearch, "Search Button");
		Thread.sleep(10000);
		clickOn(loc_Confirmation, "select row of Confirmation no. " + var_confirmationNo_1);
		Thread.sleep(5000);
		verifyTextOf(getElement(loc_checkStatus), "To be processed", "To be processed Status");
		clickOn(loc_cancelTransaction, "Cancel Transaction Button");
		Thread.sleep(5000);
		clickOn(loc_btnYes, "Yes Button");
		Thread.sleep(10000);
		verifyTextOf(getElement(loc_message), "Cancellation request has been successful", "To be processed Status");
		
		var_confirmationNo_2  = getElement(loc_ConfirmationNo_2).getText().trim();
		System.out.println(var_confirmationNo_2);
		ExecutionLog.log(ExecutionLog.color("blue", "===================================================="));
		ExecutionLog.log("");
		driver.close();

	}
	
	public static void searchTransactionNotExist() throws Exception {	

		ExecutionLog.log(ExecutionLog.color("blue", "======Functionality: Search the Transaction does not exits after delete======="));
		driver = TestBase.setDriver(browser, var_appURL);
		LoginLogoutPageActions login = new LoginLogoutPageActions();
		login.getLogin(var_adminUserName, var_adminPass);

		clickOn(loc_FutureTransactionMenu, "FutureTransactionMenu");
		sendKeys(loc_paymentDateTo, var_paymentDate, "Payment Date To");
		clickOn(loc_btnSearch, "Search Button");
		Thread.sleep(10000);
		Assert.assertFalse(isElementPresent(loc_Confirmation));
		
		ExecutionLog.log(ExecutionLog.color("blue", "===================================================="));
		ExecutionLog.log("");
		driver.close();

	}
	
	
	
	public static void TransactionHistory(String status, String ConfNumber) throws Exception {	

		System.out.println(status);
		System.out.println(ConfNumber);
		ExecutionLog.log(ExecutionLog.color("blue", "======Functionality: Search Transaction in Transaction History======="));
		driver = TestBase.setDriver(browser, var_appURL);
		LoginLogoutPageActions login = new LoginLogoutPageActions();
		login.getLogin(var_adminUserName, var_adminPass);

		clickOn(loc_TransactionHistory, "TransactionHistory");
		sendKeys(loc_paymentDateTo, var_paymentDate, "Payment Date To");
		
		
		clickOn(loc_AdvancedSearch, "Advance Search");
		Thread.sleep(5000);
		clickOn(loc_radioBtnConfNumber, "radio Btn ConfNumber");
		Thread.sleep(5000);
		sendKeys(loc_confirationNumberField, ConfNumber, "confirmation No");
		sendKeys(loc_paymentEndDateField, var_paymentDate, "End Date");				
		clickOn(loc_btnSearch, "Search Button");
		Thread.sleep(10000);
		
		Assert.assertTrue(isElementPresent("//td[contains(text(),'" + var_paymentTypeSearch + "')] | //span[contains(text(),'" + var_paymentTypeSearch +"')]"));
		Assert.assertTrue(isElementPresent("//td[contains(text(),'" + status +"')] | //span[contains(text(),'"+status +"')]"));
		String loc = ("//tr[contains(@data-rk,'acctNbr:"+ var_accountNumber +"')]/td[text()='"+ConfNumber+"']");
		System.out.println(loc);
		Assert.assertTrue(isElementPresent(loc));
			

		ExecutionLog.log(ExecutionLog.color("blue", "===================================================="));
		ExecutionLog.log("");
		driver.close();

	}



}
