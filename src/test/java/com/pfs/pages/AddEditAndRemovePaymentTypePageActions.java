package com.pfs.pages;

import static com.pfs.pages.RegisteredPaymentsAndAccountsPageActions.*;
import static com.pfs.utility.CommonMethods.*;

import org.openqa.selenium.JavascriptExecutor;

import com.pfs.reporting.ExecutionLog;
import com.pfs.reporting.ExtentTestManager;
import com.pfs.test.base.TestBase;
import com.pfs.utility.CommonMethods;
import com.relevantcodes.extentreports.LogStatus;
/**
 * This class contains all possible UI elements (and its actions) of RegisteredPaymentsAndAccounts Page
 *
 */
public class AddEditAndRemovePaymentTypePageActions extends TestBase {

	public static String loc_paymentType ;
	public static String loc_rowSelectorForPaymentType ;
	public static String loc_spinner 		;
	public static String loc_next 			;
	public static String loc_accountNumberField ;
	public static String loc_done 			;
	public static String btn_next ;
	public static String btn_save ;
	public static String btn_done ;

	public static String txt_account ;
	public static String inp_account_num ;
	public static String txt_edit_account;	

	public AddEditAndRemovePaymentTypePageActions() {


		System.out.println("********************All Variables in AddEditAndRemovePaymentTypePageActions Constructor********************");
		System.out.println(var_appURL);
		System.out.println(var_paymentType);
		System.out.println(var_adminUserName);
		System.out.println(var_adminPass);
		System.out.println(var_accountNumber);
		System.out.println(var_editAccountNumber);			
		System.out.println("*********************************************************");


		txt_account 			= "//td[contains(text(),'"+ var_accountNumber +"')]";
		inp_account_num 		= "//input[@value='"+var_accountNumber+"']";
		txt_edit_account 		= "//span[contains(text(),'"+var_editAccountNumber+"')]";	
		loc_paymentType 		= "//span[contains(text(),'" + var_paymentType+ "')]";

		loc_rowSelectorForPaymentType = "//select[@name='detailForm:pmtTypeDT_rppDD']";
		loc_spinner 			= "//div[contains(@class,'blockMsg')]";
		loc_next 				= "//span[contains(text(),'Next')]";
		loc_accountNumberField 	= "//input[contains(@id,'acctNumber')]";
		loc_done 				= "//span[contains(text(),'Done')]";
		btn_next 				= "//span[contains(text(),'Next')]";
		btn_save 				= "//span[contains(text(),'Save')]";
		btn_done 				= "//span[contains(text(),'Done')]";

	}

	/**
	 * @description: Click on Add PAyment Type
	 * select 100 from the drop down to show 100 payment types in the list
	 * select the respective payment type >> Next >> Enter Account number >> Next >> Done
	 * Verify that created account number is shown at the RegisteredPaymentsAndAccountsPageActions
	 * 
	 * @throws Exception
	 */
	public static void addAccount() throws Exception {	
		
		ExecutionLog.log(ExecutionLog.color("blue", "======Functionality: Add Account======="));
		driver = TestBase.setDriver(browser, var_appURL);
		LoginLogoutPageActions login = new LoginLogoutPageActions();
		login.getLogin(var_adminUserName, var_adminPass);

		clickOn(loc_addPaymentType, "addPaymentType");
		Thread.sleep(5000);
		CommonMethods.selectValueFromDropDown(loc_rowSelectorForPaymentType, "100", "rowSelectorForPaymentType");
		Thread.sleep(10000);

		clickOn(loc_paymentType, "paymentType " + var_paymentType);
		Thread.sleep(10000);
		clickOn(loc_next, "Next Button");
		Thread.sleep(5000);
		System.out.println(var_accountNumber);
		sendKeys(loc_accountNumberField, var_accountNumber, "Account Number");
		Thread.sleep(5000);
		clickOn(loc_next, "Next Button");
		Thread.sleep(5000);
		clickOn(loc_done, "Done Button");
		Thread.sleep(20000);
		CommonMethods.verifyTextOf(CommonMethods.getElement(loc_accountNumber), var_accountNumber, "Account Number");
		ExecutionLog.log("Verified that account number '" + var_accountNumber + "' has been created successfully");
		ExecutionLog.log(ExecutionLog.color("blue", "===================================================="));
		ExecutionLog.log("");
		driver.close();

	}

	public static void editAccount() throws Exception {
		ExecutionLog.log(ExecutionLog.color("blue", "======Functionality: Edit Account======="));
		driver = TestBase.setDriver(browser, var_appURL);
		LoginLogoutPageActions login = new LoginLogoutPageActions();
		login.getLogin(var_adminUserName, var_adminPass);

		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", CommonMethods.getElement(radio_paymentType));
		clickOn(radio_paymentType, "radio button of payment type");
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", CommonMethods.getElement(radio_paymentType));

		clickOn(btn_edit, "Edit Button");
		Thread.sleep(5000);
		editAccountNumber();
		clickOn(btn_done, "Done Button");
		ExecutionLog.log(ExecutionLog.color("blue", "===================================================="));
		ExecutionLog.log("");
		driver.close();
		

	}

	/**
	 * Edit account number
	 * @throws Exception 
	 */
	public static void editAccountNumber() throws Exception {
		if(CommonMethods.getElements(txt_account).size() > 0) {
			clickOn(txt_account, "Account Number");
			clickOn(btn_next, "Next Button");
			Thread.sleep(10000);
		}		
		sendKeys(inp_account_num, var_editAccountNumber, "Account Number");
		clickOn(btn_save, "Save Button");
		Thread.sleep(10000);
		CommonMethods.verifyTextOf(getElement(txt_edit_account), var_editAccountNumber, "Edit Account Number");
	}
}
