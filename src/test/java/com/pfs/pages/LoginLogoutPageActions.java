package com.pfs.pages;

import static com.pfs.utility.CommonMethods.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.pfs.reporting.ExecutionLog;
import com.pfs.test.base.TestBase;

public class LoginLogoutPageActions {

	String pageTitle = "Tax Filing / Service de déclaration fiscale";
	
	public static String userName = "//input[@id='userId']";
	public static String password = "//input[@id='passwordId']";
	public static String logonBtn = "//input[@id='logonBtnId'] |//button[@type='submit']";
	
	public LoginLogoutPageActions() {
		initSession();
		PageFactory.initElements(TestBase.getDriver(), this);
	}
	
	
	
	/**
	 * login to application using provided credentials
	 * @param username
	 * @param pass
	 * @throws Exception 
	 */
	public void getLogin(String username, String pass) throws Exception {
		ExecutionLog.log("Entering user name.");
		sendKeys(userName, username, "username");
		sendKeys(password, pass, "password");
		clickOn(logonBtn, "logonBtn");
		
		//waitForInVisibilityOf(DashboardScreen.getLoadingSpinner(), 90, "Loading Spinner");
	}
}
