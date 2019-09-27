package com.pfs.testengine;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pfs.excel.operations.ReadExcel;
import com.pfs.pages.AddEditAndRemovePaymentTypePageActions;
import com.pfs.pages.RegisteredPaymentsAndAccountsPageActions;
import com.pfs.reporting.ExecutionLog;
import com.pfs.utility.CommonMethods;
import static com.pfs.pages.AddEditAndRemovePaymentTypePageActions.*;

public class PFS_Scripts extends CommonMethods {

	RegisteredPaymentsAndAccountsPageActions registeredPaymentsAndAccounts;

	@DataProvider(name = "PFSTestData")
	public Object[][] PFSTestData() throws Exception {
			initSession();
			ReadExcel.setFileConfig(filePath, sheetName);
			int totalRows = ReadExcel.getRowCount(sheetName);
			int totalCols = 6;
			Object[][] tabArray = new Object[totalRows-1][totalCols];			
			try {
			int startCol = 0;
			int ci, cj;
			ci = 0;
			for (int i = 1; i <= totalRows-1; i++, ci++) {
				cj = 0;
				for (int j = startCol; j <= totalCols-1; j++, cj++) {
					tabArray[ci][cj] = ReadExcel.getCellData(i, j, sheetName, filePath);
				}
			}
			return tabArray;
		} catch (FileNotFoundException e){
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		} catch (IOException e){
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return(tabArray);

	}
	
	
	@Test(dataProvider= "PFSTestData", groups = "Regression", enabled = true, description="PFS_TestFunctionalities")
	public void PFS_TestFunctionalities( String paymentType, String appURL,  String userName,  String pass,  String accountNumber,  String editAccountNumber) throws Exception {
		 var_appURL 			= appURL;
		 var_paymentType 		= paymentType;
		 var_adminUserName 		= userName;
		 var_adminPass 			= pass;
		 var_accountNumber 	   	= accountNumber;
		 var_editAccountNumber 	= editAccountNumber;	
		
		 // Initialize Objects
		 new AddEditAndRemovePaymentTypePageActions();
		 new RegisteredPaymentsAndAccountsPageActions();
		 try {
			
			 addAccount();
			 editAccount();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	
}
