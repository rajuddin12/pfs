package com.pfs.excel.operations;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import java.io.*;
import java.util.Calendar;

/**
 * Created by abdul.wahidali on 9/5/2017.
 */
public class ReadExcel {
	public static FileInputStream fis = null;
	public static FileOutputStream fileOut = null;
	private static XSSFWorkbook workbook = null;
	private static XSSFSheet sheet = null;
	private static int totalNoOfRows;
	private static int totalNoOfCols;
	public static int rowCount = 0;

	// Return number of column count
	public static int getColumnCount(String sheetName, int rowNum) {
		Row r = sheet.getRow(rowNum);
		int maxCell = r.getLastCellNum();
		return maxCell;
	}

	// Set excel configuration
	public static void setFileConfig(String filePath, String sheetName) {
		try {
			fis = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Returns all rows which have Test env
	public static String[][] getExcelData(String path, String sheetName) {
		ReadExcel.setFileConfig(path, sheetName);
		String[][] arrayExcelData = null;
		int count = 0;
		try {
			totalNoOfRows = getRowCount(sheetName);
			totalNoOfCols = getColumnCount(sheetName, 1);
			for (int k = 0; k < totalNoOfRows; k++) {
				String colVal = getCellData(k + 1, 1, sheetName, path);
				if (colVal.equals("Test")) {
					count++;
				}
			}
			arrayExcelData = new String[count][totalNoOfCols];
			for (int l = 0; l < totalNoOfRows + 1; l++) {
				String colVal1 = getCellData(l + 1, 1, sheetName, path);

				if (colVal1.equals("Test")) {
					rowCount++;
					for (int j = 0; j < totalNoOfCols; j++) {
						arrayExcelData[rowCount - 1][j] = getCellData(l + 1, j, sheetName, path);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayExcelData;
	}

	// returns particular cell data
	public static String getCellData(int rowNum, int colNum, String SheetName, String Path) throws Exception {

		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);
			// Access the required test data sheet
			XSSFWorkbook ExcelWBook = new XSSFWorkbook(ExcelFile);
			XSSFSheet ExcelWSheet = ExcelWBook.getSheet(SheetName);
			XSSFCell cell = ExcelWSheet.getRow(rowNum).getCell(colNum);
			
			if (cell == null)
				return "";
			// System.out.println(cell.getCellType());
			if (cell.getCellType() == CellType.STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() ==  CellType.NUMERIC
					|| cell.getCellType() ==  CellType.FORMULA) {

				String cellText = String.valueOf(cell.getNumericCellValue());
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// format in form of M/D/YY
					double d = cell.getNumericCellValue();

					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR)))
							.substring(2);
					cellText = cal.get(Calendar.DAY_OF_MONTH) + "/"
							+ cal.get(Calendar.MONTH) + 1 + "/" + cellText;

					// System.out.println(cellText);

				}

				return cellText;
			} else if (cell.getCellType() == CellType.BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());

		} catch (Exception e) {

			e.printStackTrace();
			return "row " + rowNum + " or column " + colNum
					+ " does not exist in xls";
		}
		/*	String CellData = cell.getStringCellValue();	
			return CellData;*/

		}
	


	// returns the row count in a sheet
	public static int getRowCount(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return 0;
		else {
			sheet = workbook.getSheetAt(index);
			int number = sheet.getLastRowNum() + 1;
			return number;
		}

	}

}
