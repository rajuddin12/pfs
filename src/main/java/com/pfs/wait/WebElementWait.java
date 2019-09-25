package com.pfs.wait;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementWait {
	
	/**
	 * @param element
	 * @param timeOutInSeconds
	 * @param driver
	 */
	public static void waitForElement(WebElement element, int timeOutInSeconds, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		
		
	}
	
	/** 
	 * @param element
	 * @param timeOutInSeconds
	 * @param driver
	 */
	public static void waitForElementVisibility(WebElement element, int timeOutInSeconds, WebDriver driver) {
		FluentWait<WebDriver> wait =new FluentWait<WebDriver>(driver).withTimeout(timeOutInSeconds, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOf(element));
	
		
	}
	
	/**
	 * @param element
	 * @param timeOutInSeconds
	 * @param driver
	 */
	public static void waitForElementCondition(WebElement element, int timeOutInSeconds, WebDriver driver) {
		FluentWait<WebDriver> wait =new FluentWait<WebDriver>(driver).withTimeout(timeOutInSeconds, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions. elementToBeClickable(element));
	
		
	}
	
	public static ExpectedCondition<Boolean> angularHasFinishedProcessing() {
        return new ExpectedCondition<Boolean>() {
         
            public Boolean apply(WebDriver driver) {
                return Boolean.valueOf(((JavascriptExecutor) driver).executeScript("return (window.angular !== undefined) && (angular.element(document).injector() !== undefined) && (angular.element(document).injector().get('$http').pendingRequests.length === 0)").toString());
            }

			
        };
    }
	
	public static void backGroundOpr(WebDriver driver ) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(angularHasFinishedProcessing());
	}
	

}
