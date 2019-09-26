package com.pfs.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.pfs.reporting.ExecutionLog;
import com.pfs.test.base.TestBase;
/**
 * This class contains all possible UI elements (and its actions) of RegisteredPaymentsAndAccounts Page
 *
 */
public class AddPaymentTypePageActions extends TestBase {

	@FindBy(how = How.XPATH, using = "//select[@name='detailForm:pmtTypeDT_rppDD']") 		public WebElement rowSelectorForPaymentType;
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'blockMsg')]") 		public WebElement spinner;
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Next')]") 		public WebElement next;
	@FindBy(how = How.XPATH, using = "//input[contains(@id,'acctNumber')]") 	public WebElement accountNumberField;
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Done')]") 		public WebElement done;
	public String loc_paymentType = "//span[contains(text(),'@paymentType')]";
	
	public AddPaymentTypePageActions() {
		ExecutionLog.log("==========| AddPaymentTypePage |==========");	
		PageFactory.initElements(driver, this);
	}

	
	public void createAccount(String AccountType, String AccountNumber) {
		ExecutionLog.log("Create the account ");
		
		
		
	}
	
	
}
