<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.kds.KODE_DEV.dao.*"  %>
   <%@page import="com.kds.KODE_DEV.domain.*" %>
    <%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/VocationalCoursesDetails.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link href="../CSS/webindex.css" rel="stylesheet"></link>
</head>
<body>
<%@ include file="../JSP/WebViewHeader.jsp"  %>
<div class="body-mtr"><!--  body content start -->
<div class="container">
<!-- testing part -->
<div class="subject-div">
<form name="VocationalCourses" method="post">
<ul class="subject-ul">

<%
VocationalCoursesListDao ild=new VocationalCoursesListDao();
ArrayList<DisplayCoursesDomain> arl=ild.selectVocationalCourses();
Iterator<DisplayCoursesDomain> it= arl.iterator();
while(it.hasNext())
{
	DisplayCoursesDomain dd=it.next();
     %>
     				<li class="nn-part"><a href="#" ><img class="dbimg" src="<%=dd.getImagePath().toString()%>"/><br/><span><input class="sub_btn" type="submit" name="VocationalCourseName" value="<%=dd.getCourseName()%>" onclick="vocatioalCourse()"></input></span></a></li>
     
    
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