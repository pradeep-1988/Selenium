package workingWithExcelFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class WriteDataToExcel {
	
	@Test
	public void CreateRowAndWrite_Excel() throws IOException {
		
		//Create an object of File class to open xlsx file
		File file = new File("/Users/pradeep/Documents/NewStarting_Workspace/Selenium/TestData.xlsx");
		
		// Create an object of FileInputStream class to read the file
		FileInputStream inputStream = new FileInputStream(file);
		
		//creating workbook instance that refers to .xlsx file
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		
		
		//Get the sheet on which we need to work upon.
		XSSFSheet sheet = wb.getSheetAt(0);
		
		//Get the last row number:
		int lastRowNum = sheet.getLastRowNum();
		
		//Create a new row at the end of the sheet and assigned it to a raw object of XSSFRow class
		XSSFRow newRow = sheet.createRow(lastRowNum+1);
		
		//Get the cell counts
		int cellCount = newRow.getLastCellNum();
		
		//Update "Hello" into all the cells of newly created row.
		for(int i=0; i<cellCount; i++) {
			newRow.getCell(i).setCellValue("Hello");
		}
		
		//write the data in excel using output stream
		FileOutputStream outputStream = new FileOutputStream(file);
		wb.write(outputStream);
		wb.close();
	}

}
