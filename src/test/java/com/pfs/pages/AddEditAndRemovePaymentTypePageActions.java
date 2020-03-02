package com.pfs.pages;

import static com.pfs.pages.RegisteredPaymentsAndAccountsPageActions.*;
import static com.pfs.utility.CommonMethods.*;

import org.openqa.selenium.JavascriptExecutor;

import com.pfs.reporting.ExecutionLog;
import com.pfs.test.base.TestBase;
import com.pfs.utility.CommonMethods;
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
	public static String loc_cancel 			;
	public static String btn_next ;
	public static String btn_save ;
	public static String btn_done ;

	public static String txt_account ;
	public static String inp_account_num ;
	public static String loc_nickName;
	public static String loc_editnickName;
	public static String txt_edit_account;
	public static String loc_col_edit_account;
	//Address Details
	public static String loc_City;
	public static String loc_Address;
	public static String loc_Province;
	public static String loc_PostalCode;
	public static String loc_SiteNumber;
	public static String loc_CustomerNumber;
	 
	 
	public static String loc_number_Of_accountPresentForPaymentType;
	public static int number_Of_accountPresentForPaymentType;
	//td[contains(text(),'Federal Payroll Deductions - Monthly/Quarterly -- EMPTX -- (PD7A)')]/..//td[3]/div
	//Remove account locators
	public static String loc_remove_account;
	public static String loc_text_output_value;
	public static String loc_accountRemovedMsg;
	

	public AddEditAndRemovePaymentTypePageActions() {


		System.out.println("********************All Variables in AddEditAndRemovePaymentTypePageActions Constructor********************");
		System.out.println(var_appURL);
		System.out.println(var_paymentType);
		System.out.println(var_adminUserName);
		System.out.println(var_adminPass);
		System.out.println(var_accountNumber);
		System.out.println(var_editAccountNumber);			
		System.out.println("*********************************************************");

		loc_number_Of_accountPresentForPaymentType=	"//td[contains(text(),\"" + var_paymentTypeSpace +"\")]/..//td[3]/div";
		
		txt_account 			= "//*[contains(text(),'"+ var_accountNumber +"')]";
		inp_account_num 		= "//input[@value='"+var_accountNumber+"']";
		loc_nickName 		= "//input[contains(@id,'nickname')]";
		loc_editnickName 		= "//input[contains(@id,'nickname')]";
		txt_edit_account 		= "//*[contains(text(),'"+var_editAccountNumber+"')]";
		loc_col_edit_account 	= "//td/span[contains(text(),'"+var_editAccountNumber+"')]";
		loc_paymentType 		= "//span[contains(text(),\"" + var_paymentType+ "\")]";

		loc_rowSelectorForPaymentType = "//select[@name='detailForm:pmtTypeDT_rppDD']";
		loc_spinner 			= "//div[contains(@class,'blockMsg')]";
		loc_next 				= "//span[contains(text(),'Next')]";
		loc_accountNumberField 	= "//input[contains(@id,'acctNumber')]";
		loc_done 				= "//span[contains(text(),'Done')]";
		loc_cancel				= "//span[contains(text(),'Cancel')]";
		btn_next 				= "//span[contains(text(),'Next')]";
		btn_save 				= "//span[contains(text(),'Save')]";
		btn_done 				= "//span[contains(text(),'Done')]";
		loc_remove_account		="//button[@id='tranForm:delbtn']";
		loc_text_output_value 	="//span[@class='output-value'] | //span[contains(text(),'account number')]/following-sibling::div/span";
		loc_accountRemovedMsg		= "//li[@role='alert']/span[text()='Account has been removed.']";
		
		////input[contains(@id,'companyName')]
		loc_City				= "//input[contains(@id,'city')]";
		loc_Address				= "";
		loc_Province				= "";
		loc_PostalCode				= "//input[contains(@id,'postalCode')]";
		loc_SiteNumber				= "";
		loc_CustomerNumber				= "";
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
//		driver = TestBase.setDriver(browser, var_appURL);
//		LoginLogoutPageActions login = new LoginLogoutPageActions();
//		login.getLogin(var_adminUserName, var_adminPass);

//		clickOn("//span[text()='Registered payments and accounts']", "Registered payments and accounts");

		try {
			// If FI does not has any account in the list than we will not get below link
			clickOn(loc_addPaymentType, "addPaymentType");
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		if(var_appURL.contains("pfs")) {
//			clickOn("//label[text()='Government tax payment and filing service:']","Government tax payment and filing service:");
//			Thread.sleep(5000);	
//		}
		
		CommonMethods.selectValueFromDropDown(loc_rowSelectorForPaymentType, "100", "rowSelectorForPaymentType");
		Thread.sleep(10000);

		clickOn(loc_paymentType, "paymentType " + var_paymentType);
		Thread.sleep(10000);
		clickOn(loc_next, "Next Button");
		Thread.sleep(10000);
		sendKeys(loc_accountNumberField, var_accountNumber, "Account Number");
		sendKeys(loc_nickName, var_nickName, "Nick Name");
		// Add Address Details
		/*sendKeys(loc_nickName, var_City, "Nick Name");
		sendKeys(loc_nickName, var_Address, "Nick Name");
		sendKeys(loc_nickName, var_Province, "Nick Name");
		sendKeys(loc_nickName, var_postalCode, "Nick Name");
		sendKeys(loc_nickName, var_SiteNumber, "Nick Name");
		//sendKeys(loc_nickName, var_SiteNumber_Edit, "Nick Name");
		sendKeys(loc_nickName, var_CustomerNumber, "Nick Name");*/
		
		clickOn(loc_next, "Next Button");
		Thread.sleep(10000);
		try {
			clickOn(loc_done, "Done Button");
			
		} catch (Exception e) {
			if(isElementPresent("//span[text()='This account already exists!']")) {
				clickOn(loc_cancel, "Cancel Button");
			}
			Thread.sleep(20000);
		}
	
		CommonMethods.verifyTextOf(CommonMethods.getElement(loc_accountNumberSpace), var_accountNumber, "Account Number");
		ExecutionLog.log("Verified that account number '" + var_accountNumber + "' has been created successfully");
		number_Of_accountPresentForPaymentType = getElements(loc_number_Of_accountPresentForPaymentType).size();
		ExecutionLog.log(ExecutionLog.color("blue", "===================================================="));
		ExecutionLog.log("");
//		driver.close();

	}

	public static void editAccount() throws Exception {
		ExecutionLog.log(ExecutionLog.color("blue", "======Functionality: Edit Account======="));
//		driver = TestBase.setDriver(browser, var_appURL);
//		LoginLogoutPageActions login = new LoginLogoutPageActions();
//		login.getLogin(var_adminUserName, var_adminPass);

		clickOn("//span[text()='Registered payments and accounts']", "Registered payments and accounts");
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", CommonMethods.getElement(radio_paymentType));
		clickOn(radio_paymentType, "radio button of payment type");
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", CommonMethods.getElement(radio_paymentType));

		clickOn(btn_edit, "Edit Button");
		Thread.sleep(5000);
		editAccountNumber();
		clickOn(btn_done, "Done Button");
		Thread.sleep(10000);
		ExecutionLog.log(ExecutionLog.color("blue", "===================================================="));
		ExecutionLog.log("");
//		driver.close();
		

	}
	

	/**
	 * Edit account number
	 * @throws Exception 
	 */
	public static void editAccountNumber() throws Exception {
		
		if(number_Of_accountPresentForPaymentType > 1) {
//		if(CommonMethods.getElements(txt_account).size() > 0) {
			clickOn(txt_account, "Account Number");
			Thread.sleep(2000);
			clickOn(btn_next, "Next Button");
			Thread.sleep(10000);
		}		
		sendKeys(loc_nickName, var_nickName, "Nick Name");
		Thread.sleep(2000);
		sendKeys(inp_account_num, var_editAccountNumber, "Account Number");		
		clickOn(btn_save, "Save Button");
		Thread.sleep(10000);
		CommonMethods.verifyTextOf(getElement(txt_edit_account), var_editAccountNumber, "Edit Account Number");
	}
	
	/**
	 * Edit account number
	 * @throws Exception 
	 */
	public static void removeAccountNumber() throws Exception {
		if(number_Of_accountPresentForPaymentType > 1) {
//		if(CommonMethods.getElements(loc_col_edit_account).size() > 0) {
			clickOn(loc_col_edit_account, "Account Number");
			Thread.sleep(2000);
			clickOn(btn_next, "Next Button");
			Thread.sleep(10000);
		}		
		
		clickOn(loc_remove_account, "Remove Button");
		Thread.sleep(10000);
		CommonMethods.verifyTextOf(getElement(loc_accountRemovedMsg), "Account has been removed.", "Account has been removed. message");
	}
	
	
	
	public static void removeAccount() throws Exception {
		ExecutionLog.log(ExecutionLog.color("blue", "======Functionality: remove Account======="));
//		driver = TestBase.setDriver(browser, var_appURL);
//		LoginLogoutPageActions login = new LoginLogoutPageActions();
//		login.getLogin(var_adminUserName, var_adminPass);
		clickOn("//span[text()='Registered payments and accounts']", "Registered payments and accounts");
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", CommonMethods.getElement(radio_paymentType));
		clickOn(radio_paymentType, "radio button of payment type");
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", CommonMethods.getElement(radio_paymentType));

		clickOn(btn_remove, "remove Button");
		Thread.sleep(5000);
		
		
		removeAccountNumber();
		clickOn(btn_done, "Done Button");
	
		
		//verifyElementIsNotDisplayed(getElement("//tbody[@id='mainForm:tranmainDT_data'] | //div[@id='content']").getText().contains(var_editAccountNumber), "Non-Presence of Account Number");
		ExecutionLog.log(ExecutionLog.color("blue", "===================================================="));
		ExecutionLog.log("");
//		driver.close();
		

	}
}





