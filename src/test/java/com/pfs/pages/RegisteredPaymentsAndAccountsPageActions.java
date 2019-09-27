package com.pfs.pages;

import com.pfs.test.base.TestBase;
/**
 * This class contains all possible UI elements (and its actions) of RegisteredPaymentsAndAccounts Page
 *
 */
public class RegisteredPaymentsAndAccountsPageActions extends TestBase {

	public static String  loc_addPaymentType;
	public static String loc_accountNumber ;
	public static String radio_paymentType ;
	public static String btn_edit;
	
	
	public RegisteredPaymentsAndAccountsPageActions() {
		loc_addPaymentType 	= "//button[@id='mainForm:addBt']//span[contains(text(),'Add payment type')]";
		 loc_accountNumber 	= "//td[contains(text(),'" + var_paymentType  +"')]/..//td/div[text()='" + var_accountNumber + "']";
		 radio_paymentType 	= "//td[text()='" + var_paymentType  +"']/../td/div[contains(@class,'radiobutton')]";
		 btn_edit 			= "//span[contains(text(),'Edit')]";
		
	}
}
