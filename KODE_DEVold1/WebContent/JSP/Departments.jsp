<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.kds.KODE_DEV.domain.DepartmentsDomain" %>
     <%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/Semdetails.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Departments</title>

<link href="../CSS/webindex.css" rel="stylesheet"></link>
</head>
<body>
<%@ include file="../JSP/WebViewHeader.jsp"  %>
<div class="body-mtr"><!--  body content start -->
<div class="container">
<!-- testing part -->
<div class="subject-div">
<form name="DepartmentName" method="post">
<ul class="subject-ul">

<%
ArrayList<DepartmentsDomain> arl=(ArrayList<DepartmentsDomain>)request.getAttribute("departments");

Iterator<DepartmentsDomain> it= arl.iterator();
while(it.hasNext())
{
	DepartmentsDomain dd=it.next();
%>
     				<li class="nn-part"><a href="/KODE_DEV/ControllerServlet/SemestersService?DepartmentsName=<%=dd.getDepartmentsNmae() %>" ><img class="dbimg" src="<%=dd.getImageLocation().toString()%>"/><br/><span><input class="sub_btn" type="submit" name="DepartmentsName" value="<%=dd.getDepartmentsNmae()%>" onclick="Sem()"></input></span></a></li>
     
    
  <% } %>

</ul>
</form>
</div>
</div>
</div>
<div id="page_footer1">
<p id="footer-text">Powered by <a href="www.kompacdigit.com" target="_newwindow" style="color: #fff;text-decoration: none">Kompac Digital Systems</a></p>
</div>

</body>
</html>