package com.pfs.testengine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.testng.annotations.Test;

import com.pfs.utility.DBConnection;

public class MonthlyReports {

	ArrayList<String> CSVData = new  ArrayList<String> ();
	BufferedReader csvReader = null;
	String fileName = "6_40_60_3_citibank_cancel_1.csv";

	String paiment_id;
	String acct_nbr;
	String userid_nbr;
	String payment_type_code;
	String amt_12;
	String conference_nbr;
	String creditdebit_indx;
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	

	@Test
	public void insertData() throws Exception {
		//sdf.parse(date.getDate());
		insertCSVDataInDB(fileName);
	}

	public String createInsertionQuery(String tableName, String columnNames, String Values){		
		 return ("INSERT INTO "+ tableName +"("+columnNames + ") VALUES" + "("+ Values + ") ;" ).replace("[", "").replace("]", "");
		
	}
	
	private String getColumnNames_MonthlyReport() {
		return "payment_id,account_number,userid_number,payment_type_code,transaction_date,transaction_time,status_code,payment_date,amount_1,amount_2,amount_3,amount_4,amount_5,amount_6,amount_7,amount_8,amount_9,amount_10,amount_11,amount_12,clear_fi_number,confirmation_extension,confirmation_number,credit_debit_index,debit_fi_account_number,debit_fi_branch_number,debit_fi_number,due_date,filing_userid,input_method_code,member_fi_number,operator_id,payment_on_account,recipient_id,revenue_expense_transit_number,report_thru_date,fi_settlement_date,recipient_settlement_date,payment_extension_number,otc_flag,otc_company_name,otc_ref_info,created_by,created_date,updated_by,updated_date";
	}
	
	
	private String getValues_MonthlyReport() {
	//	getDate();
		String var_TransDate 			 ="20200302";
		String var_TransTime 			 ="9464348";
	//	String var_TransTime 			 ="9464348";
		String var_PaymentDate 			 ="20200302";
		String var_FillingDate  		 ="20200302";
		String var_ReportThruDate  		 ="20200302";
		String var_fi_settlement_date 	 ="20200302";
		String var_recipient_settlement_date = "0";
		String var_CreatedDateWithTime = "2020-01-01 10:00:00";
		String var_UpdatedDateWithTime = "2020-01-01 10:00:00";
		
		return CSVData.get(0)+"," +CSVData.get(1) +"," +CSVData.get(2)  + "," +CSVData.get(3) + "," + var_TransDate + "," + var_TransTime+ "," +CSVData.get(15)+ "," + var_PaymentDate + "," +CSVData.get(4)+ ",0,0,0,0,0,0,0,0,0,0" +  "," +CSVData.get(4) +",0,0," + CSVData.get(5)+ "," +CSVData.get(6)+"," + CSVData.get(7) +"," + CSVData.get(8)+"," + CSVData.get(9)+ "," + var_FillingDate +"," + CSVData.get(10)+",'W' "+ "," + CSVData.get(11) + ",'G_N'" +"," + CSVData.get(12)  +"," + CSVData.get(13) +"," + CSVData.get(14)+ ","+ var_ReportThruDate+ ","+ var_fi_settlement_date+","+ var_recipient_settlement_date+",0,'N','Askida','Askida','QA1', '"+var_CreatedDateWithTime+"' ,'QA1','"+var_UpdatedDateWithTime+"'";

	}
	
/*	public String getDate() {
		
	}*/
	
	
	public void insertCSVDataInDB(String fileName) throws Exception {
		csvReader = new BufferedReader(new FileReader(fileName));
		String row;
		while ((row = csvReader.readLine()) != null) {
			
			if(!row.split(",")[0].contains("id")) {
				for(int i =0; i<row.split(",").length; i++) {
					CSVData.add(row.split(",")[i]);
				}
			DBConnection.executeInsertDeleteQuery(createInsertionQuery("paytax.payment", getColumnNames_MonthlyReport(), getValues_MonthlyReport()));

			CSVData.clear();
	    	}
    	 }	
		csvReader.close();
	}
	
	//@Test
	public void dateTest() throws Exception {
		String FORMAT = "dd/MM/yyyy";
		String BaseDate_String = "15/02/2019";

		
		String dateToFormat = "21/07/2018";
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
		Date d = sdf.parse(dateToFormat);
		Date BaseDate = sdf.parse(BaseDate_String);
		
		
		if (d.compareTo(BaseDate) < 0) {
            System.out.println("d is before BaseDate");
        } 
		
        

	}
	
}
