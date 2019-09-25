package com.pfs.utility;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.annotations.Test;



public class TestSuiteRunner {

	@Test
	public void executeTestSuite() {
		TestNG  tNG = new TestNG();
		List<String> xml = new ArrayList<String>();
		System.out.println("Working.");
		xml.add(System.getProperty("testSuite"));
		tNG.setTestSuites(xml);
		tNG.run();
	}
	
}
