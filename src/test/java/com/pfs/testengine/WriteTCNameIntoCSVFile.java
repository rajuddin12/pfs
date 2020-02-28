package com.pfs.testengine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.pfs.excel.operations.ExcelWriter;

public class WriteTCNameIntoCSVFile {

	/**
	 * change the value of path variable
	 * change the program name folder
	 * 
	 * 
	 */
	static List<String> allCSVFiles 		= new ArrayList<String>();
	static String path = "file:///D:/DailyStatus/2020/Feb/ASKIDA%20Program%20Execution/";
	static String url   = "";
	static String programNumber = "6.17";
	

	static String reportPath= "D:\\DailyStatus\\2020\\Feb\\ASKIDA Program Execution\\" +programNumber + "\\report.xlsx";
	
	static XSSFWorkbook workbook = new XSSFWorkbook();
	static XSSFSheet sheet;
	static Row row;
	static int rowNumber_excel = 0;

	static WebDriver driver;
	
	public static void init() {
		String driverPath = System.getProperty("user.dir") + File.separator + "Drivers" + File.separator;
		System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		//===================================
		/*File file = new File("C:/Program Files/phantomjs/bin/phantomjs.exe");				
        System.setProperty("phantomjs.binary.path", file.getAbsolutePath());		
        WebDriver driver = new Phan();*/	
		//========================
		
		driver.get(url);
	}
	
	
	@Test(enabled = true)
	public static void updateSigleCSV_File() throws Throwable {

		try {
			File folder = new File("D:\\DailyStatus\\2020\\Feb\\ASKIDA Program Execution\\" +programNumber);
			listFilesForFolder(folder);
			Iterator<String> iter = allCSVFiles.iterator();

			while(iter.hasNext()) {
				String fileName = iter.next().replace(" ", "%20");
				url = path + programNumber + "/" +fileName;
				init();
				getTCName();
			}
		} catch (Exception e) {
			driver.close();
		}
		
				
	}
	
	
	public static void getTCName() throws Throwable {
		int count = driver.findElements(By.xpath("//*[@id=\"suite-stats\"]/tbody/tr")).size();
		for(int rowNuber =3; rowNuber <=count; rowNuber++) {

			String loc_Name = "//*[@id='suite-stats']/tbody/tr[" + rowNuber + "]/td[1]/div/a";
			String loc_TimeElapsed = "//*[@id='suite-stats']/tbody/tr[" + rowNuber + "]/td[5]";

			String text2= driver.findElement(By.xpath(loc_Name)).getText();
			String TimeElapsed= driver.findElement(By.xpath(loc_TimeElapsed)).getText();

			if(text2.contains("Scenario")) {
				String Name = "		" + text2.split("Scenario")[1].replace("]", "");
				enterDataInExcel(" ", "Script", Name, TimeElapsed);
				System.out.println(Name);	
			} else  {			
				String Name = text2.split("Functionality")[1].replace("]", "");
				String progname[] = Name.split(". ");
				enterDataInExcel(progname[0], "Functionality", Name, TimeElapsed);
				System.out.println(progname[1]);
			}	
			try (FileOutputStream outputStream = new FileOutputStream(reportPath)) {
				workbook.write(outputStream);
			}
		}

	}

	public static void enterDataInExcel(String progNumber, String Category, String name, String Time) throws Throwable {
		FileInputStream ExcelFile = new FileInputStream(reportPath);
		workbook = new XSSFWorkbook(ExcelFile);
		try {
			sheet = workbook.createSheet(programNumber);
		} catch (Exception e) {
			sheet = workbook.getSheet(programNumber);
		}
		
		row = sheet.createRow(rowNumber_excel);
		
		Cell cell = row.createCell(0);
		 cell.setCellValue((String) progNumber);
		cell = row.createCell(1);
		 cell.setCellValue((String) Category);
		 cell = row.createCell(2);
		 cell.setCellValue((String) name);
		 cell = row.createCell(3);
		 cell.setCellValue((String) Time);
		 rowNumber_excel++;
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
