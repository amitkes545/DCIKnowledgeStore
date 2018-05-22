<%@page import="java.util.*"%>
<%@ page import="com.kds.KODE_DEV.domain.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Group Student List</title>
<style type="text/css">
.container.from-top > a:hover{
background: none repeat scroll 0 0 #5699FA;
color:#fff;
}
.container.from-top > a {
    background: none repeat scroll 0 0 #eee;
    color: #444;
    float: left;
    font-family: arial;
    margin: 10px;
    padding: 15px 5px; 
    text-align: center;
    text-decoration: none;
    text-transform: uppercase;
    width: 250px;
}
.from-top{margin-top:50px!important}
</style>
</head>
<body>
<%@ include file="../JSP/FacultyViewHeader.jsp" %>
<div class="body-mtr">
<div class="container from-top">
<%
ArrayList<RetriveImagesDomain> arl=(ArrayList<RetriveImagesDomain>)request.getAttribute("studentinfo");
Iterator<RetriveImagesDomain> it=arl.iterator();

while(it.hasNext()){
	RetriveImagesDomain rid=it.next();
	
	%>
	
	<a href="/KODE_DEV/ControllerServlet/IndividualStudentDetailsService?studentId=<%=rid.getCourseImageLocation() %>"><%=rid.getCourseName() %></a>
	
	
	<%
			
}
%>
</div>
</div>
<%@ include file="../JSP/FooterView.jsp" %>
</body>
</html>