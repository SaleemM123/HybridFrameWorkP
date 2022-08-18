package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
XSSFWorkbook wb;
//constructor for reading excel path
public ExcelFileUtil(String excelpath) throws Throwable
{
	FileInputStream fi= new FileInputStream(excelpath);
	wb= new XSSFWorkbook(fi);
}
//count no of cells ina row
public int rowcount(String sheetName)
{
	return wb.getSheet(sheetName).getLastRowNum();
}
//count no of cells in a row
public int cellcount(String sheetName)
{
	return wb.getSheet(sheetName).getRow(0).getLastCellNum();
}
//read cell data from sheet
public String getCellData(String sheetName, int row, int column)
{
	String data="";
	if(wb.getSheet(sheetName).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
	{
		int celldata=(int)wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
		data=String.valueOf(celldata);
	}
	else
		{
		data=wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
		
		}
	return data;
	}
	//set cell data
public void setCellData(String sheetName, int row, int column, String status, String writeexcel) throws Throwable
{
	//get sheet from wb
	XSSFSheet ws=wb.getSheet(sheetName);
	//get row from sheet
	XSSFRow rowNum=ws.getRow(row);
	//creat cell
	XSSFCell cell=rowNum.createCell(column);
	//write status into cell
	cell.setCellValue(status);
	if(status.equalsIgnoreCase("Pass"))
	{
		XSSFCellStyle style=wb.createCellStyle();
		XSSFFont font=wb.createFont();
		font.setColor(IndexedColors.BRIGHT_GREEN.getIndex());
		font.setBold(true);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rowNum.getCell(column).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("Fail"))
	{
		XSSFCellStyle style=wb.createCellStyle();
		XSSFFont font=wb.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		font.setBold(true);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rowNum.getCell(column).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("Blocked"))
	{
		XSSFCellStyle style=wb.createCellStyle();
		XSSFFont font=wb.createFont();
		font.setColor(IndexedColors.BLUE.getIndex());
		font.setBold(true);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rowNum.getCell(column).setCellStyle(style);
	}
	FileOutputStream fo=new FileOutputStream(writeexcel);
	wb.write(fo);
	}
public static void main(String[] args)throws Throwable
{
	ExcelFileUtil xl=new ExcelFileUtil("D://dummy.xlsx");
	int rc=xl.rowcount("Input");
	System.out.println(rc);
	for(int i=1;i<=rc;i++)
	{
		String user=xl.getCellData("Input", i, 0);
		String pass=xl.getCellData("Input", i, 1);
		System.out.println(user+"   "+pass);
		xl.setCellData("Input", i, 2, "Pass", "D://result.xlsx");
	}
}


}
