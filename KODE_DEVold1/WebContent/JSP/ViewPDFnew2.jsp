<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@page import="java.util.*" %>
    <%@page import="java.io.File" %>
    <%@page import="java.io.OutputStream" %>
    <%@page import="java.io.File" %>
<%@page import=" java.io.BufferedInputStream" %>
<%@page import="java.io.FileInputStream" %>
<%@page import="java.io.IOException" %>
    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
		<script language="javascript" type="text/javascript"
 src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js">
 </script>
		<script type="text/javascript" language="javascript">
        $(function() {
        	alert("in function");
            $(this).bind("contextmenu", function(e) {
                e.preventDefault();
            });
        }); 
       
</script>
<style type="text/css">
.toolbar {
   display: none;
   visibility: hidden; 
}
paper-toolbar{
display: none; 
visibility: hidden;
}
</style>
<body>
<div class="toolbar">
<%

String filename=request.getParameter("filename");
System.out.println("filename="+filename);

BufferedInputStream buf = null;
ServletOutputStream stream = null;
//String filepath="/home/ftpuser1/"+request.getParameter("filePath");
String filepath="C:/Users/PrityMukesh/Downloads/1.pdf";
System.out.println("request filepath: "+filepath);

//
File outPutFile=new File(filepath);
System.out.println((int) outPutFile.length());
//ServletOutputStream stream = response.getOutputStream();
response.setContentType("application/pdf");
response.setHeader("Content-Disposition", "inline; filename=\"1.pdf\"");
response.setContentLength((int) outPutFile.length());
//67yikuy0o98response.cu
response.reset();
stream = response.getOutputStream();


FileInputStream infile = new FileInputStream(filepath); 
    int content;
    while ((content = infile.read()) != -1) {
        stream.write(content);
    }
infile.close();
stream.close();
response.flushBuffer();
	%>
</div>
</body>
</html>