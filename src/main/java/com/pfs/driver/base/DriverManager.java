package com.pfs.driver.base;

import org.openqa.selenium.WebDriver;

public class DriverManager {


		private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
		private static String envType;
		private static String browser;
		private static String runType;

		/**
		 * This method is used to get run type.
		 * 
		 * @return
		 */
		public static String getRunType() {
			return runType;
		}

		/**
		 * This method is used to set run type.
		 * 
		 * @param runType
		 */
		public static void setRunType(String runType) {
			DriverManager.runType = runType;
		}

		/**
		 * This method is used to get browser.
		 * 
		 * @return
		 */
		public static String getBrowser() {
			return browser;
		}

		/**
		 * This method is used to set browser.
		 * 
		 * @param browser
		 */
		public static void setBrowser(String browser) {
			DriverManager.browser = browser;
		}

		/**
		 * This method is used to get env type.
		 * 
		 * @return
		 */
		public static String getEnvType() {
			return envType;
		}

		/**
		 * This method is used to set env type.
		 * 
		 * @param envType
		 */
		public static void setEnvType(String envType) {
			DriverManager.envType = envType;
		}

		/**
		 * This method is used to get driver.
		 * 
		 * @return
		 */
		public static WebDriver getDriver() {
			return webDriver.get();
		}

		/**
		 * This method is used to set driver.
		 * 
		 * @param driver
		 */
		public static void setWebDriver(WebDriver driver) {
			webDriver.set(driver);
		}
}
