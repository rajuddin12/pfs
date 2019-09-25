package com.pfs.utility;

public class ConstantsVar {
	
	private ConstantsVar() {
	    throw new IllegalStateException("Utility class");
	  }
	
	public static final String REPORTCONFIGFILE = System.getProperty("user.dir") +  "/" + "Config" + "/" +  "extent-config.xml";
	public static final String MODULARPATH= "D:/telusc/TAF_COMCAST/ExecutionReports/Modular/";
	public static final String DOTCOMPATH= "D:/telusc/TAF_COMCAST/ExecutionReports/Dotcom/";
	public static final String ORDERLABPATH= "D:/telusc/TAF_COMCAST/ExecutionReports/OrderLab/";
	
	
	

}
