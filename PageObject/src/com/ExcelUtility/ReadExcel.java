package com.ExcelUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	
	XSSFWorkbook xwb;
	XSSFSheet xsheet;
	FileInputStream fis;
	/*HSSFWorkbook wb;
	HSSFSheet sheet;
*/

	public ReadExcel(String ExcelPath)
	{
		try{
			File file =new File(ExcelPath);
		 fis = new FileInputStream(file);
			xwb = new XSSFWorkbook(fis);
		}catch(Exception e){
			System.out.println(e+" Exception occured");}
	}

	public String getData(String sheetName, int row, int column)
	{	
		try {
		XSSFCell cell = null;
		xsheet = xwb.getSheet(sheetName);
		
		cell = xsheet.getRow(row).getCell(column);
		if(cell == null || cell.getCellType() == CellType.BLANK) {
			
		}
	//	cell.setCellType(Cell.C);
		String	data=cell.getStringCellValue();
		return data;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getData(int sheetindex, int row, int column){
		xsheet = xwb.getSheetAt(sheetindex);
		String	data=xsheet.getRow(row).getCell(column).getStringCellValue();
		return data;
	}
	public int rowGetCount(String sheetname){
		int row = xwb.getSheet(sheetname).getLastRowNum();
		row = row+1;
		return row;
	}
	
	public void close() throws IOException {
		fis.close();
	}
	
	
/*	public static void main(String[] args) {
			   
		ReadExcel Rx =new ReadExcel("H:\\SampleTest.xls");
		
		String Ex_data = Rx.getData(0,1,0);
		
		System.out.println(Rx.rowGetCount(0));
		
		System.out.println(Ex_data);
		
	}*/
}