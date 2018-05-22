<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@page import="java.io.*" %>
<%@ page import="org.apache.poi.ss.usermodel.Cell" %>
<%@ page import="org.apache.poi.ss.usermodel.HorizontalAlignment" %>
<%@ page import="org.apache.poi.ss.usermodel.Row" %>
<%@ page import="org.apache.poi.ss.util.CellRangeAddress" %>
<%@ page import="org.apache.poi.xssf.usermodel.XSSFCellStyle" %>
<%@ page import="org.apache.poi.xssf.usermodel.XSSFSheet" %>
<%@ page import="org.apache.poi.xssf.usermodel.XSSFWorkbook" %>
<%@ page import="org.apache.poi.xssf.usermodel.XSSFRichTextString" %>
<%@ page import="java.util.ArrayList" %> 
<%@ page import="java.util.HashMap" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Generated File</title>

<script>

<%
OutputStream output = null;	

String OrgName=(String)session.getAttribute("OrgName");
String ReportTitle=(String)session.getAttribute("ReptName");
String RptFileName=(String)session.getAttribute("FileName");
String[] Headers=(String[])session.getAttribute("Headers");
ArrayList<HashMap<String,String>> tabledata=(ArrayList<HashMap<String,String>>)session.getAttribute("tabledata");
String[] dataCols=(String[])session.getAttribute("dataCols");
String Filename=""+RptFileName+".xls";

response.setContentType("application/vnd.ms-excel"); //  Set the page to download to excel
response.setHeader("Content-Disposition","attachment; filename = "+Filename);

	try
	{
		
		//Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook(); 
        
        XSSFCellStyle Datastyle=workbook.createCellStyle();
        Datastyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        Datastyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        Datastyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        Datastyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        
        XSSFCellStyle orgnamestyle=workbook.createCellStyle();
        orgnamestyle.setAlignment(HorizontalAlignment.CENTER);
        
        
        
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("MIS Data");
        
        
        //org name start
        int Rowcnt=0;
        Row Headerrow = sheet.createRow(Rowcnt);
        Cell OrgNameCell=Headerrow.createCell(0);
        XSSFRichTextString orgnameval=new XSSFRichTextString(OrgName.toUpperCase());
        OrgNameCell.setCellValue(orgnameval);
        OrgNameCell.setCellStyle(orgnamestyle);
        //org name end
        
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,Headers.length+1 ));
                
        //report title start
        Rowcnt++;
        Headerrow = sheet.createRow(Rowcnt);
        Cell rptName=Headerrow.createCell(0);
        rptName.setCellValue(ReportTitle);
        rptName.setCellStyle(orgnamestyle);
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0,Headers.length+1 ));
        //report title end
        
        //empty row start
        Rowcnt++;
        Headerrow = sheet.createRow(Rowcnt);
        //empty row end
        
        //header start
        Rowcnt++;
        Headerrow = sheet.createRow(Rowcnt);
        Headerrow.createCell(0).setCellValue(" ");
        for(int i=0;i<Headers.length;i++)
        {
        	String Head=Headers[i].toString().trim();
        	Cell cellheader=Headerrow.createCell(i+1);
        	cellheader.setCellValue(Head);
        	cellheader.setCellStyle(Datastyle);
        }
        //header end
        
        //data write start
        Rowcnt++;
        for(int i=0;i<tabledata.size();i++)
        {
        	HashMap<String,String> info=tabledata.get(i);
        	Row DataRow=sheet.createRow(Rowcnt);
        	DataRow.createCell(0).setCellValue(" ");
        	for(int j=0;j<dataCols.length;j++)
        	{
        		String Data=info.get(dataCols[j]).toString().trim();
        		Cell celldata=DataRow.createCell(j+1);
        		celldata.setCellStyle(Datastyle);
        		celldata.setCellValue(Data);
        	}
        	Rowcnt++;
        }
        
        output=response.getOutputStream();
        workbook.write(output);

             
	}catch(Exception ex){}
	finally
	{
		//once report downloaded remove attributs from session
		/* session.removeAttribute("OrgName");
		session.removeAttribute("ReptName");
		session.removeAttribute("Headers");
		session.removeAttribute("dataCols");
		session.removeAttribute("tabledata");
		session.removeAttribute("FileName"); */
		System.out.println("MIS Export Excel session cleared here");
	if (output != null)
		output.close();
	
	}

%>

</script>


</head>
<body>


</body>
</html>