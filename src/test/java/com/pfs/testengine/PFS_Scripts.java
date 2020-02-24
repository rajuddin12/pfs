package com.pfs.testengine;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pfs.excel.operations.ReadExcel;
import com.pfs.pages.AddEditAndRemovePaymentTypePageActions;
import com.pfs.pages.FutureTransactionsPageActions;
import com.pfs.pages.MakeAPaymentPageActions;

import static com.pfs.pages.MakeAPaymentPageActions.*;
import static com.pfs.utility.CommonMethods.isElementPresent;

import com.pfs.pages.RegisteredPaymentsAndAccountsPageActions;
import com.pfs.utility.CommonMethods;
import static com.pfs.pages.AddEditAndRemovePaymentTypePageActions.*;
import static com.pfs.pages.FutureTransactionsPageActions.*;

public class PFS_Scripts extends CommonMethods {

	RegisteredPaymentsAndAccountsPageActions registeredPaymentsAndAccounts;

	@DataProvider(name = "PFSTestData")
	public Object[][] PFSTestData() throws Exception {
			initSession();
			ReadExcel.setFileConfig(filePath, sheetName);
			int totalRows = ReadExcel.getRowCount(sheetName);
			int totalCols = ReadExcel.getColumnCount(sheetName, 0);
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
	public void PFS_TestFunctionalities(String[] args) throws Exception {
	//public void PFS_TestFunctionalities( String paymentType, String paymentTypeSpace, String appURL,  String userName,  String pass,  String accountNumber,  String editAccountNumber, String paymentDate, String endDate) throws Exception {
		 var_paymentType 		= args[0];
		 var_paymentTypeSpace 	= args[1];
		 var_appURL 			= args[2];
		 var_adminUserName 		= args[3];
		 var_adminPass 			= args[4];
		 var_accountNumber 	   	= args[5];
		 var_editAccountNumber 	= args[6];			 
		 var_paymentTypeSearch 	= args[7];
		 var_status				= args[8]; 
		 var_paymentDate 		= args[9];	
		 var_endDate 			= args[10];
		 var_ReportingFromDate 	= args[11];
		 var_ReportingToDate	= args[12];
		
		/* var_appURL 			= appURL;
		 var_paymentType 		= paymentType;
		 var_paymentTypeSpace 	= paymentTypeSpace;
		 var_adminUserName 		= userName;
		 var_adminPass 			= pass;
		 var_accountNumber 	   	= accountNumber;
		 var_editAccountNumber 	= editAccountNumber;	
		 var_paymentDate 		= paymentDate;	
		 var_endDate 			= endDate;	*/
		
		 // Initialize Objects
		 new AddEditAndRemovePaymentTypePageActions();
		 new RegisteredPaymentsAndAccountsPageActions();
		 new MakeAPaymentPageActions();
		 new FutureTransactionsPageActions();
		 try {
			addAccount();
			maekAPayment();
			searchTransaction();
			cancelPayment();
			searchTransactionNotExist();
			TransactionHistory("Cancelled", var_confirmationNo_1);
			TransactionHistory("Cancellation Request", var_confirmationNo_2);
			editAccount();
			removeAccount();
			 
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	
}
