package com.pfs.testengine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.Test;

public class UpdateCSVFile {

	static String[] data;
	static long   payment_id = 350000;
	static int    rowNo		 = 0;		// Temporary variable to write 0.00(1st row) and 999999.99(2nd row) into the amt_12 field.

	/**
	 * To update the specific CSV file
	 * Run the method "updateSigleCSV_File()"
	 */
	static String fileName   = "6_40_1_6_bmo_dlc_freetrial_tax_payments_processed.csv";

	/**
	 * To update the all CSV files of the specific folder
	 * Run the method "updateAllCSVFiles_Under_Folder()"
	 */
	static String folderName = "RBC";

	static List<String> allCSVFiles = new ArrayList<String>();

	@Test
	public static void updateSigleCSV_File() throws Exception {
		BufferedReader csvReader = null;
		csvReader = new BufferedReader(new FileReader(fileName));
		File f = new File("Updated_"+fileName);
		FileWriter fw = new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fw);
		String row;
		while ((row = csvReader.readLine()) != null) {
			data = row.split(",");
			if(data[0].contains("id")) {
				bw.write("paiment_id,acct_nbr,userid_nbr,payment_type_code,amt_12,conference_nbr,creditdebit_indx,debit_fi_acct_nbr,debit_fi_branch_nbr,debit_fi_nbr,filing_userid,mbr_fi_nbr,payment_on_acct,recipient_id,revenue_expense_transit_nbr,status_cd");
				bw.write("\n");
				continue;
			}

			bw.write(addSemiColunToAlldata());
			bw.write(updateRow(data));
			bw.write("\n");

		}
		csvReader.close();
		bw.close();
		System.out.println("Success!!");
	}

	@Test
	public static void updateAllCSVFiles_Under_Folder() throws Exception {
		BufferedReader csvReader = null;

		//Read all CSV file placed under current Directory
		File folder = new File(System.getProperty("user.dir")+ File.separator + folderName);
		listFilesForFolder(folder);
		Iterator<String> iter = allCSVFiles.iterator();

		while(iter.hasNext()) {
			fileName = iter.next();
			csvReader = new BufferedReader(new FileReader(System.getProperty("user.dir") +File.separator + folderName +File.separator + fileName));
			File f = new File(folderName+ File.separator + "Updated_"+fileName);
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			String row;
			while ((row = csvReader.readLine()) != null) {
				data = row.split(",");
				if(data[0].contains("id")) {
					bw.write("paiment_id,acct_nbr,userid_nbr,payment_type_code,amt_12,conference_nbr,creditdebit_indx,debit_fi_acct_nbr,debit_fi_branch_nbr,debit_fi_nbr,filing_userid,mbr_fi_nbr,payment_on_acct,recipient_id,revenue_expense_transit_nbr,status_cd");
					bw.write("\n");
					continue;
				}

				//bw.write(addSemiColunToAlldata());
				//bw.write(updateRow(data));
				bw.write(removeextraSingleQuotes(data));
				bw.write("\n");

			}
			csvReader.close();
			bw.close();
			System.out.println("Successfully updated '" + fileName + "'");
		}
	}

	static String updateRow(String str[]) {
		String pattern = "######.##";
		DecimalFormat decimalFormat = new DecimalFormat(pattern);

		String fStr="";
		for(int i=0;i<str.length;i++) {
			if(i==0) {

				fStr="'"+payment_id+"'"+","; continue;

			}if(i==1 && !str[1].contains("'")) {

				fStr+="'"+str[1]+"',"; continue;

			}if(i==4) {						
				if(rowNo==0) {
					fStr+="'0.00',";
					rowNo++;   //  rowNo = 1 
				} else if(rowNo==1) {
					fStr+="'99999999.99',";
					rowNo++;  //rowNo = 2 
				} else fStr+="'" +handleDecimalTwoPoints(decimalFormat.format((Math.random()*((99999999.99-0.00)+1))+0.00))+"',";

				continue;

			}if(i==5) {

				fStr+="'"+payment_id+"'"+","; continue;
			}
			if(i==7 && !str[7].contains("'")) {

				fStr+="'"+str[7]+"',"; continue;
			}
			if(i==str.length-1) {

				fStr+=str[i];  continue;

			}

			fStr+=str[i]+",";			
		}

		payment_id++;
		return fStr;
	}

	public static String handleDecimalTwoPoints(String number) {

		if(!number.contains(".") ) {			
			number = number.concat(".12");
		} else if(number.split("\\.")[1].length()<2)			
			number = number.concat("2");

		return number;
	}

	/**
	 * Add the Semicolun to the all data of the respective row
	 * return the complete row as String so that it can used to write in csv file
	 * 
	 */
	public static String addSemiColunToAlldata() {
		String fStr="";

		for(int i =0; i<data.length; i++) {
			if(data[i].contains("'")) {
				data[i]= data[i].replace("'", "");
				data[i] = "'" + data[i]+ "'";// Add Semicoluns
			}

			fStr+=data[i]+",";	    // Covert data into string from Array
		}
		return fStr;
	}	

	public static String removeextraSingleQuotes(String str[]) {
		String fStr="";
		String pattern = "######.##";
		DecimalFormat decimalFormat = new DecimalFormat(pattern);

		List<Integer> integerColumns = new ArrayList<Integer>();
		integerColumns.add(0);   	// 	paiment_id
		integerColumns.add(2);   	// 	userid_nbr
		integerColumns.add(3);   	//	payment_type_code
		integerColumns.add(4);   	//	amt_12
		integerColumns.add(5);  	// 	Confirmation Number
		integerColumns.add(6); 		//	creditdebit_indx
		integerColumns.add(8);  	//	debit_fi_branch_nbr
		integerColumns.add(9);  	//	debit_fi_nbr
		integerColumns.add(10); 	// 	filing_userid
		integerColumns.add(11); 	//	mbr_fi_nbr
		integerColumns.add(13);    	//	recipient_id
		integerColumns.add(14);	  	// 	revenue_expense_transit_nbr

		for(int i=0;i<str.length;i++) {

			if(integerColumns.add(i)) {
				data[i]= data[i].replace("'", "");// remove Semicolons
			} if(i==4) {						
				if(rowNo==0) {
					fStr+="'0.00',";
					rowNo++;   //  rowNo = 1 
				} else if(rowNo==1) {
					fStr+="'99999999.99',";
					rowNo++;  //rowNo = 2 
				} else fStr+="'" +handleDecimalTwoPoints(decimalFormat.format((Math.random()*((9999.99-0.00)+1))+0.00))+"',";
			}
			fStr+=str[i]+",";	
		}
		return fStr;
	}
	public static List<String> listFilesForFolder(final File folder) {

		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				allCSVFiles.add(fileEntry.getName());
				System.out.println(fileEntry.getName());
			}
		}

		return allCSVFiles;
	}
}
