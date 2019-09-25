package com.pfs.excel.operations;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import com.pfs.reporting.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class ExcelWriter {

	// Update the result in excel sheet
	public static void setCellData(String Result, int RowNum, int ColNum, String SheetName, String Path) {

		try {
			System.out.println();
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);
			// Access the required test data sheet
			XSSFWorkbook ExcelWBook = new XSSFWorkbook(ExcelFile);
			XSSFSheet ExcelWSheet = ExcelWBook.getSheet(SheetName);
			XSSFRow Row1 = ExcelWSheet.getRow(RowNum);
			// Cell1 = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
			XSSFCell Cell1 = Row1.getCell(ColNum);
			//XSSFCell Cell1 = Row1.createCell(ColNum);
			Cell1.setCellValue(Result);
			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(Path);
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Excel wirte exception : " + e);
			Assert.fail();
		}
	}
}
