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
//	@FindBy(how = How.XPATH, using = "//input[@id='userId']") 		public static WebElement userName;
//	@FindBy(how = How.XPATH, using = "//input[@id='passwordId']") 		public static WebElement password;
//	@FindBy(how = How.XPATH, using = "//input[@id='logonBtnId'] |//button[@type='submit']") 		public static WebElement logonBtn;
	
	
	public static String userName = "//input[@id='userId']";
	public static String password = "//input[@id='passwordId']";
	public static String logonBtn = "//input[@id='logonBtnId'] |//button[@type='submit']";
	
	public LoginLogoutPageActions() {
		initSession();
		PageFactory.initElements(TestBase.getDriver(), this);
	}

	@FindBy(how = How.XPATH, using = "//form[@action='/authenticate']//p[text()='Login']")
	WebElement txt_Login;
	
	
	/**
	 * Verify user lands on login page. Asserts page title and Login text 
	 * @param title
	 */
	public void verifyUserIsOnLoginPage(String title) {
		ExecutionLog.log("Verifying user is on Login page.");
		verifyPageTitle(title);		
		ensureVisibilityOf(txt_Login, "Login Text");
		
	}
	
	/**
	 * login to application using provided credentials
	 * @param username
	 * @param pass
	 * @throws Exception 
	 */
	public void getLogin(String username, String pass) throws Exception {
		//verifyUserIsOnLoginPage(pageTitle);
		ExecutionLog.log("Entering user name.");
		sendKeys(userName, username, "username");
		sendKeys(password, pass, "password");
		clickOn(logonBtn, "logonBtn");
		
		/*userName.sendKeys(username);
		ExecutionLog.log("Entering user password.");
		password.sendKeys(pass);
		ExecutionLog.log("Clicking submit button.");
		password.submit();*/
		//waitForInVisibilityOf(DashboardScreen.getLoadingSpinner(), 90, "Loading Spinner");
	}
}
