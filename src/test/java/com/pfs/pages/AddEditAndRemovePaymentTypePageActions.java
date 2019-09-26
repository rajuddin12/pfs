package com.pfs.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.pfs.reporting.ExecutionLog;
import com.pfs.test.base.TestBase;
import com.pfs.utility.CommonMethods;
/**
 * This class contains all possible UI elements (and its actions) of RegisteredPaymentsAndAccounts Page
 *
 */
public class AddEditAndRemovePaymentTypePageActions extends TestBase {

	@FindBy(how = How.XPATH, using = "//select[@name='detailForm:pmtTypeDT_rppDD']") 		public WebElement rowSelectorForPaymentType;
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'blockMsg')]") 		public WebElement spinner;
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Next')]") 		public WebElement next;
	@FindBy(how = How.XPATH, using = "//input[contains(@id,'acctNumber')]") 	public WebElement accountNumberField;
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Done')]") 		public WebElement done;
	public String loc_accountType = "//span[contains(text(),'" + paymentType  +"')]";
	public String txt_account = "//td[contains(text(),'"+ accountNumber +"')]";
	public String btn_next = "//span[contains(text(),'Next')]";
	public String btn_save = "//span[contains(text(),'Save')]";
	public String inp_account_num = "//input[@value='"+accountNumber+"']";
	public String btn_done = "//span[contains(text(),'Done')]";
	public String txt_edit_account = "//span[contains(text(),'"+editAccountNumber+"')]";
	
	public AddEditAndRemovePaymentTypePageActions() {
		ExecutionLog.log("==========| AddPaymentTypePage |==========");	
		PageFactory.initElements(driver, this);
	}

	
	public void createAccount(String AccountType, String AccountNumber) {
		ExecutionLog.log("Create the account ");
				
	}
	
	/**
	 * Edit account number
	 */
	public void EditAccountNumber() {
		if(CommonMethods.getElements(txt_account).size() > 0) {
			CommonMethods.getElement(txt_account).click();
			CommonMethods.getElement(btn_next).click();
			
		}
		CommonMethods.getElement(inp_account_num).sendKeys(editAccountNumber);
		CommonMethods.getElement(btn_save).click();
		CommonMethods.verifyTextOf(CommonMethods.getElement(txt_edit_account), editAccountNumber, "Edit Account Number");
	}
	
	/**
	 * Click on Done button
	 *
	 */
	public void clickOnDoneButton() {
		ExecutionLog.log("[INFO] Clicking on Done button.");
		CommonMethods.getElement(btn_done).click();
		
	}
}
