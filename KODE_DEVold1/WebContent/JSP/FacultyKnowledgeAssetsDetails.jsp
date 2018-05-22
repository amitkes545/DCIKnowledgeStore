<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@page import="com.kds.KODE_DEV.domain.*" %>
        <%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FacultyKnowledgeAssetsDetails</title>
<style type="text/css">
.ww{
text-transform: capitalize;
}
input{
border: none;
}
.h44
{
font-size: 22px;
text-transform: uppercase;
}
.up-load
{
margin-top: -40px;
margin-left: 150px;
position: absolute;
}
.sub_mit
{
margin-top: -10px;
margin-left: 150px;
position: absolute;
width: 100px;
background: #3366FF;
padding: 10px;
color: #fff;
}
.sub_mit:hover
{
background: #CC33FF;

}
.m-top{
margin-top:50px!important;
}
.center{text-algin:center;}
.certi td {
    padding: 10px;
}
.certi table {
    width: 100%;
}
.certi tr 
{
    background: none repeat scroll 0 0 #eee;
    width: 100%;
    color:#333;
}
.certi tr.title {
    background: none repeat scroll 0 0 #5699fa;
    color: #fff;
    text-align: center;
}
.certi td a{text-align: center;}
</style>
</head>

<body>
<%@ include file="../JSP/FacultyViewHeader.jsp" %>
<div class="body-mtr">
	<div class="container m-top center">
			<div style="clear: both;"></div>
	<div class="certi">

<table>
<tr class="title">
<td>Departments</td>
    <td>Description</td>
    <td>Subject</td>
    <td>File Name</td>
    <td>status</td>
    <td>File Path</td></tr> 
<%
ArrayList<AssessmentsDetailsDomain> arl1=(ArrayList<AssessmentsDetailsDomain>)request.getAttribute("assessmentDetails");
Iterator<AssessmentsDetailsDomain> it1= arl1.iterator();
while(it1.hasNext())
{
	  AssessmentsDetailsDomain add=it1.next();
	  %>
	  <tr>
	 <td> <%=add.getAssessmentName() %></td>
	  <td><%=add.getUploadedBy() %></td>
	  <td> <%=add.getAssessmentDescription() %></td>
	 <td> <%=add.getUploadedDate() %></td>
	  <td><%=add.getUploadedTime() %></td>
	 <td> <%=add.getFilePath() %></td></tr>
	  
	  <%
}

%>
</table>
</div>
	</div>
</div>
<%@ include file="../JSP/FooterView.jsp" %>
</body>
</html>