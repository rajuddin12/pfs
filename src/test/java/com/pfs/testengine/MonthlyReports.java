package com.pfs.testengine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pfs.utility.DBConnection;

public class MonthlyReports {

	ArrayList<String> CSVData = new  ArrayList<String> ();
	BufferedReader csvReader = null;

	String paiment_id;
	String acct_nbr;
	String userid_nbr;
	String payment_type_code;
	String amt_12;
	String conference_nbr;
	String creditdebit_indx;

	@Test
	public void testing() throws Exception {
		insertCSVDataInDB("6_40_1_2_bmo_cancel.csv");
	}

	public String createInsertionQuery(String tableName, String columnNames, String Values){		
		 return ("INSERT INTO "+ tableName +"("+columnNames + ") VALUES" + "("+ Values + ") ;" ).replace("[", "").replace("]", "");
		
	}
	
	private String getColumnNames_MonthlyReport() {
		return "payment_id,account_number,userid_number,payment_type_code,transaction_date,transaction_time,status_code,payment_date,amount_1,amount_2,amount_3,amount_4,amount_5,amount_6,amount_7,amount_8,amount_9,amount_10,amount_11,amount_12,clear_fi_number,confirmation_extension,confirmation_number,credit_debit_index,debit_fi_account_number,debit_fi_branch_number,debit_fi_number,due_date,filing_userid,input_method_code,member_fi_number,operator_id,payment_on_account,recipient_id,revenue_expense_transit_number,report_thru_date,fi_settlement_date,recipient_settlement_date,payment_extension_number,otc_flag,otc_company_name,otc_ref_info,created_by,created_date,updated_by,updated_date";
	}
	
	
	private String getValues_MonthlyReport() {
		String var_TransDate 			 ="20191223";
		String var_TransTime 			 ="9464348";
		String var_PaymentDate 			 ="20191223";
		String var_FillingDate  		 ="20191223";
		String var_ReportThruDate  		 = "20191223";
		String var_fi_settlement_date 	 = "20191223";
		String var_recipient_settlement_date = "0";
		String var_CreatedDateWithTime = "2019-12-17 10:00:00";
		String var_UpdatedDateWithTime = "2019-12-17 10:00:00";
		
		return CSVData.get(0)+"," +CSVData.get(1) +"," +CSVData.get(2)  + "," +CSVData.get(3) + "," + var_TransDate + "," + var_TransTime+ "," +CSVData.get(15)+ "," + var_PaymentDate + "," +CSVData.get(4)+ ",0,0,0,0,0,0,0,0,0,0" +  "," +CSVData.get(4) +",0,0," + CSVData.get(5)+ "," +CSVData.get(6)+"," + CSVData.get(7) +"," + CSVData.get(8)+"," + CSVData.get(9)+ "," + var_FillingDate +"," + CSVData.get(10)+",'W' "+ "," + CSVData.get(11) + ",'G_N'" +"," + CSVData.get(12)  +"," + CSVData.get(13) +"," + CSVData.get(14)+ ","+ var_ReportThruDate+ ","+ var_fi_settlement_date+","+ var_recipient_settlement_date+",0,'N','Askida','Askida','QA1', '"+var_CreatedDateWithTime+"' ,'QA1','"+var_UpdatedDateWithTime+"'";

	}
	
	public void insertCSVDataInDB(String fileName) throws Exception {
		csvReader = new BufferedReader(new FileReader(fileName));
		String row;
		while ((row = csvReader.readLine()) != null) {
			for(int i =0; i<row.split(",").length; i++) {
				CSVData.add(row.split(",")[i]);
			}
			System.out.println(createInsertionQuery("paytax.payment", getColumnNames_MonthlyReport(), getValues_MonthlyReport()));
			DBConnection.executeInsertDeleteQuery(createInsertionQuery("paytax.payment", getColumnNames_MonthlyReport(), getValues_MonthlyReport()));

			CSVData.clear();
		}	
	}
	
}
