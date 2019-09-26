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
public class RegisteredPaymentsAndAccountsPageActions extends TestBase {

	@FindBy(how = How.XPATH, using = "//button[@id='mainForm:addBt']//span[contains(text(),'Add payment type')]") 		public WebElement addPaymentType;
	public String loc_accountNumber = "//td[contains(text(),'@paymentType')]/..//td/div[text()='@accountNumber']";

	//public String loc_accountNumber = "//td[contains(text(),'" + paymentType  +"')]/..//td/div[text()='" + accountNumber + "']";
	public String radio_paymentType = "//td[text()='" + paymentType  +"']/../td/div[contains(@class,'radiobutton')]";
	public String btn_edit = "//span[contains(text(),'Edit')]";
	
	
	public RegisteredPaymentsAndAccountsPageActions() {
		ExecutionLog.log("==========| Registered Payments And Accounts Page |==========");	
		PageFactory.initElements(driver, this);
	}

	/**
	 * Select provided payment type at Registered Payments And Accounts
	 * @param paymentType
	 */
	public void selectPaymentType(String paymentType) {
		ExecutionLog.log("[INFO] Selecting payment type: " + paymentType);
		CommonMethods.getElement(radio_paymentType).click();
		
	}
	
	/**
	 * Click on Edit button
	 */
	public void clickOnEditButton() {
		ExecutionLog.log("[INFO] Clicking on Edit button.");
		CommonMethods.getElement(btn_edit).click();
	
	}
	
	
	
	
}
