package com.ExcelUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {
	XSSFWorkbook xwb;
	XSSFSheet xsheet;
	FileOutputStream fos;
	FileInputStream fis;
	File file;
	
	public WriteExcel(String ExcelPath)
	{
		try{
			file =new File(ExcelPath);
			fis = new FileInputStream(file);
			xwb = new XSSFWorkbook(fis);
		}catch(Exception e){System.out.println(e+" Exception occured");}
	}
	
	public synchronized void writeData(String sheetName, String variableName, String variableValue)
	{
		System.out.println("inside write function");
		try
		{
		XSSFRow rowindex =  null;	
		XSSFCell cell = null;
		String data;
		xsheet= xwb.getSheet(sheetName);
		int totalRows = xsheet.getLastRowNum();
		for(int i = 0 ; i <= totalRows ; i++){
			rowindex = xsheet.getRow(i);
			cell = rowindex.getCell(0);
			data = cell.getStringCellValue();
			if(data.equalsIgnoreCase(variableName)){
				cell = rowindex.getCell(2);
				if(cell == null || cell.getCellType() == CellType.BLANK) {
					cell = rowindex.createCell(2);
				}
				cell.setCellType(CellType.STRING);
				cell.setCellValue(variableValue);
				break;
			}
		}
		fos= new FileOutputStream(file);
		xwb.write(fos);
		fos.flush();
		fos.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception: "+e.getMessage());
		}
	}
	
}
