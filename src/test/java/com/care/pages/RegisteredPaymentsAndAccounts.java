package com.care.pages;

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
public class RegisteredPaymentsAndAccounts extends TestBase {

	@FindBy(how = How.XPATH, using = "//span[@class='ddmenuicon collapsed']") 		public WebElement CapacityPlanningCollapsed;
	

	
	public RegisteredPaymentsAndAccounts() {
		ExecutionLog.log("==========| Registered Payments And Accounts Page |==========");	
		PageFactory.initElements(driver, this);
	}

	
	
	
	
}
