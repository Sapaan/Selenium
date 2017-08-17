package Automation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ReadExcel {

	public static String myfile = System.getProperty("user.dir")+ "//src//Excels//Suite.xls";
	
	public String path;

	static String cellText;
	
	static double d;

	public FileInputStream mystream = null;
	private HSSFWorkbook workbook = null;

	private Sheet sheet = null;

	private Row row = null;

	private Cell cell = null;

	
	  public ReadExcel (String path) {
	 
	 this.path= path;
	 
	try{ mystream = new FileInputStream(path); 
	workbook = new HSSFWorkbook(mystream); 
	sheet = workbook.getSheetAt(0); 
	
	mystream.close();
	} catch (Exception e) {
	
	 System.out.println("Unable to get excel--> " + e.getMessage());
	}
	
	  }
	
	  public boolean isSheetExist(String sheetname)
	  {
		
		  

          for (int i = 0; i < workbook.getNumberOfSheets(); i++) {  

             if (workbook.getSheetName(i).equals(sheetname)) 
                
            	 return true;
            	
             }
		return false;
           }

	  
	  public void setCellData()
	  {
		  
	  }

	public String getCellData(String sheetname, int rowNum,
			int ColNum) {

		try {

			/*File myfile = new File(path);

			FileInputStream mystream = new FileInputStream(myfile);

			HSSFWorkbook workbook = new HSSFWorkbook(mystream);*/

			Sheet sheet = workbook.getSheet(sheetname);

			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";

			cell = row.getCell(ColNum);

			if (cell == null)
				return "";
			if (cell.getCellType() == cell.CELL_TYPE_STRING)

				return cell.getStringCellValue().trim();
			else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC
					|| cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {

				cellText = String.valueOf(cell.getNumericCellValue());
				
				if(HSSFDateUtil.isCellDateFormatted(cell)) {
				//	format in the form of M/D/YY
					
                d = cell.getNumericCellValue();
				Calendar cal=  Calendar.getInstance();
				cal.setTime(HSSFDateUtil.getJavaDate(d));
				cellText= String.valueOf(cal.get(Calendar.YEAR)).substring(2);
				cellText= cal.get(Calendar.DAY_OF_MONTH) + "/" + 
						cal.get(Calendar.MONTH)+1+ "/" + cellText;
				
			}
				// System.out.println("cellValue "+cellvalue);

				return cellText;
			}

			else if (cell.getCellType() == cell.CELL_TYPE_BLANK)
				return "";

			else
				return String
						.valueOf(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN);
		}

		catch (Exception e) {

			System.out.println(e.getMessage());
			return "row " + rowNum + "and column " + ColNum
					+ "does not exist in xls";
		}

	}

	public HashMap<Integer, List<String>> readExcel(String path,
			String sheetname) throws Exception

	{

		/*File myfile = new File(path);

		FileInputStream mystream = new FileInputStream(myfile);

		HSSFWorkbook workbook = new HSSFWorkbook(mystream);*/

		Sheet sheet = workbook.getSheet(sheetname);

		Row row;

		Cell cell = null;

		HashMap<Integer, List<String>> map1 = new HashMap<Integer, List<String>>();

		for (int i = (sheet.getFirstRowNum() + 2); i <= sheet.getLastRowNum(); i++) {

			List<String> list1 = new ArrayList<String>();

			row = sheet.getRow(i);

			for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++)

			{
				cell = row.getCell(j);

				list1.add(cell.getStringCellValue());
			}

			map1.put(i, list1);

		}

		System.out.println("map" + map1);

		return (map1);

	}

	public void writeExcel(int rowNum, String result, String sheetname) throws IOException

	{
		/*File myfile = new File("C:\\Selenium Automation\\MC Sanity.xls");

		// get the lock on file
		FileInputStream mystream = new FileInputStream(myfile);

		HSSFWorkbook workbook = new HSSFWorkbook(mystream);*/

		Sheet sheet = workbook.getSheet(sheetname);

		Cell cell = null;

		cell = sheet.getRow(rowNum).createCell(5);
		cell.setCellValue(result);

		FileOutputStream outputstream = new FileOutputStream(myfile);

		workbook.write(outputstream);

	}

	public HashMap<Integer, List<String>> readindexsheet(String sheetname) throws Exception {

		/*File myfile = new File("C:\\Selenium Automation\\MC Sanity.xls");

		// get the lock on file
		FileInputStream mystream = new FileInputStream(myfile);

		HSSFWorkbook workbook = new HSSFWorkbook(mystream);
*/
		Sheet sheet = workbook.getSheet(sheetname);

		Row row = null;

		Cell cell = null;

		HashMap<Integer, List<String>> map2 = new HashMap<Integer, List<String>>();

		for (int i = (sheet.getFirstRowNum() + 2); i <= sheet.getLastRowNum(); i++) {

			List<String> list2 = new ArrayList<String>();
			row = sheet.getRow(i);

			for (int j = (row.getFirstCellNum()); j < row.getLastCellNum(); j++)

			{
				cell = row.getCell(j);

				list2.add(cell.getStringCellValue());
			}

			map2.put(i, list2);

		}

		System.out.println("list>" + map2);
		return (map2);

	}
	
	public int getRowCount(String sheetname)
	{
		
	int index= workbook.getSheetIndex(sheetname);
		
	if(index==-1)
			return 0;
		
		else
{
		sheet= workbook.getSheetAt(index);
		 int number= sheet.getLastRowNum()+1;
		 return number;
		}	
			
	}
	
	public  String getCellData(String sheetName, String colName, int rowNum)
	{

try {
	if(rowNum<=0)
		return "1";
	
	int index= workbook.getSheetIndex(sheetName);
	int ColNum= -1;
	
	if (index==-1)
		
	return"";
	
	sheet= workbook.getSheetAt(index);

	row = sheet.getRow(0);
	
	for(int i=0; i<row.getLastCellNum(); i++){
		
		if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
		
			ColNum=i;
		
	}
		if(ColNum==-1)
			
			return "";
		
		sheet= workbook.getSheetAt(index);
		row = sheet.getRow(rowNum-1);
		
		if(row==null)
			
			return "";
		
		cell=row.getCell(ColNum);
		if(cell==null)
			return"";
		
		if (cell.getCellType() == cell.CELL_TYPE_STRING)

			return cell.getStringCellValue().trim();
		else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC
				|| cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) 
		{
		 cellText = String.valueOf(cell.getNumericCellValue());
		
		if(HSSFDateUtil.isCellDateFormatted(cell)) {
		//	format in the form of M/D/YY
			
        d = cell.getNumericCellValue();
		Calendar cal=  Calendar.getInstance();
		cal.setTime(HSSFDateUtil.getJavaDate(d));
		cellText= String.valueOf(cal.get(Calendar.YEAR)).substring(2);
		cellText= cal.get(Calendar.DAY_OF_MONTH) + "/" + 
				cal.get(Calendar.MONTH)+1+ "/" + cellText;
		
	}
		// System.out.println("cellValue "+cellvalue);

		return cellText;
	
		}

		else  if (cell.getCellType() == cell.CELL_TYPE_BLANK) 
		return null;

	else 
		return String
				.valueOf(cell.getBooleanCellValue());

}
catch (Exception e) {

	System.out.println(e.getMessage());
	return "row " + rowNum + "and column " + colName
			+ "does not exist in xls";
}
	
	}

	public void setCellData(String currentTestCaseName, String result, int currentTestDataSetID, String keyword_Skip) {
		// TODO Auto-generated method stub
		
	}}

		
