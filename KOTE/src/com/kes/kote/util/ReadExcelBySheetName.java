package com.kes.kote.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.kes.kote.util.SessionUtil;
import com.monitorjbl.xlsx.StreamingReader;


public class ReadExcelBySheetName {

	public static String[] Cols=new String[26];
	public static ArrayList<HashMap<String,String>> data=new ArrayList<HashMap<String,String>>();
	
	public static String SheetName="";
	
	
	public void processBySheet(String sheetname,HttpSession sess) throws Exception {
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			data=new ArrayList<HashMap<String,String>>();
			Cols=new String[26];
			SheetName=sheetname;
			
			String ExcelFilePath=util.getExcelPath();
			
			util.print("ExcelFilePath at ReadExcelBySheetName : "+ExcelFilePath);
			
			InputStream is = new FileInputStream(new File(ExcelFilePath));
        	        	
        	Workbook workbook = null;              // InputStream or File for XLSX file (required)
        	if(ExcelFilePath.trim().endsWith(".xls"))
        	{
        		try
        		{
        			//util.print("before read .xls");
        			POIFSFileSystem poifs = new POIFSFileSystem(is);
        		    workbook = new HSSFWorkbook(poifs);
        		    
            		// workbook=new HSSFWorkbook(is);
            		//util.print("after read .xls");	
        		}catch(Exception ex){util.print(ex.getMessage());}
        		
        	}else
        	{
        		workbook = StreamingReader.builder()
            	        .rowCacheSize(100)    // number of rows to keep in memory (defaults to 10)
            	        .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
            	        .open(is);   
        		//util.print("ends with .xlsx");
        	}
        	util.setSheetName(SheetName);
        	
        	Sheet sheet = workbook.getSheet(SheetName);
        	int RowCount=0;
        	int tempcolcnt=0;
        	
        	int CounterVal=ExcelSheetUtil.getRowCountVal(SheetName);
        	
        	for(Row r : sheet)
        	{
        		if(RowCount==0) // header of excel sheet
        		{
        			
            		for (Cell c : r) { //counting no of columns in excel header 
            			if(c!=null)
            				tempcolcnt++;
            		} 
            		Cols=new String[tempcolcnt]; 
            		tempcolcnt=0;
            		for (Cell c : r) {
            			String ColName=c.getStringCellValue();
           			 	if(ColName.contains(" ")) // SPOC Name
           			 		ColName=ColName.replaceAll(" ", "_");//replace space with _ to get key // SPOC_Name
           			 	
           			 	Cols[tempcolcnt]=ColName; // collection header cell value from excel // cols[0]=Department , cols[1]=SPOC_Name .... 
           			 	tempcolcnt++;
           		 	}
            		util.setSheetCols(Cols);//collect columns and stored to session
            		//util.print(util.getSheetName() +" cols	"+util.getSheetCols().toString());
        		}
        		
        		HashMap<String,String> row=new HashMap<String,String>(); // <ColumnName,CellValue>
        		
        		for(int c=0;c<Cols.length;c++)
        		{
        			Cell cel=r.getCell(c);
        			String CellVal=""; // default is empty
        			
        			if(cel!=null)
        			{
        				if(ExcelFilePath.endsWith(".xls"))
        				{
        					DataFormatter dataFormatter = new DataFormatter();
        					CellVal = dataFormatter.formatCellValue(cel);
        				}else
        				{
        					CellVal=cel.getStringCellValue();	//reading the cell value as String
        				}
        				
        			}
        			row.put(Cols[c],CellVal.trim()); // ("Department_Name","Adminisitration");
        		}
        		
        		if(RowCount!=0) // if RowCount==0 means it's header of excel
        			data.add(row); //adding row data to list
        		
        		if(RowCount>CounterVal) //checking reading row with row limit
        			break;
        		
        		RowCount++;
        	}
        	
        	//util.print("add sheet details to session");
        	
        	util.setSheetDetails(data);
        	// util.printsheetdata();
        	
        		
		}catch(Exception ex){ex.printStackTrace();}
		
	}
		
	/*public static void main(String[] args) {
		try {
			
			ReadExcelBySheetName excel=new ReadExcelBySheetName();
			excel.processBySheet(ExcelSheetUtil.USER);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}*/
}
