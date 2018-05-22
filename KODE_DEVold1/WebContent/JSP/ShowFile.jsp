<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@page import="java.io.*" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Generated File</title>

<script>

<%
String ReportPath=request.getParameter("ReportPath").toString().trim();
//ReportPath="https://www.tutorialspoint.com/java/lang/pdf/class_getclassloader.pdf";

File f=new File(ReportPath);

// System.out.println("ReportPath ::"+ReportPath +"		filename :: "+f.getName());
response.setContentType("APPLICATION/OCTET-STREAM");   
response.setHeader("Content-Disposition","attachment; filename=\"" + f.getName() + "\"");   
  
java.io.FileInputStream fileInputStream=new java.io.FileInputStream(ReportPath);  
            
  int i;   
  while ((i=fileInputStream.read()) != -1) {  
    out.write(i);   
  }   
  fileInputStream.close();
  
%>

</script>


</head>
<body>

<object data='<%= ReportPath %>' type="application/pdf" >
    <embed src='<%= ReportPath %>'  type="pdf" />
</object>

</body>
</html>