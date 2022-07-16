package workingWithExcelFiles;

public class ApachePOI {
	/*
	What is Apache POI ?
		- Apache POI is an open-source java library often utilized to create and handle Microsoft Office-based files.
		- Users can leverage POI to perform various operations (such as modify, create, display, read) on certain file formats (Excel files being one of them).
		- Since Java does not offer built-in support for Excel files, testers need open-source APIs to work with them.
		
	In order to create or maintain Excel Workbooks, Apache POI provides a ”Workbook” as a super-interface of all classes.
		- It belongs to org.apache.poi.ss.usermodel package. 
		- It uses WorkbookFactory class for creating the appropriate kind of Workbook (i.e. HSSFWorkbook or XSSFWorkbook).
		- The two classes which implement the “Workbook” interface are given below:
			* XSSFWorkbook: Methods of this class are used to read/write data to MS Excel and OpenOffice XML files in .xls or .xlsx format.
			* HSSFWorkbook: Methods of this class are used to read/write data to MS Excel file in .xls format.
	
	
	Classes and Interfaces in POI:
		1. Workbook interface: XSSFWorkbook and HSSFWorkbook classes implement this interface.
			* XSSFWorkbook: It is a class representation of XLSX file.
			* HSSFWorkbook: It is a class representation of XLS file.
		2. Sheet interface: XSSFSheet and HSSFSheet classes implement this interface.
			* XSSFSheet: It is a class representing a sheet in an XLSX file.
			* HSSFSheet: It is a class representing a sheet in an XLS file.
		3. Row interface: XSSFRow and HSSFRow classes implement this interface.
			* XSSFRow: It is a class representing a row in the sheet of XLSX file.
			* HSSFRow: It is a class representing a row in the sheet of XLS file.
		4. Cell interface: XSSFCell and HSSFCell classes implement this interface.
			* XSSFCell: It is a class representing a cell in a row of XLSX file.
			* HSSFCell: It is a class representing a cell in a row of XLS file.
	*/

}
