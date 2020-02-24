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
	public static String btn_remove;
	
	
	public RegisteredPaymentsAndAccountsPageActions() {

		loc_addPaymentType 	= "//button[@id='mainForm:addBt']//span[contains(text(),'Add payment type')]";
		loc_accountNumber 	= "//td[contains(text(),'" + var_paymentType  +"')]/..//td/div[text()='" + var_accountNumber + "']";
		radio_paymentType 	= "//td[contains(text(),'" + var_paymentTypeSpace  +"')]/../td/div[contains(@class,'radiobutton')]";
		btn_edit 			= "//span[contains(text(),'Edit')]";
		btn_remove 			= "//span[contains(text(),'Remove')]";

	}
}
