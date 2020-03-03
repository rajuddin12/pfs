package com.pfs.utility;

import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.pfs.reporting.ExecutionLog;
import com.pfs.reporting.ExtentTestManager;
import com.pfs.reporting.ReportScreenshot;
import com.pfs.test.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class CommonMethods extends TestBase {

	public static WebElement getElement(String locator) {

		By by = null;

		if (locator.startsWith("//") || locator.startsWith("(//")) {
			by = By.xpath(locator);
		} else if (locator.startsWith("css=")) {
			by = By.cssSelector(locator.replace("css=", ""));
		} else if (locator.startsWith("name=")) {
			by = By.name(locator.replace("name=", ""));
		} else if (locator.startsWith("link=")) {
			by = By.linkText(locator.replace("link=", ""));
		} else {
			by = By.id(locator);
		}
		return driver.findElement(by);
	}

	
	
	public static List<WebElement> getElements(String locator) {

		By by = null;

		if (locator.startsWith("//")) {
			by = By.xpath(locator);
		} else if (locator.startsWith("css=")) {
			by = By.cssSelector(locator.replace("css=", ""));
		} else if (locator.startsWith("name=")) {
			by = By.name(locator.replace("name=", ""));
		} else if (locator.startsWith("link=")) {
			by = By.linkText(locator.replace("link=", ""));
		} else {
			by = By.id(locator);
		}
		return driver.findElements(by);
	}

	public static void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();		

	}

	/**
	 * Return alert text
	 * @param driver
	 * @return
	 */
	public static String getAlertText(WebDriver driver) {
		return driver.switchTo().alert().getText();		

	}


	public static void clickOn(WebElement element, String elementName) {
		element.click();
		ExecutionLog.log("Clicked on the " + elementName);
	}

	public static void clickOn(String locator, String webElementNameOfLocator) throws Exception {
		if(webElementNameOfLocator.length()>1) {
		getElement(locator).click();
		ExecutionLog.log("Clicked on the '" + webElementNameOfLocator + "'");
		
		}
	}

	public static void sendKeys(String locator, String TestData, String webElementNameOfLocator) throws Exception {
		if(TestData.length()>1) {
			getElement(locator).clear();
			getElement(locator).sendKeys(TestData);
			ExecutionLog.log("Entered \"" + TestData + "\" in field '" + webElementNameOfLocator + "'");
			Thread.sleep(2000);
		}
		
	}
	
	public static void sendKeys(WebElement elem, String TestData, String webElementNameOfLocator) throws Exception {
		if(TestData.length()>1) {
			elem.clear();
			elem.sendKeys(TestData);
			ExecutionLog.log("Entered \"" + TestData + "\" in field '" + webElementNameOfLocator + "'");
			Thread.sleep(2000);
		}
		
	}


	public static WebElement clickOndisplayedElement(String locator, String elementName) {
		List<WebElement> allElements = getElements(locator);
		WebElement dispElem = allElements.get(0);
		for(WebElement elem : allElements){
			if(elem.isDisplayed()){
				dispElem  = elem;
				return dispElem;
			}
		}
		return dispElem;
	}

	public static void checkRadioBtn(WebElement element, String elementName) {
		element.click();
		ExecutionLog.log("Checked the " + elementName);
		try {
			Assert.assertTrue(element.isSelected());
			ExecutionLog.log("Verified that Radio Button " + elementName + " is Checked ");
		} catch (AssertionError e) {
			exceptionController(e);
		}

	}

	public static void unCheckRadioBtn(WebElement element, String elementName) {
		element.click();
		ExecutionLog.log("Un-Checked the " + elementName);
		Assert.assertFalse(element.isSelected());
		ExecutionLog.log("Verified that Radio Button " + elementName + " is Un-Checked ");
	}

	public static void selectValueFromDropDown(String locator, String dropdownValue, String elementName) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(dropdownValue);
		ExecutionLog.log("Selected '" + dropdownValue + "' in " + elementName);
	}
	
	public static void selectValueFromDropDown(WebElement element, String dropdownValue, String elementName) {
		Select select = new Select(element);
		select.selectByVisibleText(dropdownValue);
		ExecutionLog.log("Selected '" + dropdownValue + "' in " + elementName);
	}

	/**
	 * This method is used to get the first selected text or currently selected option text
	 * @param element
	 * @return WebElement
	 */
	public static WebElement getSelectedTextFromDropDown(WebElement element) {
		Select select = new Select(element);
		return select.getFirstSelectedOption();

	}

	public static void enterDataIn(WebElement element, String testData, String elementName) {
		element.clear();
		element.sendKeys(testData);
		ExecutionLog.log("Entered " + testData + " in " + elementName);

	}

	/**
	 * Set input value using JS
	 * @param element
	 * @param testData
	 * @param elementName
	 */
	public static void enterDataUsingJS(WebElement element, String testData, String elementName) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].setAttribute('value', '" + testData + "')", element);
		ExecutionLog.log("Entered " + testData + " in " + elementName);

	}

	/**
	 * Press enter
	 * @param element
	 */
	public static void pressEnter(WebElement element) {
		element.sendKeys(Keys.ENTER);

	}

	/**
	 * @author Rajuddin
	 * @param Locator
	 * @return value of the attribute of Webelement
	 */
	public static String getAttribute(WebElement element, String attribute){
		String attributeValue = "No element is present on Web Page (CommonMethod.getAttribute(WebElement element, String attribute))";
		if(element.isDisplayed()) {
			attributeValue = element.getAttribute(attribute);
		}
		return attributeValue;
	}

	public static void waitForVisibilityOf(WebElement element, int timeOutInSeconds, String elementName) {
		try {
			ExecutionLog.log("Waiting for " + elementName + "........");
			FluentWait<WebDriver> wait =new FluentWait<WebDriver>(driver)
					.withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
					.pollingEvery(1, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.visibilityOf(element));

			ExecutionLog.log("The " + elementName + " is visible now");
		} catch (Exception e) {
			// TODO: handle exception
		}
		

	}
	
	public static void waitForVisibilityOf(String loc, int timeOutInSeconds, String elementName) {
		try {
			
			ExecutionLog.log("Waiting for " + elementName + "........");
			FluentWait<WebDriver> wait =new FluentWait<WebDriver>(driver)
					.withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
					.pollingEvery(1, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.visibilityOf(getElement(loc)));

			ExecutionLog.log("The " + elementName + " is visible now");
		} catch (Exception e) {
			// TODO: handle exception
		}
		

	}

	public static void waitForInVisibilityOf(List<WebElement> element, int timeOutInSeconds, String elementName) {
		try {
			Thread.sleep(5000);
			if(element.size()>0) {			
				//ExecutionLog.Log("Waiting for " + elementName + " to be Invisible........");
				FluentWait<WebDriver> wait =new FluentWait<WebDriver>(driver)
						.withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
						.pollingEvery(1, TimeUnit.SECONDS)
						.ignoring(NoSuchElementException.class)
						.ignoring(StaleElementReferenceException.class);
				wait.until(ExpectedConditions.invisibilityOfAllElements(element));
				//ExecutionLog.Log("The " + elementName + " is Invisible now");
				Thread.sleep(5000);
			}
		} catch(Exception e) {

		}
	}

	/**
	 * Return the value of provided web element
	 * @param e
	 * @return
	 */
	public static String getValueOfElement(WebElement e) {
		return e.getAttribute("value");

	}

	/**
	 * This Method can be used for:
	 * Element is Editable
	 * Element is not Editable
	 * Element should accept only String OR Integers etc.	 * 
	 * @param element
	 */
	public static Boolean isElementEditable(WebElement element, String data, String elementName) {
		Boolean flag = false;
		enterDataIn(element, data, elementName);
		String actualValue = element.getAttribute("value");
		if(actualValue.equals(data)) {
			flag = true;
			ExecutionLog.log(elementName +  " is editable.");
		} else ExecutionLog.log( elementName +  " is NOT editable.");
		return flag;
	}

	public void elementHighlight(WebElement element, WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].setAttribute('style', 'background: yellow;');", element);

	}

	public void javascriptClick(WebElement element, WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(false)", element);
		element.click();

	}

	public void javascriptClickTrueView(WebElement element, WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true)", element);
		element.click();

	}

	/** Verify following provided call type is present at top-right of the screen
	 * @param callType
	 */
	public static List<String> getAllDropDownValues(WebElement element) {
		List<String> dropDownValues = new ArrayList<>();
		Select select = new Select(element);
		List<WebElement> allOptions = select.getOptions();
		for( WebElement elem : allOptions) {
			dropDownValues.add(elem.getText().trim());
		}
		return dropDownValues;
	}

	/**
	 * Compare lists along with their item's order as present
	 * @param actualDataFromApp
	 * @param expectedData
	 * @param elementName
	 */
	public static void compareListWithItemsOrder(List<String> actualDataFromApp, List<String> expectedData, String elementName) {
		int atualListSize = actualDataFromApp.size();
		int expectedListSize = expectedData.size();
		ExecutionLog.log("Verifying " + elementName + " list");

		if(atualListSize != expectedListSize)
			Assert.assertTrue(false, "[ASSERTION FAILED] " + elementName + " list's items are not in order.");
		else {
			for(int i = 0 ; i < actualDataFromApp.size(); i++) {
				if(!actualDataFromApp.get(i).equals(expectedData.get(i))) {
					System.out.println("atual:="+actualDataFromApp.get(i)+"||"+expectedData.get(i));
					Assert.assertTrue(false, "[ASSERTION FAILED] " + elementName + " list's items are not in order as expected.");
				}
				ExecutionLog.log(actualDataFromApp.get(i));
			}
		}
		ExecutionLog.log("Verified " + elementName + " list's items are in order as expected.");
	}

	public static void keyboard_TAB() throws Exception{
		try {
			Keyboard keyboard = ((HasInputDevices) driver).getKeyboard();
			keyboard.pressKey(Keys.CONTROL);
			keyboard.releaseKey(Keys.TAB);
		} catch (Exception e) {
			// TODO: handle exception
		}
		

	}

	/**
	 * Get forecast period in following format:
	 * 11/2018 - 1/2019
	 * @param startMonth
	 * @param startYear
	 * @param endMonth
	 * @param endYear
	 * @return
	 */
	public static String getForecastPeriod(String startMonth_str, String startYear, String endMonth_str, String endYear) {
		String startMonth_num = String.valueOf(getMonthNumberFromString(startMonth_str));
		String endMonth_num = String.valueOf(getMonthNumberFromString(endMonth_str));

		return startMonth_num + "/" + startYear + " - " + endMonth_num + "/" + endYear;

	}

	/**
	 * This will convert provided month (in String) to month (in integer)
	 * @param monthValue (Should be in format i.e. Jan, Mar etc)
	 * @return
	 */
	public static int getMonthNumberFromString(String monthValue) {
		int month = 0;
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();

		for(String m : months) {
			month++;
			if(m.contains(monthValue))
				break;	    	
		}	    
		return month;
	}

	public static void verifyValueNotPresentInDropDown(WebElement element, String dropdownValue, String elementName) {
		Boolean flag = false;
		Select select = new Select(element);
		List<WebElement> allOptionsSelected = select.getOptions();
		try {
			for( WebElement elem : allOptionsSelected) {
				if(elem.getText().equals(dropdownValue)) {
					Assert.assertTrue(flag);
					ExecutionLog.log("Verified that '" + dropdownValue + "' is NOT present in "+ elementName);
				}
			}
		} catch (AssertionError e) {
			ExecutionLog.log("[ASSERTION FAILED] '" + dropdownValue + "' is NOT present in "+ elementName);
			exceptionController(e);
		}
	}

	
	/**
	 * @author Rajuddin
	 * @param locator
	 * @param webElementNameOfLocator
	 * @throws Exception
	 * @description Verify that the Element is not present on the webpage
	 */
	public static void verifyElementNotPresent(String locator, String webElementNameOfLocator, Object...runStatus) throws Exception {
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ;
	
			try {
				Assert.assertFalse(isElementPresent(locator));
				ExecutionLog.log ("\"" + webElementNameOfLocator + "\" is disable or not Present");
			} catch (AssertionError e) {
				ExecutionLog.log ("Verify The data of " + webElementNameOfLocator);
				ExecutionLog.log("====Failed==== \""+webElementNameOfLocator+"\"  should not be shown");
			//	getScreenShotOnCheckpointFailure(webElementNameOfLocator.replace("*", ""), e, locator);
				e.printStackTrace();
			}	
		driver.manage().timeouts().implicitlyWait(120,TimeUnit.SECONDS) ;
	}
	

	/**
	 * @author Rajuddin
	 * @description Verify that the text is present on the webpage
	 * @return Elemet Status, true: if present on the page else false
	 */
	public static Boolean isElementPresent(String Locator) {
		boolean flag = false;
		List<WebElement> elemList = driver.findElements(By.xpath((Locator)));
		if(elemList.size()>0) {
			flag = true;
		}
		return flag;

	}
	
	/**
	 * @author Rajuddin
	 * @description Verify that the text is present on the webpage
	 * @return Elemet Status, true: if present on the page else false
	 */
	public static Boolean isElementDisplayed(String Locator) {
		return driver.findElement(By.xpath((Locator))).isDisplayed();

	}
	
	/**
	 * Verify element is not present on screen, if present then verify it is not be displayed
	 * @param elements (Uniquely located element)
	 * @param elementName
	 */
	public static void verifyElementIsNotDisplayed(List<WebElement> elements, String elementName) {
		ExecutionLog.log("Verifying " + elementName + " is not Present");
		try { 
			if(elements.size() > 0) {
				Assert.assertFalse(elements.get(0).isDisplayed(), "[ASSERTION FAILED] " + elementName + " is displayed.");

			}
			ExecutionLog.log("Verified " + elementName + " is not Present");
		} catch (AssertionError e) {
			ExecutionLog.log("[Failed]: " + elementName + " is Present");
			exceptionController(e);
		}

	}

	/**
	 * Verify current page title is exact
	 * @param title
	 */
	public static void verifyPageTitle(String title) {
		try { 
			Assert.assertEquals(driver.getTitle().trim(), title.trim(), "[ASSERTION FAILED] Page title is not exact. Actual: " + driver.getTitle() + ", Expected: "+ title);
			ExecutionLog.log("Verified user is on '" + title + "' page.");		
		}
		catch (AssertionError e) {
			ExecutionLog.log("[ASSERTION FAILED] user is NOT on '" + title + "' page.");		
			exceptionController(e);
		}


	}

	/**
	 * Verify element is displayed on page
	 * @param element
	 * @param elementName
	 */
	public static void ensureVisibilityOf(WebElement element, String elementName) {
		try { 
			Assert.assertTrue(element.isDisplayed(), "[ASSERTION FAILED] " + elementName + " is NOT displayed.");
			ExecutionLog.log("Verified that '" + elementName + "' is Displayed");
		} catch (AssertionError e) {
			ExecutionLog.log("[ASSERTION FAILED]: '" + elementName + "' is NOT Displayed");
			exceptionController(e);
		}


	}

	public static void ensureInVisibilityOf(WebElement element, String elementName) {
		try { 
			Assert.assertFalse(element.isDisplayed(), "[ASSERTION FAILED] " + elementName + " is Displayed.");
			ExecutionLog.log("Verified that '" + elementName + "' is Displayed");
		} catch (AssertionError e) {
			ExecutionLog.log("[ASSERTION FAILED]: '" + elementName + "' is Displayed");
			exceptionController(e);
		}


	}

	public static void verifyValueOf(WebElement element, String attributeValue, String expectedValue, String elementName) {
		String actualValue = element.getAttribute(attributeValue).toString();		
		try { 
			Assert.assertEquals(actualValue.trim(), expectedValue);
			ExecutionLog.log("Verified that Expected Value [" + expectedValue + "] is matched with actual value[" + actualValue + "]" + " of " + elementName);
		} catch (AssertionError e) {
			ExecutionLog.log("[ASSERTION FAILED]: Expected Value [" + expectedValue + "] is NOT matched with actual value[" + actualValue + "]" + " of " + elementName);
			exceptionController(e);

		}

	}

	/**
	 * Verify element is enabled
	 * Note: Hidden elements are also enabled.
	 * @param element
	 */
	public static void verifyElementIsEnabled(WebElement element, String elementName) {
		ExecutionLog.log("Verifying element - " + elementName +  " is enabled." );
		try { 
			Assert.assertTrue(element.isEnabled());
			ExecutionLog.log("Verified element - " + elementName + " is enabled." );
		} catch (AssertionError e) {
			ExecutionLog.log("[ASSERTION FAILED]: element - " + elementName + " is disabled." );
			exceptionController(e);
		}

	}

	public static void equalLists(List<String> actualDataFromApp, List<String> expectedData, String elementName){     
		// Check for sizes and nulls

		Boolean flag = true;
		if (actualDataFromApp == null && expectedData == null) flag = true;
		if ((actualDataFromApp == null && expectedData!= null) || (actualDataFromApp != null && expectedData== null) || (actualDataFromApp.size() != expectedData.size()))  {
			flag = false;
		}

		// Sort and compare the two lists          
		Collections.sort(actualDataFromApp);
		Collections.sort(expectedData);  
		if(flag==false) {
			ExecutionLog.log("Actual Data FromApp:" + actualDataFromApp);
			ExecutionLog.log("Expected Data: " + expectedData);
			assertTrue(flag, "[ASSERTION FAILED]: Either Expected OR Actual Value of '\" + elementName + \"' is NULL ");
		} else {
			ExecutionLog.log("Actual Data FromApp:" + actualDataFromApp);
			ExecutionLog.log("Expected Data: " + expectedData);
			assertTrue(actualDataFromApp.equals(expectedData), "[ASSERTION FAILED]: Either Expected is NOT matched with Actual");
			ExecutionLog.log("Verified that Expected and Actual Value of '" + elementName + "' is matched");
		}
	}

	/**
	 * Verify following provided check box is checked if @isChecked is true
	 * Verify following provided check box is unchecked if @isChecked is false 
	 * @param checkbox
	 */
	public static void verifyCheckboxIsChecked(WebElement checkbox, String elementName, boolean isChecked) {
		try {
			if(isChecked) {
				Assert.assertTrue(checkbox.isSelected(), "[Assertion Failed] "+ elementName + " is not selected.");
				ExecutionLog.log("Verified. " + elementName + " is selected.");
			}else {
				Assert.assertFalse(checkbox.isSelected(), "[Assertion Failed] "+ elementName + " is selected.");
				ExecutionLog.log("Verified. " + elementName + " is not selected.");
			}
		} catch (AssertionError e) {
			ExecutionLog.log("[ASSERTION FAILED]: at" + elementName);
			exceptionController(e);
		}
	}

	public static void verifyValueFromDropDown(WebElement element, String dropdownValue, String elementName) {
		Boolean flag = false;
		Select select = new Select(element);
		List<WebElement> allOptionsSelected = select.getOptions();
		for( WebElement elem : allOptionsSelected) {
			if(elem.getText().equals(dropdownValue)) {
				ExecutionLog.log("Verified that '" + dropdownValue + "' is present in "+ elementName);
				flag = true;
				break;
			}
		}
		try {
			Assert.assertTrue(flag);

		} catch (AssertionError e) {
			ExecutionLog.log("[ASSERTION FAILED]: '" + dropdownValue + "' is not present in "+ elementName);
			exceptionController(e);
		}
	}

	/**
	 * Verify the actual and expected strings are equals
	 * @param actualValue
	 * @param expectedValue
	 */
	public static void verifyStringsAreMatching(String actualValue, String expectedValue) {
		try {
			Assert.assertEquals(actualValue.trim(), expectedValue.trim());
			ExecutionLog.log("Verified that expected value [" + expectedValue + "] is matched with actual value [" + actualValue + "]");
		} catch (AssertionError e) {
			ExecutionLog.log("[ASSERTION FAILED]: expected value [" + expectedValue + "] is NOT matched with actual value [" + actualValue + "]");
			exceptionController(e);
		}
	}

	/**
	 * Verify the actual and expected strings are not equals
	 * @param actualValue
	 * @param expectedValue
	 */

	public static void verifyStringsAreNotMatching(String actualValue, String expectedValue) {
		try {
			Assert.assertNotEquals(actualValue.trim(), expectedValue.trim());
			ExecutionLog.log("Verified that expected Value [" + expectedValue + "] is not matched with actual value [" + actualValue + "]");
		} catch (AssertionError e) {
			ExecutionLog.log("[ASSERTION FAILED]: expected Value [" + expectedValue + "] is not matched with actual value [" + actualValue + "]");
			exceptionController(e);
		}
	}

	/**
	 * Verify actual text of provided element with expectedText
	 * @param element
	 * @param expectedValue
	 * @param elementName
	 */
	public static void verifyTextOf(WebElement element, String expectedText, String elementName) {
		String actualValue = element.getText();
		try {
			Assert.assertEquals(actualValue.trim(), expectedText.trim());
			ExecutionLog.log("Verified that Expected Value [" + expectedText + "] is matched with actual value [" + actualValue + "]" + " of " + elementName);
		} catch (AssertionError e) {
			ExecutionLog.log("[ASSERTION FAILED]: Expected Value [" + expectedText + "] is NOT matched with actual value [" + actualValue + "]" + " of " + elementName);

			exceptionController(e);
		}
	}
	
	public static void verifyTextOf(String loc, String expectedText, String elementName) {
		String actualValue = "";
		try {
			
			actualValue = getElement(loc).getText();
			Assert.assertEquals(actualValue.trim(), expectedText.trim());
			ExecutionLog.log("Verified that Expected Value [" + expectedText + "] is matched with actual value [" + actualValue + "]" + " of " + elementName);
		} catch (AssertionError e) {
			ExecutionLog.log(loc);
			ExecutionLog.log("[ASSERTION FAILED]: Expected Value [" + expectedText + "] is NOT matched with actual value [" + actualValue + "]" + " of " + elementName);
			exceptionController(e);
		} catch (Exception e1) {
			ExecutionLog.log(loc);
			e1.printStackTrace();
		}
	}

	/**
	 * Verify actual text of provided element with expectedText
	 * @param element
	 * @param expectedValue
	 * @param elementName
	 */
	public static void verifyPresenceOfElement(String locator,	String elementName) {
		try {
			Assert.assertTrue(isElementPresent(locator));
			ExecutionLog.log("Verified that element [" + elementName + "] is present");
		} catch (AssertionError e) {
			ExecutionLog.log("[ASSERTION FAILED]: Verified that element [" + elementName + "] is NOT present");

			exceptionController(e);
		}
	}
	
	public static void assertTrue(Boolean flag, String msgOnFailures) {
		try {
			Assert.assertTrue(flag, msgOnFailures);
		} catch (AssertionError e) {
			exceptionController(e);
		}
	}

	public static void assertFalse(Boolean flag, String msgOnFailures) {
		try {
			Assert.assertFalse(flag, msgOnFailures);
		} catch (AssertionError e) {
			exceptionController(e);
		}
	}

	public static void exceptionController(AssertionError e) {
		ReportScreenshot.captureAndDisplayScreenShots(driver);
		ExecutionLog.log("assertion Failed");
		// Print stackTrace in Extent Report
		ExtentTestManager.getTest().log(LogStatus.INFO, org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace(e));
		// Print stackTrace in console
		e.printStackTrace();
	}

	/**
	 * Get random float number starting from @start to @end  
	 * @param start
	 * @param end
	 * @return
	 */
	public static double getRandomNumber(float start, float end) {
		DecimalFormat df = new DecimalFormat("0.0");		
		String random = df.format(Math.random()*(end-1) + start); 
		System.out.println("Generated number: " + random);
		double finalNumber = 0.0;
		try {
			finalNumber = Double.parseDouble(random); 
			return finalNumber;
		}catch(Exception e) {System.out.println("Problem in parsing.");}
		System.out.println("Generated number> " + finalNumber);
		return finalNumber;		

	} 


	public static void sleepTime(int timeOutInSecond) throws Exception {
		Thread.sleep(timeOutInSecond*1000);
	}

}
