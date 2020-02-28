package com.pfs.reporting;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.pfs.test.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;


public class ExecutionLog {	

  
	/**
	 * @For_BUG: ExecutionLog.Log(color("red", "Mention Bug ID/Related Information"));
	 * @param text
	 * @param Status
	 */
	public static void log(String text) {
		// Escaping the html code to be shown in the report
		final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
		System.setProperty(ESCAPE_PROPERTY, "false");
		logging(text);
	}

	public static void logging(String text) {
	try {
			if(text.contains("Verified")) {  
				System.out.println(text);
				ExtentTestManager.getTest().log(LogStatus.PASS,  text);
			} else if(text.contains("BUG") || text.contains("Failed")) {
				TestBase.TestCaseStatus = false;
				text = "[FAIL]  "+ text;
				System.out.println(text);
//				ExtentTestManager.getTest().log(LogStatus.FAIL,color("red", text));
				ExtentTestManager.getTest().log(LogStatus.INFO,color("red", text));
			} else if(text.contains("ScreenShot") ) { 

			} else {
				System.out.println(text);
				ExtentTestManager.getTest().log(LogStatus.INFO, text);
			}
		} catch (Exception e) { 
			System.err.println("ERROR: " + e.getMessage()); 
		}
	}


	public  String getFileName() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();		 
		String fileName = "Report-"+dateFormat.format(cal.getTime());
		return fileName;
	}

	/**
	 * @author Rajuddin
	 * @param text
	 * @return colored text(blue)
	 * @description : This method is used inside the ExecutionLog.Log()
	 * It will color the text in the Test NG Report
	 */
	public static String color(String color, String text) {
		String coloredtext  = "<span style=\"color:"+ color + "\">"+text+"</span>";
		return coloredtext;
	}

	public String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();	
		String dateTime =  dateFormat.format(cal.getTime());
		return dateTime;
	}
	
	public String getTime() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Calendar cal = Calendar.getInstance();	
		String dateTime =  dateFormat.format(cal.getTime());
		return dateTime;
	}

}
