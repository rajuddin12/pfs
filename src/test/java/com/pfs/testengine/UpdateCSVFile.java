package com.pfs.testengine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DecimalFormat;

public class UpdateCSVFile {

	static String[] data;
	static long payment_id = 350000;
	static int rowNo=0;		// Temporary variable to write 0.00(1st row) and 999999.99(2nd row) into the amt_12 field.
	static String fileName="TestCSVFile.csv";
	
	public static String handleDecimalTwoPoints(String number) {
		
		if(!number.contains(".") ) {			
			number = number.concat(".12");
		} else if(number.split("\\.")[1].length()<2)			
			number = number.concat("2");
		
		return number;
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

	public static void main(String agr[]) throws Exception {
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
		    
		    bw.write(updateRow(data));
		    bw.write("\n");
		    
		}
		csvReader.close();
		bw.close();
		System.out.println("Success!!");
	}
	
	
}
