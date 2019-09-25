package com.pfs.reporting;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.pfs.test.base.TestBase;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
	private static LocalDateTime now = LocalDateTime.now();
	private static String datetime  = dtf.format(now);
	//private static ExtentReports extent;
    private static String reportFileName = "Comcast_Test-Automaton-Report_"+datetime+".html";
    protected static String fileSeperator = System.getProperty("file.separator");
    protected static String reportFilepath = System.getProperty("user.dir") +fileSeperator+ "TestReport";
    private static String reportFileLocation =  reportFilepath +fileSeperator+ reportFileName;
    final static String filePath = System.getProperty("user.dir") + "\\test-output\\AdvanceReport\\comcast.html";
    
    public synchronized static ExtentReports getReporter() {
        if (TestBase.report == null) {
        	TestBase.report = new ExtentReports(filePath, true);
        }
        
        return TestBase.report;
    }
 
   /* public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }
 /*
    //Create an extent report instance
    public static ExtentReports createInstance() {
    	
        String fileName = getReportPath(reportFilepath);
        
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(reportFileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(reportFileName);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
 
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        //Set environment details
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("AUT", "QA");
 
        return extent;
    }
     
    //Create the report path
    private static String getReportPath (String path) {
    	File testDirectory = new File(path);
        if (!testDirectory.exists()) {
        	if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!" );
                return reportFileLocation;
            } else {
                System.out.println("Failed to create directory: " + path);
                return System.getProperty("user.dir");
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
		return reportFileLocation;
    }
 
  
   
  */
    
	
	
}

