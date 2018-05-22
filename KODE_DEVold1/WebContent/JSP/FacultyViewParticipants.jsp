<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Participants Details</title>
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
<%@ include file="../JSP/FacultyViewHeader.jsp" %>
<div class="body-mtr"> <!-- body content start -->
<div class="container">
<p style="color: #c2c2c2; font-size:18px; margin-top: 50px; margin-bottom: 20px;">Faculty > <span style="color:#ba8c1c;">Courses</span></p>
<div class="subject-div">
<ul class="subject-ul">
<li><a href="../JSP/IndividualParticipants.jsp"><img src="../Image/course1.jpg" /><br/><span>Individual</span></a></li>
<li><a href="../JSP/GroupParticipants.jsp"><img src="../Image/course2.jpg" /><br/><span>Groups</span></a></li>
</ul>
</div>
<div style="clear: both;"></div>
</div>
</div><!-- body content close -->
<%@ include file="../JSP/FooterView.jsp" %>
</body>
</html>