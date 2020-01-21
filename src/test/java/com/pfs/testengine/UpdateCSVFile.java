package com.pfs.testengine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.apache.http.annotation.Obsolete;
import org.testng.annotations.Test;

public class UpdateCSVFile {

	static String[] data;
	static long   payment_id = 350000;
	static long   temp_payment_id = 350000;
	static int    rowNo		 = 0;		// Temporary variable to write 0.00(1st row) and 999999.99(2nd row) into the amt_12 field.
    static int numberOfRowsInFile;
	
	/**
	 * To update the specific CSV file
	 * Run the method "updateSigleCSV_File()"
	 */
	static String fileName   = "6_40_2_3_bns_cancel - Updated_Extracted_data-BNS Bank.csv";

	/**
	 * To update the all CSV files of the specific folder
	 * Run the method "updateAllCSVFiles_Under_Folder()"
	 */
	static String folderName = "BNS";

	static List<String> allCSVFiles 		= new ArrayList<String>();
	static List<String> payment_amount_key 		= new ArrayList<String>();
	static Hashtable<String, String>  payment_amount_hashtable = new Hashtable<>();

	static List<Integer> integerColumns = new ArrayList<Integer>();


	@Test(enabled = true)
	public static void updateSigleCSV_File() throws Exception {
		
		integerColumns.add(0);   	// 	paiment_id
		integerColumns.add(2);   	// 	userid_nbr
		integerColumns.add(3);   	//	payment_type_code
		integerColumns.add(4);   	//	amt_12
		integerColumns.add(5);  	// 	Confirmation Number
		integerColumns.add(8);  	//	debit_fi_branch_nbr
		integerColumns.add(9);  	//	debit_fi_nbr
		integerColumns.add(10); 	// 	filing_userid
		integerColumns.add(11); 	//	mbr_fi_nbr
		integerColumns.add(13);    	//	recipient_id
		integerColumns.add(14);	  	// 	revenue_expense_transit_nbr
		integerColumns.add(16);	  	// 	created_date
		
		
		BufferedReader csvReader = null;
		csvReader = new BufferedReader(new FileReader(fileName));
		
		numberOfRowsInFile = getNumberOfRows(csvReader);
		csvReader.close();
		csvReader = new BufferedReader(new FileReader(fileName));
		
		
		File f = new File("updated_CSV_File");
		f.mkdir();
		f = new File("updated_CSV_File" + File.separator + fileName);
		
		FileWriter fw = new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fw);
		String row;
		while ((row = csvReader.readLine()) != null) {
			rowNo++;
			data = row.split(",");
			if(data[0].contains("id")) {
				bw.write("paiment_id,acct_nbr,userid_nbr,payment_type_code,amt_12,conference_nbr,creditdebit_indx,debit_fi_acct_nbr,debit_fi_branch_nbr,debit_fi_nbr,filing_userid,mbr_fi_nbr,payment_on_acct,recipient_id,revenue_expense_transit_nbr,status_cd,created_time");
				bw.write("\n");
				continue;
			}


			bw.write(updateRow(data));
			bw.write("\n");

		}
		csvReader.close();
		bw.close();
		System.out.println("Success!!");
	}

	@Test(enabled = true)
	public static void updateAllCSVFiles_Under_Folder() throws Exception {
		
		integerColumns.add(0);   	// 	paiment_id
		integerColumns.add(2);   	// 	userid_nbr
		integerColumns.add(3);   	//	payment_type_code
		integerColumns.add(4);   	//	amt_12
		integerColumns.add(5);  	// 	Confirmation Number
		integerColumns.add(8);  	//	debit_fi_branch_nbr
		integerColumns.add(9);  	//	debit_fi_nbr
		integerColumns.add(10); 	// 	filing_userid
		integerColumns.add(11); 	//	mbr_fi_nbr
		integerColumns.add(13);    	//	recipient_id
		integerColumns.add(14);	  	// 	revenue_expense_transit_nbr
		integerColumns.add(16);	  	// 	created_date
		
		
		BufferedReader csvReader = null;
		
		//Read all CSV file placed under current Directory
		File folder = new File(System.getProperty("user.dir")+ File.separator + folderName);
		listFilesForFolder(folder);
		Iterator<String> iter = allCSVFiles.iterator();

		while(iter.hasNext()) {
			payment_id = temp_payment_id;
			rowNo  = 0;
			fileName = iter.next();		// iterate to the next available csv file
			
			csvReader = new BufferedReader(new FileReader(System.getProperty("user.dir") +File.separator + folderName +File.separator + fileName));
			
			// Enable below lines to count the rows
			//numberOfRowsInFile = getNumberOfRows(csvReader);
			//csvReader.close();			
			//csvReader = new BufferedReader(new FileReader(System.getProperty("user.dir") +File.separator + folderName +File.separator + fileName));
			
			// create a folder, where updated csv file will be placed
			File f = new File(folderName+ File.separator + "updated_CSV_Files");
			f.mkdir();
			f = new File(folderName+ File.separator + "updated_CSV_Files" + File.separator + fileName.replace(".csv", "").split(" ")[0].replace("updated", "")+".csv");
			
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			String row;
			
			// updating the specific file as per the rule
			while ((row = csvReader.readLine()) != null) {
				rowNo++;
				data = row.split(",");
				if(data[0].contains("id")) {
					bw.write("paiment_id,acct_nbr,userid_nbr,payment_type_code,amt_12,conference_nbr,creditdebit_indx,debit_fi_acct_nbr,debit_fi_branch_nbr,debit_fi_nbr,filing_userid,mbr_fi_nbr,payment_on_acct,recipient_id,revenue_expense_transit_nbr,status_cd,created_time");
					bw.write("\n");
					continue;
				}

				bw.write(updateRow(data));
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
        	if(i==0 || i==5) {
        		fStr  += applySingleQuote(payment_id+"",i)+",";
        	} else if(i==4) {				
        		if(rowNo==2) {
        			fStr+=applySingleQuote("'0.00'",i)+","; 
        			payment_amount_hashtable.put(str[3], "0.00");
        		} else if(rowNo==3) {	
        			fStr+=applySingleQuote("'9999.99'",i)+","; 
        			payment_amount_hashtable.put(str[3], "9999.99");
        		} else 
        			// set the value of respective key in the hashtable
        			if(!payment_amount_hashtable.containsKey(str[3])) {
        				str[4] = handleDecimalTwoPoints(decimalFormat.format((Math.random()*((9999.99-0.00)+1))+0.00));
        				payment_amount_hashtable.put(str[3], str[4]);					
        				fStr+= payment_amount_hashtable.get(str[3])+",";
        			}  else {
        				fStr+= payment_amount_hashtable.get(str[3])+",";
        			}

        		continue;

        	} else if(i<str.length-1) {
        		fStr  += applySingleQuote(str[i]+"",i)+",";
        	} else if(i==str.length-1) {
        		fStr+=applySingleQuote(str[i],i)+"," +getTimeToAdd(numberOfRowsInFile, rowNo);
        		continue;
        	}			
        }
		payment_id++;
		return fStr;
	}

	
	@Obsolete
	static String updateRow_old(String str[]) {
		String pattern = "######.##";
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String fStr="";
		for(int i=0;i<str.length;i++) {
			if(i==0) {
				fStr="'"+payment_id+"'"+","; continue;
			}if(i==1 && !str[1].contains("'")) {
				fStr+="'"+str[1]+"',"; continue;
			}if(i==4) {						
				if(rowNo==1) {
					fStr+="'0.00',";
				} else if(rowNo==2) {
					fStr+="'99999999.99',";
				} else fStr+="'" +handleDecimalTwoPoints(decimalFormat.format((Math.random()*((99999999.99-0.00)+1))+0.00))+"',";
				continue;
			}if(i==5) {
				fStr+="'"+payment_id+"'"+","; continue;
			}
			if(i==7 && !str[7].contains("'")) {
				fStr+="'"+str[7]+"',"; continue;
			}
			if(i==str.length-1) {
				fStr+=str[i]+","+getTimeToAdd(numberOfRowsInFile, rowNo);
				continue;
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

	
	public static String applySingleQuote(String str,int i) {
			str= str.trim();
			if(integerColumns.contains(i)) {
				str= str.replace("'", "");// remove Single Quotes
			} else {
				str= str.replace("'", "");
				str = "'" + str+ "'";// Add Single Quote
			}
			
			return str;
			
	}

	
	/*
	public static String applySingleQuote(String str[]) {
		String fStr="";

		List<Integer> integerColumns = new ArrayList<Integer>();
		integerColumns.add(0);   	// 	paiment_id
		integerColumns.add(2);   	// 	userid_nbr
		integerColumns.add(3);   	//	payment_type_code
		integerColumns.add(4);   	//	amt_12
		integerColumns.add(5);  	// 	Confirmation Number
		integerColumns.add(8);  	//	debit_fi_branch_nbr
		integerColumns.add(9);  	//	debit_fi_nbr
		integerColumns.add(10); 	// 	filing_userid
		integerColumns.add(11); 	//	mbr_fi_nbr
		integerColumns.add(13);    	//	recipient_id
		integerColumns.add(14);	  	// 	revenue_expense_transit_nbr
		integerColumns.add(16);	  	// 	created_date


		for(int i=0;i<str.length;i++) {

			if(integerColumns.contains(i)) {
				str[i]= str[i].replace("'", "");// remove Single Quotes
			} else {
				str[i]= str[i].replace("'", "");
				str[i] = "'" + str[i]+ "'";// Add Single Quote
			}
			fStr+=str[i]+",";	
		}
		return fStr;
	}*/

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
	
	/**
	 * Count number of rows present in file excluding the header row
	 * @param br
	 * @return
	 */
	static int getNumberOfRows(BufferedReader br) throws Exception{
		int numOfRows = 0;
		String row = "";
		while ((row = br.readLine()) != null) {
			if(row.contains("id"))
				continue;
		    numOfRows++;
		}
		return numOfRows;
	}
	
	static String getTimeToAdd(int totalNumOfRow, int currentRow) {
		String time = "";
		int temp = totalNumOfRow/4;
		
		if(currentRow <= temp)
			time = "0:00:00";
		else if (currentRow <= temp*2)
			time = "11:59:59";
		else if (currentRow <= temp*3)
			time = "12:00:00";
		else
			time = "23:59:59";
		return time;
		
	}
}
