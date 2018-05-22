   <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
   <%@ page import="com.kds.KODE_DEV.dao.*"  %>
   <%@page import="com.kds.KODE_DEV.domain.*" %>
   <%@page import="java.util.*" %>
   <%@page import="java.sql.*"%>
   <%@page import="java.io.InputStream"%>
   <%@page import="java.io.OutputStream"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../CSS/kstyle.css"/>
<title>KODE_DEV</title>
<style type="text/css">
.subject-ul{text-align: center;}
.subject-ul li
	{
	margin-top:50px;
	margin-left:50px;
	text-align: center;
	list-style: none;
	float: left;
	width: 205px;
	}
.subject-ul li:first-child{margin-left: 0px;}
.subject-ul li a{text-decoration: none;color: #ba8c1c; font-size: 18px; font-weight: 800; font-family: Roboto-Regular;}
</style>
</head>
<body>
<%
/** 

Participant View Home Page
It contains @include Header Page,@include Footer page.

*/
%>
<%@ include file="../JSP/ParticipantViewHeader.jsp" %>
<!-- <div class="body-mtr"> --> <!-- body content start -->
<!-- <div class="container"> -->
<!-- <p style="color: #c2c2c2; font-size:18px; margin-top: 50px; margin-bottom: 20px;">Participant > <span style="color:#ba8c1c;">Courses</span></p>
<div class="subject-div">
<ul class="subject-ul">
<li><a href="#"><img src="../Image/course1.jpg" /><br/><span>Technology</span></a></li>
<li><a href="#"><img src="../Image/course2.jpg" /><br/><span>Engineering</span></a></li>
<li><a href="#"><img src="../Image/course3.jpg" /><br/><span>Mathematics</span></a></li>
<li><a href="#"><img src="../Image/course4.jpg" /><br/><span>commerce</span></a></li>
</ul>
</div> -->
<div style="clear: both;"></div>
</div>
<!-- </div> --><!-- body content close -->

<%@ include file="../JSP/footer.jsp" %>
</body>
</html>