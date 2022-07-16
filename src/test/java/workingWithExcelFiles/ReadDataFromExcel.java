package workingWithExcelFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;


public class ReadDataFromExcel {

	
	@Test(priority=0)
	public void ReadExcel_Specific_CellValue_Test() throws IOException {
		
		// Get the excel file
		File file  = new File("/Users/pradeep/Documents/NewStarting_Workspace/Selenium/TestData.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
				
				
				
		// Get the sheet by providing sheet name.
		XSSFSheet sheet = wb.getSheet("Sheet1");
				
		// Get the sheet by providing the index.
		XSSFSheet sheet1 = wb.getSheetAt(0);
				
				
		// Get the row
		XSSFRow row = sheet.getRow(2);
				
		//Get the cell
		XSSFCell cell = row.getCell(2);

		System.out.println(cell.getStringCellValue());
		
		System.out.println("Serial number: "+row.getCell(0).getNumericCellValue());
		System.out.println("First Name: "+row.getCell(1).getStringCellValue());
		System.out.println("Last Name: "+row.getCell(2).getStringCellValue());
		System.out.println("date: "+row.getCell(6).getStringCellValue());
		
	}
	
	@Test(priority=1)
	public void ReadExcel_EntireExcel() throws IOException {
		
		//Create an object of File class to open xlsx file
		File file  = new File("/Users/pradeep/Documents/NewStarting_Workspace/Selenium/TestData.xlsx");
		
		//Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);
		
		//creating workbook instance that refers to .xlsx file
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		
		//creating a Sheet object
		XSSFSheet sheet = wb.getSheetAt(0);
		
		
		// Get total number of rows:
		int rows = sheet.getLastRowNum() - sheet.getFirstRowNum();
		
		//Get total numbers of columns
		int cols = sheet.getRow(1).getLastCellNum();
		
		System.out.println("Total rows: "+rows);
		System.out.println("Total columns: "+cols);
		
		for(int i=0; i<=rows; i++) {
			for(int j=1; j<cols; j++) {
				System.out.print(sheet.getRow(i).getCell(j).getStringCellValue()+" ");
			}
			System.out.println();
		}
		
		
	}

}
